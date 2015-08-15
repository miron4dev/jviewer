package tk.jviewer.websockets;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Set;

/**
 * Server-side implementation of chat.
 * Based on WebSocket protocol.
 * @author Evgeny Mironenko
 */
@ServerEndpoint(value = "/chat/{room}")
public class ChatServerEndpoint {

    private static final Logger LOG = Logger.getLogger(ChatServerEndpoint.class);
    private static final String ROOM = "room";
    private static final Set<Session> SESSIONS = new HashSet<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(ROOM) String room) {
        session.getUserProperties().put(ROOM, room);
        SESSIONS.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        SESSIONS.remove(session);
    }

    /**
     * Sends the message trough websocket protocol.
     * @param msg message, which should be delivered.
     */
    @OnMessage
    public void sendMessage(Session session, String msg) {
        synchronized (SESSIONS) {
            String room = (String) session.getUserProperties().get(ROOM);
            for (Session s : SESSIONS) {
                if (s.isOpen() && room.equals(s.getUserProperties().get(ROOM))) {
                    s.getAsyncRemote().sendText(msg);
                }
            }
        }
    }

    @OnError
    public void logError(Throwable throwable) {
        LOG.error("Exception occurred during data transmission through Web Socket", throwable);
    }
}
