package ru.spb.herzen.jviewer.service.impl;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.spb.herzen.jviewer.model.impl.RequestModel;
import ru.spb.herzen.jviewer.model.UserModel;
import ru.spb.herzen.jviewer.service.LoginService;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Spring Security service.
 * @author Evgeny Mironenko
 */
public class SecurityService implements AuthenticationProvider {

    private LoginService loginService;
    private RequestModel requestModel;
    private UserModel userModel;

    /**
     * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserModel user = loginService.getData(requestModel);

        if (user == null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Data is invalid.");
            throw new BadCredentialsException("Data is invalid.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        userModel.setEnabled(user.isEnabled());
        userModel.setRole(user.getRole());
        userModel.setFaculty(user.getFaculty());
        userModel.setId(user.getId());
        userModel.setInvitationID(user.getInvitationID());
        userModel.setName(user.getName());
        userModel.setPassword(user.getPassword());
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    /**
     * @see org.springframework.security.authentication.AuthenticationProvider#supports(Class)
     */
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

    public void setRequestModel(RequestModel requestModel) {
        this.requestModel = requestModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
