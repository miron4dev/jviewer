package tk.jviewer.controller.impl;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import tk.jviewer.controller.LoginController;
import tk.jviewer.model.RoomModel;
import tk.jviewer.model.UserModel;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Login controller implementation.
 * @author Evgeny Mironenko
 */
public class LoginControllerImpl implements LoginController {

    private static final Logger LOG = Logger.getLogger(LoginControllerImpl.class);

    private UserModel userModel;
    private RoomModel roomModel;
    private AuthenticationManager authenticationManager;

    /**
     * Prepares user for login to system.
     * @return URL for redirect. In BadCredentialsException cases return null and show error message.
     */
    @Override
    public String loginUser() {
        try{
            prepareUser();
            return "rooms?faces-redirect=true";
        } catch (BadCredentialsException e){
            LOG.warn("Host: " + ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteHost() + " | Authentication by user was failed. More: " + e);
            return null;
        }
    }

    /**
     * Prepares admin for login to system.
     * @return URL for redirect. In BadCredentialsException cases return null and show error message.
     */
    @Override
    public String loginAdmin() {
        try{
            prepareUser();
            return "admin?faces-redirect=true";
        } catch (BadCredentialsException e){
            LOG.warn("Host: " + ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteHost() + " | Authentication by admin was failed. More: " + e);
            return null;
        }
    }

    /**
     * Invalidate current session and logout user from system.
     * @return main page URL for redirect.
     */
    @Override
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        SecurityContextHolder.getContext().setAuthentication(null);
        return "index?faces-redirect=true";
    }

    /**
     * @see tk.jviewer.controller.LoginController#pageRedirect(String)
     * Cancels redirect, if no one rooms was selected.
     */
    @Override
    public String pageRedirect(String page){
        if(roomModel.getCurrentRoom() == null || roomModel.getCurrentRoom().isEmpty()){
            return null;
        }
        userModel.setCurrentRoom(roomModel.getCurrentRoom());
        return page + "?faces-redirect=true";
    }

    /**
     * Prepares any type of user for login to system.
     */
    private void prepareUser(){
        authentication();
        roomModel.refreshRooms();
        if (!roomModel.getRooms().isEmpty()) {
            roomModel.setCurrentRoom(roomModel.getRooms().get(0));
        } else {
            roomModel.setCurrentRoom("");
        }
    }

    /**
     * User authentication.
     */
    private void authentication() {
        Authentication request = new UsernamePasswordAuthenticationToken(userModel.getName(), userModel.getPassword());
        Authentication result = authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);
    }

    //
    // Setters for Dependency Injection.
    //

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
    }
}
