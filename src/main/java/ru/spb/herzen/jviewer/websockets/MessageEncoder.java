package ru.spb.herzen.jviewer.websockets;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * WebSocket message decoder.
 * @author Evgeny Mironenko
 */
public class MessageEncoder implements Encoder.Text<String> {

    @Override
    public String encode(String object) throws EncodeException {
        return object;
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
