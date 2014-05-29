package ru.spb.herzen.jviewer.model.impl;

import ru.spb.herzen.jviewer.model.DisplayModel;
import ru.spb.herzen.jviewer.model.SystemModel;

import java.io.Serializable;
import java.util.Map;

/**
 * System model implementation.
 * @author Evgeny Mironenko
 */
public class SystemModelImpl implements SystemModel {

    private Map<String, DisplayModel> currentState;

    /**
     * @see ru.spb.herzen.jviewer.model.SystemModel#loadCurrentScreen(String)
     */
    @Override
    public DisplayModel loadCurrentScreen(String room) {
        return currentState.get(room);
    }

    /**
     * @see ru.spb.herzen.jviewer.model.SystemModel#setCurrentState(java.util.Map)
     */
    @Override
    public void setCurrentState(Map<String, DisplayModel> currentState) {
        this.currentState = currentState;
    }

}
