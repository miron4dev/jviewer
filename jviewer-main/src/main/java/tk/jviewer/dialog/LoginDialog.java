package tk.jviewer.dialog;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Serves for "login" use case.
 */
public class LoginDialog {

    private String username;
    private String password;

    private AuthenticationManager authenticationManager;

    /**
     * Prepares user for login to system.
     * @return URL for redirect. In BadCredentialsException cases return null and show error message.
     */
    public String login() {
        try{
            Authentication request = new UsernamePasswordAuthenticationToken(username, password);
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            return "main?faces-redirect=true";
        } catch (BadCredentialsException e){
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //
    // Dependency Injection
    //

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
