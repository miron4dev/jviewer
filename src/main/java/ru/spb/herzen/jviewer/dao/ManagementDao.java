package ru.spb.herzen.jviewer.dao;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeny
 * Date: 1/11/14
 * Time: 6:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ManagementDao {

    void createRoom(String name, String password);
    void removeRoom(String name);
}
