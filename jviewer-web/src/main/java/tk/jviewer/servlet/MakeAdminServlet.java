package tk.jviewer.servlet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import tk.jviewer.business.api.UpdateProfileService;

import javax.faces.application.FacesMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static tk.jviewer.model.JViewerUriPath.LOGIN_PAGE;

/**
 * Servlet for granting the admin permissions. Admin get link to this servlet by email.
 */
@WebServlet("/makeadmin")
public class MakeAdminServlet extends HttpServlet implements EncryptedRedirectionServlet {

    private static final long serialVersionUID = 582766960556597123L;
    private static final Logger logger = Logger.getLogger(MakeAdminServlet.class);

    @Autowired
    private UpdateProfileService updateProfileService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            updateProfileService.makeAdmin(req.getQueryString());
            req.getSession().setAttribute(SERVLET_RESULT, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Admin Access has been granted.", null));
        } catch (Exception e) {
            logger.error("Could not give the admin permissions", e);
            req.getSession().setAttribute(SERVLET_RESULT, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Redirect link is incorrect.", null));
        }
        resp.sendRedirect("/" + LOGIN_PAGE.getUri());
    }
}
