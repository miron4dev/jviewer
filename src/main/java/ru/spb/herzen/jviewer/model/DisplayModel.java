package ru.spb.herzen.jviewer.model;

/**
 * Display model interface.
 * @author Evgeny Mironenko
 */
public interface DisplayModel {

    /**
     * Interprets current code.
     */
    void executeCode();

    /**
     * Updates the current input field.
     */
    void updateInput();

    /**
     * Clears input/output fields.
     */
    void clearIO();

    /**
     * Returns user input.
     * @return input.
     */
    String getInput();

    /**
     * Sets user input.
     * @param input user input.
     */
    void setInput(String input);

    /**
     * Returns user output.
     * @return output.
     */
    String getOutput();

    /**
     * Sets user output.
     * @param output user output.
     */
    void setOutput(String output);
}
