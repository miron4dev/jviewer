package tk.jviewer.websockets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tk.jviewer.testutils.CommonUtil;

import javax.websocket.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.util.*;

import static org.easymock.EasyMock.createMock;

/**
 * @author Evgeny Mironenko
 */
public class ChatServerEndpointTest {

    private ChatServerEndpoint serverEndpoint;
    private Session session;

    @Before
    public void init() {
        serverEndpoint = new ChatServerEndpoint();
        session = new Session() {

            private long timeout = 0;

            @Override
            public WebSocketContainer getContainer() {return null;}
            @Override
            public void addMessageHandler(MessageHandler handler) throws IllegalStateException {}
            @Override
            public <T> void addMessageHandler(Class<T> aClass, MessageHandler.Whole<T> whole) {}
            @Override
            public <T> void addMessageHandler(Class<T> aClass, MessageHandler.Partial<T> partial) {}
            @Override
            public Set<MessageHandler> getMessageHandlers() {return null;}
            @Override
            public void removeMessageHandler(MessageHandler handler) {}
            @Override
            public String getProtocolVersion() {return null;}
            @Override
            public String getNegotiatedSubprotocol() {return null;}
            @Override
            public List<Extension> getNegotiatedExtensions() {return null;}
            @Override
            public boolean isSecure() {return false;}
            @Override
            public boolean isOpen() {
                return true;
            }
            @Override
            public long getMaxIdleTimeout() {
                return timeout;
            }
            @Override
            public void setMaxIdleTimeout(long milliseconds) {
                this.timeout = milliseconds;
            }
            @Override
            public void setMaxBinaryMessageBufferSize(int length) {}
            @Override
            public int getMaxBinaryMessageBufferSize() {return 0;}
            @Override
            public void setMaxTextMessageBufferSize(int length) {}
            @Override
            public int getMaxTextMessageBufferSize() {return 0;}
            @Override
            public RemoteEndpoint.Async getAsyncRemote() { return null;}
            @Override
            public RemoteEndpoint.Basic getBasicRemote() {
                return new RemoteEndpoint.Basic() {
                    @Override
                    public void sendText(String text) throws IOException {}
                    @Override
                    public void sendBinary(ByteBuffer data) throws IOException {}
                    @Override
                    public void sendText(String partialMessage, boolean isLast) throws IOException {}
                    @Override
                    public void sendBinary(ByteBuffer partialByte, boolean isLast) throws IOException {}
                    @Override
                    public OutputStream getSendStream() throws IOException {return null;}
                    @Override
                    public Writer getSendWriter() throws IOException {return null;}
                    @Override
                    public void sendObject(Object data) throws IOException, EncodeException {
                        if (getMaxIdleTimeout() != 0 ){
                            throw new IOException();
                        }
                    }
                    @Override
                    public void setBatchingAllowed(boolean allowed) throws IOException {}
                    @Override
                    public boolean getBatchingAllowed() {return false;}
                    @Override
                    public void flushBatch() throws IOException {}
                    @Override
                    public void sendPing(ByteBuffer applicationData) throws IOException, IllegalArgumentException {}
                    @Override
                    public void sendPong(ByteBuffer applicationData) throws IOException, IllegalArgumentException {}
                };
            }
            @Override
            public String getId() {return "1";}
            @Override
            public void close() throws IOException {}
            @Override
            public void close(CloseReason closeReason) throws IOException {}
            @Override
            public URI getRequestURI() {return null;}
            @Override
            public Map<String, List<String>> getRequestParameterMap() {return null;}
            @Override
            public String getQueryString() {return null;}
            @Override
            public Map<String, String> getPathParameters() {return null;}
            @Override
            public Map<String, Object> getUserProperties() {
                Map<String, Object> props = new HashMap<>();
                props.put("room", "testRoom");
                return props;
            }
            @Override
            public Principal getUserPrincipal() {return null;}
            @Override
            public Set<Session> getOpenSessions() {
                Session session = this;
                Set<Session> sessions = new HashSet<>();
                sessions.add(session);
                return sessions;
            }
        };
    }

    @After
    public void destroy() {
        serverEndpoint = null;
        session = null;
    }

    @Test
    public void testSendMessage_success() throws Exception {
        serverEndpoint.open(session, "testRoom");
        serverEndpoint.sendMessage(session, "testMessage");
    }

    @Test
    public void testSendMessage_fail() throws Exception {
        session.setMaxIdleTimeout(777);
        CommonUtil.replayLogging();
        serverEndpoint.sendMessage(session, "testMessage");
    }
}
