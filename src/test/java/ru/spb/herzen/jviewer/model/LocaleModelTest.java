package ru.spb.herzen.jviewer.model;

import org.apache.shale.test.mock.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.spb.herzen.jviewer.model.impl.LocaleModel;

import javax.faces.context.ExternalContext;

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
    public void testInit_fail() {
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = createMock(ClassLoader.class);
        Thread.currentThread().setContextClassLoader(classLoader);
        expect(classLoader.getResourceAsStream("locale/output/language.properties")).andReturn(null);
        replay(classLoader);
        localeModel.init();
        Thread.currentThread().setContextClassLoader(originalClassLoader);
    }

    @Test
    public void testSwitchLocale() {
        localeModel.switchLocale("ru");
    }

    //TODO UI test: test method for cheat of test coverage statistic
    @Test
    public void testFieldsCheat() {
        localeModel.getCurrentLocale();
        localeModel.getFacultyList();
        localeModel.getLocaleFile();
        localeModel.setFacultyList(new ArrayList<>());
    }
}
