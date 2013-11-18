package ru.spb.herzen.jviewer.controller;

import ru.spb.herzen.jviewer.model.DisplayModel;

import java.util.regex.Pattern;

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
        while(documentTagExist()){
            input = input.replace("document.write", "document.getElementById('displayForm:output').innerHTML = document.getElementById('displayForm:output').innerHTML + ");
        }
        displayModel.setOutput(input);
    }

    @Override
    public void updateInput() {
        input = displayModel.getInput();
    }

    @Override
    public void clearIO() {
        displayModel.setInput("");
        displayModel.setOutput("");
    }

    @Override
    public boolean documentTagExist() {
        return input.contains("document.write");
    }

    public void setDisplayModel(DisplayModel displayModel) {
        this.displayModel = displayModel;
    }
}
