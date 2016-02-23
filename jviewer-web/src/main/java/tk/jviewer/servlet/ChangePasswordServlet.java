package tk.jviewer.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import tk.jviewer.business.api.UpdateProfileService;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet that changes the user password.
 */
@WebServlet("/changepassword")
public class ChangePasswordServlet extends EncryptedRedirectionServlet {

    private static final long serialVersionUID = -2362169443937780530L;
    private static final String SUCCESS_MESSAGE = "Password has been changed.";

    @Autowired
    private UpdateProfileService updateProfileService;

    @Override
    String execute(String queryString) {
        updateProfileService.changePassword(queryString);
        return SUCCESS_MESSAGE;
    }
}
