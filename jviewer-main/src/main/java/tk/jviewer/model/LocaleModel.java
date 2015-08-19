package tk.jviewer.model;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Locale model implementation.
 * @author Evgeny Mironenko
 */
public class LocaleModel {

    private Locale currentLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    private List<String> facultyList;

    /**
     * Generates faculty list from current *.properties file.
     */
    @PostConstruct
    public void init() {
        facultyList = new ArrayList<>();
        facultyList.add("DUMMY");
    }

    /**
     * Switches to specified locale and refreshes the page.
     * @param locale locale name.
     */
    public void switchLocale(String locale) throws IOException {
        currentLocale = new Locale(locale);
        init();

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public List<String> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<String> facultyList) {
        this.facultyList = facultyList;
    }
}

