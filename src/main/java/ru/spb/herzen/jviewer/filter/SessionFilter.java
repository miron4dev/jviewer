package ru.spb.herzen.jviewer.filter;

import ru.spb.herzen.jviewer.model.UserModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 10:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionFilter implements Filter {

    private UserModel userModel;
    private List<String> urlList;

    public void init() {
        urlList = new ArrayList<String>();
        urlList.add("/display.xhtml");
        urlList.add("/main.xhtml");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getServletPath();

        if (urlList.contains(url) && !userModel.isOnline()){
            response.sendRedirect(request.getContextPath() + "/index.xhtml");
        }
        else filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
