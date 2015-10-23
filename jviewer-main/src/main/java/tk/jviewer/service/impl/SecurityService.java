package tk.jviewer.service.impl;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tk.jviewer.profile.UserProfile;
import tk.jviewer.service.LoginService;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static tk.jviewer.profile.Permission.*;

/**
 * Implementation of Spring Security service.
 * @author Evgeny Mironenko
 */
public class SecurityService implements AuthenticationProvider {

    private LoginService loginService;
    private UserProfile userProfile;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserProfile user = loginService.getData(username, password);

        if (user == null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Data is invalid.");
            throw new BadCredentialsException("Data is invalid.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        userProfile.setName(user.getName());
        userProfile.setRole(user.getRole());
        if ("ROLE_ADMIN".equals(user.getRole())) {
            userProfile.setPermissions(Arrays.asList(CREATE_ROOM, DELETE_ROOM, EDIT_VIEWER, CREATE_QUIZ, EDIT_QUIZ, DELETE_QUIZ));
        }
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    //
    // Setters for Dependency Injection.
    //

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
