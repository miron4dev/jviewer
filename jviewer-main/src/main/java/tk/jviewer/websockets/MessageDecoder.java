package tk.jviewer.websockets;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * WebSocket message decoder.
 * @author Evgeny Mironenko
 */
public class MessageDecoder implements Decoder.Text<String> {

    @Override
    public String decode(String s) throws DecodeException {
        return s;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
