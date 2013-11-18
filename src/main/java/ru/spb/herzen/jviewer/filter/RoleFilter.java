package ru.spb.herzen.jviewer.filter;

import ru.spb.herzen.jviewer.model.UserModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoleFilter implements Filter{

    private UserModel userModel;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getServletPath();

        if (url.equals("/display.xhtml") && !userModel.getRole().equals("admin") || url.equals("/main.xhtml") && !userModel.getRole().equals("user")){
            response.sendRedirect(request.getContextPath() + "/index.xhtml");
        }
        else filterChain.doFilter(request, response);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
