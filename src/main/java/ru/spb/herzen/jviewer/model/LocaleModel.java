package ru.spb.herzen.jviewer.model;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeny
 * Date: 1/2/14
 * Time: 4:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class LocaleModel implements Serializable {

    private Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    private List<String> facultyList;
    private Properties localeFile;

    @PostConstruct
    public void init(){
        facultyList = new ArrayList<>();
        localeFile = new Properties();
        String fileName = loadFileName();
        try {
            FileInputStream fis = new FileInputStream(new File(this.getClass().getClassLoader().getResource(fileName).toURI()));
            localeFile.load(fis);
        } catch (IOException | URISyntaxException | NullPointerException e) {
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

    public String switchLocale(String locale){
        currentLocale = new Locale(locale);
        init();

        return FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath() + "?faces-redirect=true";
    }

    private String loadFileName(){
        if(currentLocale.equals(new Locale("en"))){
            return "locale/output/language.properties";
        }
        else {
            return "locale/output/language_ru.properties";
        }
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

    public Properties getLocaleFile(){
        return localeFile;
    }
}
