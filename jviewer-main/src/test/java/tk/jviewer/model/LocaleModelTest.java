package tk.jviewer.model;

import org.apache.shale.test.mock.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.faces.context.ExternalContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import static org.easymock.EasyMock.*;

/**
 * @author Evgeny Mironenko
 */
public class LocaleModelTest {

    private LocaleModel localeModel;

    @Before
    public void init() {
        MockFacesContext facesContext = new MockFacesContext();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Locale locale = new Locale("en");
        request.setLocale(locale);
        ExternalContext externalContext = new MockExternalContext(new MockServletContext(), request, new MockHttpServletResponse());
        facesContext.setExternalContext(externalContext);
        localeModel = new LocaleModel();
    }

    @After
    public void destroy() {
        localeModel = null;
    }

    @Test
    public void testInit_success() {
        localeModel.init();
    }

    @Test
    public void testSwitchLocale() throws IOException {
        localeModel.switchLocale("ru");
    }
}
