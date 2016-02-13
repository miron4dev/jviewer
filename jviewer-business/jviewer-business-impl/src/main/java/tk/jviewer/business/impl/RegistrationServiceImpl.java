package tk.jviewer.business.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.business.api.MailService;
import tk.jviewer.business.api.RegistrationService;
import tk.jviewer.business.model.JViewerBusinessException;
import tk.jviewer.business.model.UserEntity;

import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;

import static tk.jviewer.business.model.JViewerBusinessException.ErrorCode.USER_ALREADY_EXIST;

/**
 * Registration service implementation.
 *
 * @author Evgeny Mironenko
 */
@Component(value = "registrationService")
public class RegistrationServiceImpl implements RegistrationService, Serializable {

    private static final long serialVersionUID = -4988532465845828054L;

    private static final String MAIL_SUBJECT = "JViewer. Complete Registration Process";
    private static final String MAIL_TEXT = "Hello, {0}\nTo complete the registration process click on this link {1}";
    private static final char DATA_SEPARATOR = ':';
    private static final String REGISTRATION_SERVLET_PATH = "/registration?";

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private TextEncryptor encryptor;

    @Autowired
    private MailService mailService;

    @Override
    @Transactional
    public void createProfile(String encryptedData) throws DataIntegrityViolationException {
        String[] data = StringUtils.split(encryptor.decrypt(encryptedData), DATA_SEPARATOR);
        em.persist(new UserEntity(data[0], encoder.encode(data[2]), data[1]));
    }

    @Override
    @Transactional
    public void sendEmailConfirmation(String name, String password, String email) throws MessagingException {
        if (isUserExists(name, email)) {
            throw new JViewerBusinessException(USER_ALREADY_EXIST);
        }
        String link = generateLink(name, email, password);
        mailService.sendMessage(email, MAIL_SUBJECT, MessageFormat.format(MAIL_TEXT, name, link));
    }

    /**
     * Returns true if user with the specified name OR email is already exist.
     *
     * @param name  name of user.
     * @param email email of user.
     * @return see description.
     */
    private boolean isUserExists(String name, String email) {
        if (em.find(UserEntity.class, name) != null) {
            return true;
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> user = query.from(UserEntity.class);
        query.where(cb.equal(user.get("email"), email));
        try {
            return em.createQuery(query).getSingleResult() != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    /**
     * Generates email link based on the specified user name, password and email.
     *
     * @param name     username.
     * @param password password.
     * @param email    email.
     * @return generated and encoded link.
     */
    private String generateLink(String name, String password, String email) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String urlData = StringUtils.join(Arrays.asList(name, password, email), DATA_SEPARATOR);
        try {
            return new URL(request.getScheme(), request.getServerName(), request.getServerPort(),
                REGISTRATION_SERVLET_PATH + encryptor.encrypt(urlData)).toString();
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
