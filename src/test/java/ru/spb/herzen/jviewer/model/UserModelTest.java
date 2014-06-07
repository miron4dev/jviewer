package ru.spb.herzen.jviewer.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.spb.herzen.jviewer.model.impl.UserModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * @author Evgeny Mironenko
 */
public class UserModelTest {

    private UserModel userModel;

    @Before
    public void init() {
        userModel = new UserModelImpl();
        userModel.setId(123);
        userModel.setName("test");
    }

    @After
    public void destroy() {
        userModel = null;
    }

    @Test
    public void testHashCode() {
        assertEquals(127, userModel.hashCode());
    }
}
