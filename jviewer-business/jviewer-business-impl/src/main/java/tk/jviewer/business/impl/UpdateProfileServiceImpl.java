package tk.jviewer.business.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.business.api.MailService;
import tk.jviewer.business.api.UpdateProfileService;
import tk.jviewer.business.model.UserEntity;

import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;


/**
 * Implements {@link UpdateProfileService}.
 */
@Component
public class UpdateProfileServiceImpl implements UpdateProfileService {

    private static final char DATA_SEPARATOR = ':';

    private static final String ADMIN_EMAIL = "emironen0@gmail.com";
    private static final String ADMIN_REQUEST_MAIL_SUBJECT = "JViewer. New Admin Request";
    private static final String ADMIN_REQUEST_MAIL_TEXT = "Dear user,\n{0} requires the admin permissions. His / her email is {1}\n" +
        "To give the admin permissions click on this link {2}";
    private static final String MAKE_ADMIN_SERVLET_PATH = "/makeadmin?";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    private static final String CHANGE_PASSWORD_MAIL_SUBJECT = "JViewer. Change password request";
    private static final String CHANGE_PASSWORD_MAIL_TEXT = "Hello {0},\nTo change the password please follow this link {1}";
    private static final String CHANGE_PASSWORD_SERVLET_PATH = "/changepassword?";

    @Autowired
    private MailService mailService;

    @Autowired
    private TextEncryptor encryptor;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void sendAdminRequest(String username, String email) throws MessagingException {
        String link = generateLink(Arrays.asList(username, email), MAKE_ADMIN_SERVLET_PATH);
        mailService.sendMessage(ADMIN_EMAIL, ADMIN_REQUEST_MAIL_SUBJECT, MessageFormat.format(ADMIN_REQUEST_MAIL_TEXT, username, email, link));
    }

    @Override
    @Transactional
    public void makeAdmin(String encryptedData) {
        String username = StringUtils.split(encryptor.decrypt(encryptedData), DATA_SEPARATOR)[0];
        UserEntity userEntity = em.find(UserEntity.class, username);
        userEntity.setRole(ROLE_ADMIN);
        em.merge(userEntity);
    }

    @Override
    public void sendChangePasswordRequest(String username, String email, String newPassword) throws MessagingException {
        String link = generateLink(Arrays.asList(username, newPassword), CHANGE_PASSWORD_SERVLET_PATH);
        mailService.sendMessage(email, CHANGE_PASSWORD_MAIL_SUBJECT, MessageFormat.format(CHANGE_PASSWORD_MAIL_TEXT, username, link));
    }

    @Override
    @Transactional
    public void changePassword(String encryptedData) {
        String[] decryptedData = StringUtils.split(encryptor.decrypt(encryptedData), DATA_SEPARATOR);
        UserEntity userEntity = em.find(UserEntity.class, decryptedData[0]);
        userEntity.setPassword(encoder.encode(decryptedData[1]));
        em.merge(userEntity);
    }

    /**
     * Generates email link based on the specified name and email.
     *
     * @param dataToEncrypt list of the data to be encrypted. Keep in mind that it will be decrypted with the same order.
     * @return generated and encoded link.
     */
    private String generateLink(List<String> dataToEncrypt, String servletUrl) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String urlData = StringUtils.join(dataToEncrypt, DATA_SEPARATOR);
        try {
            return new URL(request.getScheme(), request.getServerName(), request.getServerPort(),
                servletUrl + encryptor.encrypt(urlData)).toString();
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
