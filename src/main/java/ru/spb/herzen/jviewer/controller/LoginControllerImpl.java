package ru.spb.herzen.jviewer.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.spb.herzen.jviewer.model.UserModel;
import ru.spb.herzen.jviewer.service.LoginService;
import ru.spb.herzen.jviewer.service.SecurityService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginControllerImpl implements LoginController {

    private UserModel userModel;
    private AuthenticationManager authenticationManager;

    @Override
    public String loginUser() {
        try{
            authentication();
            return "viewer?faces-redirect=true";
        } catch (BadCredentialsException e){
            return null;
        }
    }

    public String loginAdmin() {
        try{
            authentication();
            return "editor?faces-redirect=true";
        } catch (BadCredentialsException e){
            return null;
        }
    }

    @Override
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        SecurityContextHolder.getContext().setAuthentication(null);
        return "index?faces-redirect=true";
    }

    private void authentication(){
        Authentication request = new UsernamePasswordAuthenticationToken(userModel.getName(), userModel.getPassword());
        Authentication result = authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
