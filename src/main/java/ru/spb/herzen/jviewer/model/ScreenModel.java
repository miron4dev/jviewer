package ru.spb.herzen.jviewer.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 10:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScreenModel implements DisplayModel, Serializable{

    private String input;
    private String output;

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String getOutput() {
        return output;
    }

    @Override
    public void setOutput(String output) {
        this.output = output;
    }
}
