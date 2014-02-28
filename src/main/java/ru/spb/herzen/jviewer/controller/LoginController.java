package ru.spb.herzen.jviewer.controller;

/**
 * Login management interface.
 * @author Evgeny Mironenko
 */
public interface LoginController {

    /**
     * Login user to JViewer.
     * @return user URL.
     */
    String loginUser();

    /**
     * Login admin to JViewer.
     * @return admin URL.
     */
    String loginAdmin();

    /**
     * Logout user from JViewer.
     * @return index URL.
     */
    String logout();

    /**
     * Redirect user to some page.
     * @param page page value.
     * @return URL for redirect.
     */
    String pageRedirect(String page);
}
