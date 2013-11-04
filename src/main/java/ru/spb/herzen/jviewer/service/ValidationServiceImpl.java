package ru.spb.herzen.jviewer.service;

import ru.spb.herzen.jviewer.controller.LoginController;
import ru.spb.herzen.jviewer.controller.LoginControllerImpl;
import ru.spb.herzen.jviewer.controller.RegistrationController;
import ru.spb.herzen.jviewer.controller.RegistrationControllerImpl;
import ru.spb.herzen.jviewer.model.UserModel;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean checkUser(UserModel userModel, String formName) {
        return doChecking(userModel, formName);
    }

    private boolean doChecking(UserModel userModel, String formName) {
        boolean flag = true;
        FacesContext context = FacesContext.getCurrentInstance();
        if(!checkName(userModel.getName())){
            context.addMessage(formName + ":name", new FacesMessage("Full name should consist from first and last name," +
                    "and they should begin with a capital letter."));
            flag = false;
        }
        if(!checkPass(userModel.getPassword())){
            context.addMessage(formName + ":password", new FacesMessage("Password should be less than 6 characters."));
            flag = false;
        }

        if(formName.equals("registrationForm")){
            if(!comparePasswords(userModel.getPassword(), userModel.getTemp())){
                context.addMessage("registrationForm:password", new FacesMessage("Passwords are not equals."));
                flag = false;
            }
        }

        return flag;
    }

    private boolean checkName(String name){
        return validator("([A-Z]{1}[a-z]+ {1}[A-Z]{1}[a-z]+)", name);
    }

    private boolean checkPass(String password){
        return password.length() >= 6;
    }

    private boolean comparePasswords(String password, String rtPassword){
        return password.equals(rtPassword);
    }

    private boolean validator(String regex, String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
