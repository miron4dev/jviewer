package ru.spb.herzen.jviewer.controller;

import ru.spb.herzen.jviewer.model.DisplayModel;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 10:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScreenControllerImpl implements ScreenController {

    private DisplayModel displayModel;
    private String input;

    @Override
    public void executeCode() {
        updateInput();
        displayModel.setOutput(input);
    }

    @Override
    public void updateInput() {
        input = displayModel.getInput();
    }

    public void setDisplayModel(DisplayModel displayModel) {
        this.displayModel = displayModel;
    }
}
