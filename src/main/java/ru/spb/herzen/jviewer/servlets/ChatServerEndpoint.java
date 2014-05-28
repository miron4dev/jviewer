package ru.spb.herzen.jviewer.servlets;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 * Server-side implementation of chat.
 * Based on WebSocket protocol.
 * @author Evgeny Mironenko
 */
@ServerEndpoint(value = "/chat")
public class ChatServerEndpoint {

    @OnMessage
    public String sendMessage(String msg) {
        return msg;
    }
}
