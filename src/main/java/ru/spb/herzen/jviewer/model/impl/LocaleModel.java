package ru.spb.herzen.jviewer.model.impl;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * Locale model implementation.
 * @author Evgeny Mironenko
 */
public class LocaleModel implements Serializable {

    private Locale currentLocale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    private List<String> facultyList;
    private Properties localeFile;

    /**
     * Generates faculty list from current *.properties file.
     */
    @PostConstruct
    public void init(){
        facultyList = new ArrayList<>();
        localeFile = new Properties();
        String fileName = loadFileName();
        try {
            InputStream fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            localeFile.load(fis);
        } catch (IOException | NullPointerException e) {
            //TODO: logging
            e.printStackTrace();
        }

        facultyList.add(localeFile.getProperty("ItFaculty"));
        facultyList.add(localeFile.getProperty("MathFaculty"));
        facultyList.add(localeFile.getProperty("ChildhoodFaculty"));
        facultyList.add(localeFile.getProperty("PsychoFaculty"));
        facultyList.add(localeFile.getProperty("FineArtsFaculty"));
        facultyList.add(localeFile.getProperty("LanguageFaculty"));
        facultyList.add(localeFile.getProperty("PedagogyFaculty"));
        facultyList.add(localeFile.getProperty("MusicFaculty"));
        facultyList.add(localeFile.getProperty("SocialFaculty"));
        facultyList.add(localeFile.getProperty("PhysicsFaculty"));
        facultyList.add(localeFile.getProperty("CultureFaculty"));
        facultyList.add(localeFile.getProperty("PhilosophyFaculty"));
        facultyList.add(localeFile.getProperty("ChemistryFaculty"));
        facultyList.add(localeFile.getProperty("RussianFaculty"));
        facultyList.add(localeFile.getProperty("TechnologyFaculty"));
        facultyList.add(localeFile.getProperty("GeoFaculty"));
        facultyList.add(localeFile.getProperty("LifeFaculty"));
        facultyList.add(localeFile.getProperty("ManagementFaculty"));
        facultyList.add(localeFile.getProperty("VyborgFaculty"));
        facultyList.add(localeFile.getProperty("EconomicsFaculty"));
        facultyList.add(localeFile.getProperty("LawFaculty"));
        facultyList.add(localeFile.getProperty("ElectiveFaculty"));
        facultyList.add(localeFile.getProperty("BiologyFaculty"));
        facultyList.add(localeFile.getProperty("PhilologyFaculty"));
    }

    /**
     * Switches to another locale.
     * @param locale locale name.
     * @return current URL with another locale.
     */
    public String switchLocale(String locale){
        currentLocale = new Locale(locale);
        init();

        return FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath() + "?faces-redirect=true";
    }

    /**
     * Loads the *.properties file name.
     * @return path to file
     */
    private String loadFileName(){
        if(currentLocale.equals(new Locale("en"))){
            return "locale/output/language.properties";
        }
        else {
            return "locale/output/language_ru.properties";
        }
    }

    //
    // Getters and setters.
    //

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public List<String> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<String> facultyList) {
        this.facultyList = facultyList;
    }

    public Properties getLocaleFile(){
        return localeFile;
    }
}

