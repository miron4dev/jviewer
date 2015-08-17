package tk.jviewer.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.jviewer.model.UserModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Evgeny Mironenko
 */
@Ignore
public class LoginDaoComplexTest {

    @Autowired
    private LoginDao loginDao;

    @Test
    public void testGetData_success() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setId(1);
        userModel.setName("Evgeny Mironenko");
        userModel.setPassword("qwerty");
        userModel.setRole("ROLE_USER");
        userModel.setFaculty("Faculty of Information Technology");
        userModel.setEnabled(true);
        assertEquals(loginDao.getData("Evgeny Mironenko"), userModel);
    }

    @Test
    public void testGetData_fail() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setId(999);
        assertNotEquals(loginDao.getData("Evgeny Mironenko"), userModel);
    }
}
