package ru.spb.herzen.jviewer.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeny
 * Date: 1/12/14
 * Time: 1:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class SystemModelImpl implements SystemModel, Serializable {

    private HashMap<String, DisplayModel> currentState;

    @Override
    public DisplayModel getCurrentScreen(String room) {
        return currentState.get(room);
    }

    @Override
    public HashMap<String, DisplayModel> getCurrentState() {
        return currentState;
    }

    @Override
    public void setCurrentState(HashMap<String, DisplayModel> currentState) {
        this.currentState = currentState;
    }

}
