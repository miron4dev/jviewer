package tk.jviewer.websockets;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Server-side implementation of viewer.
 * Based on WebSocket protocol.
 * @author Evgeny Mironenko
 */
@ServerEndpoint(value = "/chat/{room}")
public class WsViewer {

    private static final Logger LOG = Logger.getLogger(WsViewer.class);
    private static final String ROOM = "room";
    private static final Set<WsViewer> connections = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam(ROOM) String room) {
        session.getUserProperties().put(ROOM, room);
        this.session = session;
        connections.add(this);
    }

    @OnClose
    public void onClose() {
        connections.remove(this);
    }

    /**
     * Sends the message through web socket.
     * @param msg message, which should be delivered.
     */
    @OnMessage
    public void sendMessage(String msg) {
        broadcast(msg);
    }

    @OnError
    public void logError(Throwable throwable) {
        LOG.error("Exception occurred during data transmission through Web Socket ", throwable);
    }

    private void broadcast(String msg) {
        for (WsViewer client : connections) {
            try {
                synchronized (client) {
                    String currentRoom = (String) session.getUserProperties().get(ROOM);
                    if (currentRoom.equals(client.session.getUserProperties().get(ROOM))) {
                        client.session.getBasicRemote().sendText(msg);
                    }
                }
            } catch (IOException e) {
                LOG.error("Exception occurred during data transmission through Web Socket ", e);
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
            }
        }
    }
}
