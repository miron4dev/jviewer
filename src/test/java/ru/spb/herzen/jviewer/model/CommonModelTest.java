package ru.spb.herzen.jviewer.model;

import org.junit.Test;
import ru.spb.herzen.jviewer.model.impl.CommonModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Evgeny Mironenko
 */
public class CommonModelTest {

    @Test
    public void testEqualsString() {
        assertTrue(CommonModel.equalsString("test", "test"));
        assertFalse(CommonModel.equalsString(null, "test"));
        assertFalse(CommonModel.equalsString("test", "test2213123"));
    }

    @Test
    public void testPrivateConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = CommonModel.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        CommonModel cm = (CommonModel)constructor.newInstance();
        assertEquals(CommonModel.class, cm.getClass());
    }
}
