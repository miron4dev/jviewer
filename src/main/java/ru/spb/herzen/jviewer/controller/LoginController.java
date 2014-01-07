package ru.spb.herzen.jviewer.controller;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface LoginController {

    String loginUser();
    String loginAdmin();
    String logout();
    String pageRedirect(String page);
}
