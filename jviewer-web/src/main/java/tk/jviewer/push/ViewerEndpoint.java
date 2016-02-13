package tk.jviewer.push;

import org.apache.log4j.Logger;
import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnClose;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;

/**
 * Viewer push endpoint.
 */
@PushEndpoint("/{room}")
@Singleton
public class ViewerEndpoint {

    private static final Logger logger = Logger.getLogger(ViewerEndpoint.class);

    @OnOpen
    public void onOpen(RemoteEndpoint endpoint) {
        if (logger.isDebugEnabled()) {
            logger.debug("Opening connection " + endpoint);
        }
    }

    @OnClose
    public void onClose(RemoteEndpoint endpoint) {
        if (logger.isDebugEnabled()) {
            logger.debug("Closing connection " + endpoint);
        }
    }

    @OnMessage(decoders = {MessageDecoder.class}, encoders = {MessageEncoder.class})
    public Message onMessage(Message message) {
        return message;
    }
}
