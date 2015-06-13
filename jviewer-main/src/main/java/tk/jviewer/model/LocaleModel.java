package tk.jviewer.model;

import tk.jviewer.service.ResourceService;

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
public class LocaleModel implements Serializable {

    private Locale currentLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    private List<String> facultyList;

    // It is bad practice to refer from Model to Service, but I didn't find better solution to get the map of resources, which is located in the database.
    private ResourceService resourceService;

    /**
     * Generates faculty list from current *.properties file.
     */
    @PostConstruct
    public void init() {
        facultyList = new ArrayList<>();
        facultyList.add(resourceService.getValue(currentLocale, "J13"));
        facultyList.add(resourceService.getValue(currentLocale, "J14"));
        facultyList.add(resourceService.getValue(currentLocale, "J15"));
        facultyList.add(resourceService.getValue(currentLocale, "J24"));
        facultyList.add(resourceService.getValue(currentLocale, "J25"));
        facultyList.add(resourceService.getValue(currentLocale, "J26"));
        facultyList.add(resourceService.getValue(currentLocale, "J27"));
        facultyList.add(resourceService.getValue(currentLocale, "J28"));
        facultyList.add(resourceService.getValue(currentLocale, "J29"));
        facultyList.add(resourceService.getValue(currentLocale, "J30"));
        facultyList.add(resourceService.getValue(currentLocale, "J31"));
        facultyList.add(resourceService.getValue(currentLocale, "J32"));
        facultyList.add(resourceService.getValue(currentLocale, "J33"));
        facultyList.add(resourceService.getValue(currentLocale, "J34"));
        facultyList.add(resourceService.getValue(currentLocale, "J35"));
        facultyList.add(resourceService.getValue(currentLocale, "J36"));
        facultyList.add(resourceService.getValue(currentLocale, "J37"));
        facultyList.add(resourceService.getValue(currentLocale, "J38"));
        facultyList.add(resourceService.getValue(currentLocale, "J39"));
        facultyList.add(resourceService.getValue(currentLocale, "J40"));
        facultyList.add(resourceService.getValue(currentLocale, "J41"));
        facultyList.add(resourceService.getValue(currentLocale, "J42"));
        facultyList.add(resourceService.getValue(currentLocale, "J43"));
        facultyList.add(resourceService.getValue(currentLocale, "J44"));
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

    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}

