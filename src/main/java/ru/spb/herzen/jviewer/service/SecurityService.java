package ru.spb.herzen.jviewer.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.spb.herzen.jviewer.model.UserModel;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeny
 * Date: 1/5/14
 * Time: 2:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityService implements AuthenticationProvider {

    private LoginService loginService;
    private UserModel userModel;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserModel user = loginService.getData(userModel);

        if (user == null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Username not found.");
            throw new BadCredentialsException("Username not found.");
            //TODO logging
        }

        if (!password.equals(user.getPassword())) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Wrong password.");
            throw new BadCredentialsException("Wrong password.");
            //TODO logging
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
