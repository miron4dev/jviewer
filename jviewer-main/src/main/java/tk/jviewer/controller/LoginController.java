package tk.jviewer.controller;

/**
 * Login management interface.
 * @author Evgeny Mironenko
 */
public interface LoginController {

    /**
     * Login user.
     * @return user URL.
     */
    String loginUser();

    /**
     * Login admin.
     * @return admin URL.
     */
    String loginAdmin();

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
