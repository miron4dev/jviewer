package tk.jviewer.business.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


/**
 * Implements {@link UpdateProfileService}.
 */
@Component
public class UpdateProfileServiceImpl implements UpdateProfileService {

    private static final String ADMIN_EMAIL = "emironen0@gmail.com";
    private static final String MAIL_SUBJECT = "JViewer. New Admin Request";
    private static final String MAIL_TEXT = "Dear user,\n{0} requires the admin permissions. His / her email is {1}\n" +
        "To give the admin permissions click on this link {2}";
    private static final char DATA_SEPARATOR = ':';
    private static final String MAKE_ADMIN_SERVLET_PATH = "/makeadmin?";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    private MailService mailService;

    @Autowired
    private TextEncryptor encryptor;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void sendAdminRequest(String username, String email) throws MessagingException {
        String link = generateLink(username, email);
        mailService.sendMessage(ADMIN_EMAIL, MAIL_SUBJECT, MessageFormat.format(MAIL_TEXT, username, email, link));
    }

    @Override
    @Transactional
    public void makeAdmin(String encryptedData) {
        String username = StringUtils.split(encryptor.decrypt(encryptedData), DATA_SEPARATOR)[0];
        UserEntity userEntity = em.find(UserEntity.class, username);
        userEntity.setRole(ROLE_ADMIN);
        em.merge(userEntity);
    }

    /**
     * Generates email link based on the specified name and email.
     *
     * @param name  username.
     * @param email email.
     * @return generated and encoded link.
     */
    private String generateLink(String name, String email) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String urlData = StringUtils.join(Arrays.asList(name, email), DATA_SEPARATOR);
        try {
            return new URL(request.getScheme(), request.getServerName(), request.getServerPort(),
                MAKE_ADMIN_SERVLET_PATH + encryptor.encrypt(urlData)).toString();
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
