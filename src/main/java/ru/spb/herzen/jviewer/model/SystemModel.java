package ru.spb.herzen.jviewer.model;

import java.io.Serializable;
import java.util.Map;

/**
 * System model interface.
 * @author Evgeny Mironenko
 */
public interface SystemModel extends Serializable {

    /**
     * Loads the current display model for showing.
     * @param room name of room
     * @return current display model
     */
    DisplayModel loadCurrentScreen(String room);

    /**
     * Sets the current state of the system model.
     * @param currentState current state of the system model
     */
    void setCurrentState(Map<String, DisplayModel> currentState);

}
