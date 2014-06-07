package ru.spb.herzen.jviewer.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Evgeny Mironenko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao-applicationContext.xml")
public class ValidationDaoComplexTest {

    @Autowired
    private ValidationDao validationDao;

    @Test
    public void testCheckUser_success() throws Exception {
        validationDao.checkUser("Evgeny Mironenko");
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testCheckUser_fail() throws Exception {
        validationDao.checkUser("Test Account");
    }

    @Test
    public void testGetUserPassword() throws Exception {
        assertEquals(validationDao.getUserPassword("Evgeny Mironenko"), "qwerty");
    }
}
