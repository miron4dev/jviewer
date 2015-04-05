package tk.jviewer.testutils;

import org.apache.shale.test.mock.MockFacesContext;
import org.apache.shale.test.mock.MockHttpServletRequest;

import javax.faces.context.ExternalContext;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

/**
 * @author Evgeny Mironenko
 */
public final class CommonUtil {

    public static void replayLogging() {
        MockFacesContext facesContext = new MockFacesContext();
        ExternalContext externalContext = createMock(ExternalContext.class);
        facesContext.setExternalContext(externalContext);
        expect(externalContext.getRequest()).andReturn(new MockHttpServletRequest());
        replay(externalContext);
    }
}
