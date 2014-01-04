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

    @PostConstruct
    public void init(){
        facultyList = new ArrayList<String>();
        Properties props = new Properties();
        String fileName = loadFileName();
        try {
            FileInputStream fis = new FileInputStream(new File(this.getClass().getClassLoader().getResource(fileName).toURI()));
            props.load(fis);
        } catch (IOException | URISyntaxException | NullPointerException e) {
            //TODO logging
            e.printStackTrace();
        }

        facultyList.add(props.getProperty("ItFaculty"));
        facultyList.add(props.getProperty("MathFaculty"));
        facultyList.add(props.getProperty("ChildhoodFaculty"));
        facultyList.add(props.getProperty("PsychoFaculty"));
        facultyList.add(props.getProperty("FineArtsFaculty"));
        facultyList.add(props.getProperty("LanguageFaculty"));
        facultyList.add(props.getProperty("PedagogyFaculty"));
        facultyList.add(props.getProperty("MusicFaculty"));
        facultyList.add(props.getProperty("SocialFaculty"));
        facultyList.add(props.getProperty("PhysicsFaculty"));
        facultyList.add(props.getProperty("CultureFaculty"));
        facultyList.add(props.getProperty("PhilosophyFaculty"));
        facultyList.add(props.getProperty("ChemistryFaculty"));
        facultyList.add(props.getProperty("RussianFaculty"));
        facultyList.add(props.getProperty("TechnologyFaculty"));
        facultyList.add(props.getProperty("GeoFaculty"));
        facultyList.add(props.getProperty("LifeFaculty"));
        facultyList.add(props.getProperty("ManagementFaculty"));
        facultyList.add(props.getProperty("VyborgFaculty"));
        facultyList.add(props.getProperty("EconomicsFaculty"));
        facultyList.add(props.getProperty("LawFaculty"));
        facultyList.add(props.getProperty("ElectiveFaculty"));
        facultyList.add(props.getProperty("BiologyFaculty"));
        facultyList.add(props.getProperty("PhilologyFaculty"));
    }

    public List<String> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<String> facultyList) {
        this.facultyList = facultyList;
    }

    public String switchLocale(String locale){
        currentLocale = new Locale(locale);
        init();

        return FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath() + "?faces-redirect=true";
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    private String loadFileName(){
        if(currentLocale.equals(new Locale("en"))){
            return "locale/output/language.properties";
        }
        else {
            return "locale/output/language_ru.properties";
        }
    }
}
