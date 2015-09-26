package tk.jviewer.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.math.BigInteger;
import java.security.SecureRandom;

import static org.junit.Assert.assertEquals;

/**
 * @author Evgeny Mironenko
 */

@Ignore
public class RegistrationDaoComplexTest {

    @Autowired
    private RegistrationDao registrationDao;

    private final static String currentInvitationId = "1030011";

    @Test
    public void testRegProfile_success() throws Exception {
        registrationDao.createProfile(getRandomString(), getRandomString(), getRandomString(), getRandomString());
    }

    @Test(expected = DataAccessException.class)
    public void testRegProfile_fail() throws Exception {
        registrationDao.createProfile("Evgeny Mironenko", "blabla", "test_role" ,"IT");
    }

    @Test
    public void testGetInvitationID() throws Exception {
        assertEquals(registrationDao.getInvitationID(), currentInvitationId);
    }

    private String getRandomString(){
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}
