package ru.spb.herzen.jviewer.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * System model implementation.
 * @author Evgeny Mironenko
 */
public class SystemModelImpl implements SystemModel, Serializable {

    private HashMap<String, DisplayModel> currentState;

    /**
     * @see ru.spb.herzen.jviewer.model.SystemModel#loadCurrentScreen(String)
     */
    @Override
    public DisplayModel loadCurrentScreen(String room) {
        return currentState.get(room);
    }

    /**
     * @see ru.spb.herzen.jviewer.model.SystemModel#setCurrentState(java.util.HashMap)
     */
    @Override
    public void setCurrentState(HashMap<String, DisplayModel> currentState) {
        this.currentState = currentState;
    }

}
