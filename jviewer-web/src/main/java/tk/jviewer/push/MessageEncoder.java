package tk.jviewer.push;

import org.primefaces.json.JSONObject;
import org.primefaces.push.Encoder;

/**
 * A Simple {@link Encoder} that decode a {@link Message} into a simple JSON object.
 */
public class MessageEncoder implements Encoder<Message, String> {

    @Override
    public String encode(Message message) {
        return new JSONObject(message).toString();
    }
}
