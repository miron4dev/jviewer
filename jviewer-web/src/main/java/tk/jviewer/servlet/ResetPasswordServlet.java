package tk.jviewer.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import tk.jviewer.business.api.UpdateProfileService;

import javax.servlet.annotation.WebServlet;
import java.text.MessageFormat;

/**
 * Servlet that resets the user password. Users get link to this servlet by email.
 */
@WebServlet("/resetpassword")
public class ResetPasswordServlet extends EncryptedRedirectionServlet {

    private static final long serialVersionUID = -2362169443937780530L;
    private static final String SUCCESS_MESSAGE = "Password has been successfully changed and now you can login.\nYou''r new password is {0}.";

    @Autowired
    private UpdateProfileService updateProfileService;

    @Override
    String execute(String queryString) {
        String newPassword = updateProfileService.changePassword(queryString);
        return MessageFormat.format(SUCCESS_MESSAGE, newPassword);
    }
}
