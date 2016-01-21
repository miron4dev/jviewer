package tk.jviewer.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import tk.jviewer.business.api.RegistrationService;

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
 * Servlet for users registration. Users get link to this servlet by email.
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = -2403791510373217767L;

    public static final String REGISTRATION_RESULT = "REGISTRATION_RESULT";

    @Autowired
    private RegistrationService registrationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            registrationService.createProfile(req.getQueryString());
            req.getSession().setAttribute(REGISTRATION_RESULT, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Registration was successful. Now you can login.", null));
        } catch (Exception e) {
            req.getSession().setAttribute(REGISTRATION_RESULT, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Redirect link is outdated or incorrect. Registration has failed!", null));
        }
        resp.sendRedirect("/" + LOGIN_PAGE.getUri());
    }
}
