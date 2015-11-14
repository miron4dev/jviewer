package tk.jviewer.push;

/**
 * Message representation.
 */
public class Message {

    private String input;
    private String output;

    public Message(String input) {
        this.input = input;
    }

    public Message(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}
