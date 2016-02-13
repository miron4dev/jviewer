package tk.jviewer.push;

import org.primefaces.json.JSONObject;
import org.primefaces.push.Decoder;

/**
 * A Simple {@link Decoder} that decode a String into a {@link Message} object.
 */
public class MessageDecoder implements Decoder<String, Message> {

    @Override
    public Message decode(String s) {
        JSONObject json = new JSONObject(s);
        return new Message(json.optString("input"), json.optString("output"));
    }
}
