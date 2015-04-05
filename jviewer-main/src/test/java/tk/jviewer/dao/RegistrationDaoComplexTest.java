package tk.jviewer.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.security.SecureRandom;

import static org.junit.Assert.assertEquals;

/**
 * @author Evgeny Mironenko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao-applicationContext.xml")
public class RegistrationDaoComplexTest {

    @Autowired
    private RegistrationDao registrationDao;

    private final static String currentInvitationId = "1030011";

    @Test
    public void testRegProfile_success() throws Exception {
        registrationDao.regProfile(getRandomString(), getRandomString(), getRandomString(), getRandomString());
    }

    @Test(expected = DataAccessException.class)
    public void testRegProfile_fail() throws Exception {
        registrationDao.regProfile("Evgeny Mironenko", "blabla", "test_role" ,"IT");
    }

    @Test
    public void testGetInvitationID() throws Exception {
        assertEquals(registrationDao.getInvitationID(), currentInvitationId);
    }

    private String getRandomString(){
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}
