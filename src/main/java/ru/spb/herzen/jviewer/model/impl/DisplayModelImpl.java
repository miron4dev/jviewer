package ru.spb.herzen.jviewer.model.impl;

import ru.spb.herzen.jviewer.model.DisplayModel;

import java.io.Serializable;

/**
 * Display model implementation.
 * @author Evgeny Mironenko
 */
public class DisplayModelImpl implements DisplayModel, Serializable{

    private String input = "";
    private String output = "";

    /**
     * @see ru.spb.herzen.jviewer.model.DisplayModel#executeCode()
     * If "document.write" is exist on the input field, it should be replaced, because page will broken.
     */
    @Override
    public void executeCode() {
        updateInput();
        while(documentTagExist()){
            input = input.replace("document.write", "document.getElementById('displayForm:output').innerHTML = document.getElementById('displayForm:output').innerHTML + ");
        }
        output = input;
    }

    /**
     * @see ru.spb.herzen.jviewer.model.DisplayModel#updateInput()
     * This method is important for correct rendering of input field.
     */
    @Override
    public void updateInput() {
        input = getInput();
    }

    /**
     * @see ru.spb.herzen.jviewer.model.DisplayModel#clearIO()
     */
    @Override
    public void clearIO() {
        input = "";
        output = "";
    }

    /**
     * Checks input field on the "document.write" existence.
     * @return
     */
    private boolean documentTagExist() {
        return input.contains("document.write");
    }

    /**
     * @see ru.spb.herzen.jviewer.model.DisplayModel#getInput()
     */
    @Override
    public String getInput() {
        return input;
    }

    /**
     * @see ru.spb.herzen.jviewer.model.DisplayModel#setInput(String)
     */
    @Override
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * @see ru.spb.herzen.jviewer.model.DisplayModel#getOutput()
     */
    @Override
    public String getOutput() {
        return output;
    }

    /**
     * @see ru.spb.herzen.jviewer.model.DisplayModel#setOutput(String)
     */
    @Override
    public void setOutput(String output) {
        this.output = output;
    }
}
