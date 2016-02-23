package tk.jviewer.servlet;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.faces.application.FacesMessage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static tk.jviewer.model.JViewerUriPath.LOGIN_PAGE;

/**
 * Common abstract class for the servlets which handle redirects from encrypted links. Users get link to this servlet by email.
 */
public abstract class EncryptedRedirectionServlet extends HttpServlet {

    private static final long serialVersionUID = 5973168340716073028L;
    private static final Logger logger = Logger.getLogger(EncryptedRedirectionServlet.class);

    private static final String ERROR_MESSAGE = "Redirect link is outdated or incorrect!";
    public static final String SERVLET_RESULT = "SERVLET_RESULT";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Invalidate the session to be redirected to login page.
            req.getSession().invalidate();
            SecurityContextHolder.getContext().setAuthentication(null);

            String message = execute(req.getQueryString());
            req.getSession().setAttribute(SERVLET_RESULT, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
        } catch (Exception e) {
            logger.error("Operation failed!", e);
            req.getSession().setAttribute(SERVLET_RESULT, new FacesMessage(FacesMessage.SEVERITY_ERROR, ERROR_MESSAGE, null));
        }
        resp.sendRedirect("/" + LOGIN_PAGE.getUri());
    }

    /**
     * Executes a business method based on the specified request query string.
     *
     * @param queryString request query string.
     * @return message to be shown on the login page.
     */
    abstract String execute(String queryString);
}
