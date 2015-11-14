package tk.jviewer.model;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.*;
import java.util.Locale;

/**
 * Contains the current locale.
 * @author Evgeny Mironenko
 */
public class LocaleManagedBean implements Serializable {

    private static final long serialVersionUID = 382454077519505058L;

    private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    /**
     * Switches language.
     * @param language language.
     */
    public void switchLanguage(String language) throws IOException {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public Locale getLocale() {
        return locale;
    }
}

