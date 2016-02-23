package tk.jviewer.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import tk.jviewer.business.api.UpdateProfileService;

import javax.servlet.annotation.WebServlet;

/**
 * Servlet for granting the admin permissions. Admin get link to this servlet by email.
 */
@WebServlet("/makeadmin")
public class MakeAdminServlet extends EncryptedRedirectionServlet {

    private static final long serialVersionUID = 582766960556597123L;
    private static final String SUCCESS_MESSAGE = "Admin Access has been granted.";

    @Autowired
    private UpdateProfileService updateProfileService;

    @Override
    String execute(String queryString) {
        updateProfileService.makeAdmin(queryString);
        return SUCCESS_MESSAGE;
    }
}
