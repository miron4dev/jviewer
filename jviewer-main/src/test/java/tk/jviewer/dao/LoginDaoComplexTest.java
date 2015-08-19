package tk.jviewer.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.jviewer.profile.UserProfile;

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
        UserProfile userProfile = new UserProfile();
        userProfile.setId(1);
        userProfile.setName("Evgeny Mironenko");
        userProfile.setPassword("qwerty");
        userProfile.setRole("ROLE_USER");
        userProfile.setFaculty("Faculty of Information Technology");
        userProfile.setEnabled(true);
        assertEquals(loginDao.getData("Evgeny Mironenko"), userProfile);
    }

    @Test
    public void testGetData_fail() throws Exception {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(999);
        assertNotEquals(loginDao.getData("Evgeny Mironenko"), userProfile);
    }
}
