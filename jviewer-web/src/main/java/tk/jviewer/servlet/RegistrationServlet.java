package tk.jviewer.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import tk.jviewer.business.api.RegistrationService;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet for users registration. Users get link to this servlet by email.
 */
@WebServlet("/registration")
public class RegistrationServlet extends EncryptedRedirectionServlet {

    private static final long serialVersionUID = -2403791510373217767L;
    private static final String SUCCESS_MESSAGE = "Registration was successful. Now you can login.";

    @Autowired
    private RegistrationService registrationService;

    @Override
    String execute(String queryString) {
        registrationService.createProfile(queryString);
        return SUCCESS_MESSAGE;
    }
}
