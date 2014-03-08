package ru.spb.herzen.jviewer.common;

import org.easymock.EasyMock;

import javax.faces.context.FacesContext;
import java.lang.reflect.Method;

/**
 * @author Evgeny Mironenko
 */
public final class MockFacesContext {

    private MockFacesContext() {
    }

    public static FacesContext createMock() throws Exception {
        FacesContext facesContext = EasyMock.createMock(FacesContext.class);
        return changeCurrentInstance(facesContext);
    }

    public static FacesContext createNiceMock() throws Exception {
        FacesContext facesContext = EasyMock.createNiceMock(FacesContext.class);
        return changeCurrentInstance(facesContext);
    }

    public static FacesContext createStrictMock() throws Exception {
        FacesContext facesContext = EasyMock.createStrictMock(FacesContext.class);
        return changeCurrentInstance(facesContext);
    }

    private static FacesContext changeCurrentInstance(FacesContext facesContext) throws Exception {
        Class facesClass = FacesContext.class;
        Method facesMethod = facesClass.getDeclaredMethod("setCurrentInstance", facesClass);
        facesMethod.setAccessible(true);
        facesMethod.invoke(facesClass, facesContext);

        return facesContext;
    }
}
