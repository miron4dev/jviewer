package tk.jviewer.dialog;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * About dialog backing bean.
 */
public class AboutDialog {

    private String name;
    private String email;
    private String text;

    public void feedback() {
        //TODO send feedback
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you for your feedback!", null));
        reset();
    }

    private void reset() {
        name = null;
        email = null;
        text = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
