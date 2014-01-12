package ru.spb.herzen.jviewer.service;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeny
 * Date: 1/11/14
 * Time: 6:58 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ManagementService {

    boolean createRoom(String room, String password);
    boolean removeRoom(String room);
}
