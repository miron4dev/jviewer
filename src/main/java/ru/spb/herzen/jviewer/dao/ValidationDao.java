package ru.spb.herzen.jviewer.dao;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ValidationDao {

    boolean checkUser(String name);
    boolean validateData(String name, String password);
}
