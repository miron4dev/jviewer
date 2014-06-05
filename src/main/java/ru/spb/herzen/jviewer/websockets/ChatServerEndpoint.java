package ru.spb.herzen.jviewer.websockets;

import org.apache.log4j.Logger;

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
@ServerEndpoint(value = "/chat/{room}")
public class ChatServerEndpoint {

    private final Logger LOG = Logger.getLogger(ChatServerEndpoint.class);

    private final String ROOM = "room";

    @OnOpen
    public void open(final Session session, @PathParam(ROOM) final String room) {
        session.getUserProperties().put(ROOM, room);
    }

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
            LOG.error("CharServerEndpoint: error in attempt of sending message via websocket. More: " + e);
        }
        return msg;
    }
}
