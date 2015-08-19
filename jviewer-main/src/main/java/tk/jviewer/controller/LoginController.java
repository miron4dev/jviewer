package tk.jviewer.controller;

/**
 * Login management interface.
 * @author Evgeny Mironenko
 */
public interface LoginController {

    /**
     * Does login.
     * @return URL for navigation.
     */
    String login();

    /**
     * Logout any profile.
     * @return index URL.
     */
    String logout();

    /**
     * Redirect profile to some page.
     * @param page page value.
     * @return URL for redirect.
     */
    String pageRedirect(String page);
}
