package ru.spb.herzen.jviewer.model;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeny
 * Date: 1/12/14
 * Time: 1:32 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SystemModel {

    HashMap<String, DisplayModel> getCurrentState();
    void setCurrentState(HashMap<String, DisplayModel> currentState);
    DisplayModel getCurrentScreen(String room);

}
