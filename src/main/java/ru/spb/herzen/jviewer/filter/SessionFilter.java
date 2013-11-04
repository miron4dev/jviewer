package ru.spb.herzen.jviewer.filter;

import ru.spb.herzen.jviewer.model.UserModel;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
    private ArrayList<String> urlList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("not-avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");

        urlList = new ArrayList<String>();

        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getServletPath();

        if (urlList.contains(url) && !userModel.isOnline()){
            response.sendRedirect(request.getContextPath() + "/index.jsf");
        }
        else filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
