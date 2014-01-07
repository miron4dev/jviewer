package ru.spb.herzen.jviewer.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.spb.herzen.jviewer.model.RoomModel;
import ru.spb.herzen.jviewer.model.RoomModelImpl;
import ru.spb.herzen.jviewer.model.UserModel;
import ru.spb.herzen.jviewer.service.LoginService;
import ru.spb.herzen.jviewer.service.SecurityService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginControllerImpl implements LoginController {

    private UserModel userModel;
    private RoomModel roomModel;
    private AuthenticationManager authenticationManager;
    private LoginService loginService;

    @Override
    public String loginUser() {
        try{
            authentication();
            roomModel.setRooms(loadNames(loginService.getRooms())); //TODO: architect solution is bad
            return "rooms?faces-redirect=true";
        } catch (BadCredentialsException e){
            return null;
        }
    }

    @Override
    public String loginAdmin() {
        try{
            authentication();
            roomModel.setRooms(loadNames(loginService.getRooms()));
            return "admin?faces-redirect=true";
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

    @Override
    public String pageRedirect(String page){
        userModel.setCurrentRoom(roomModel.getCurrentRoom());
        return page + "?faces-redirect=true";
    }

    private void authentication(){
        Authentication request = new UsernamePasswordAuthenticationToken(userModel.getName(), userModel.getPassword());
        Authentication result = authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);
    }

    private List<String> loadNames(List<RoomModelImpl> roomModelList){
        List<String> names = new ArrayList<>();
        for (RoomModelImpl aRoomModelList : roomModelList) {
            names.add(aRoomModelList.getName());
        }

        return names;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
    }
}
