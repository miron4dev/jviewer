package tk.jviewer.utils;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Evgeny Mironenko
 */
public class ObjectUtilsTest {

    @Test
    public void testEqualsString() {
        assertTrue(ObjectUtils.equalsString("test", "test"));
        assertFalse(ObjectUtils.equalsString(null, "test"));
        assertFalse(ObjectUtils.equalsString("test", "test2213123"));
    }

    @Test
    public void testHashCodeIncreasing() {
        assertEquals(14, ObjectUtils.hashCodeIncreasing(10, "test"));
        assertEquals(10, ObjectUtils.hashCodeIncreasing(10, null));
    }

    @Test
    public void testPrivateConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = ObjectUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        ObjectUtils cm = (ObjectUtils)constructor.newInstance();
        assertEquals(ObjectUtils.class, cm.getClass());
    }
}
