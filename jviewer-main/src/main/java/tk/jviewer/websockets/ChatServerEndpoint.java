package tk.jviewer.websockets;

import org.apache.log4j.Logger;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Server-side implementation of chat.
 * Based on WebSocket protocol.
 * @author Evgeny Mironenko
 */
@ServerEndpoint(value = "/chat/{room}", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class ChatServerEndpoint {

    private static final Logger LOG = Logger.getLogger(ChatServerEndpoint.class);
    private static final String ROOM = "room";

    /**
     * Prepares websocket connection.
     * @param session of current user.
     * @param room of current user.
     */
    @OnOpen
    public void open(Session session, @PathParam(ROOM) String room) {
        session.getUserProperties().put(ROOM, room);
    }

    /**
     * Sends message by websocket protocol.
     * @param session of current user.
     * @param msg - message, which should be delivered.
     * @return msg. See param.
     */
    @OnMessage
    public String sendMessage(Session session, String msg) {
        String room = (String) session.getUserProperties().get(ROOM);
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen() && room.equals(s.getUserProperties().get(ROOM))) {
                    s.getBasicRemote().sendObject(msg);
                }
            }
        } catch (IOException | EncodeException e) {
            LOG.error("Host: " + ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteHost()
                    + " | Error in attempt of sending message via websocket. More: " + e);
        }
        return msg;
    }
}
