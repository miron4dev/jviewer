package ru.spb.herzen.jviewer.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import ru.spb.herzen.jviewer.model.UserModel;

/**
 * Created with IntelliJ IDEA.
 * User: eugene
 * Date: 11/2/13
 * Time: 3:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationDaoImpl extends JdbcDaoSupport implements RegistrationDao {

    @Override
    public void regProfile(UserModel userModel) {
        getJdbcTemplate().update("insert into users (name, password, role, faculty) values (?, ?, ?, ?)", userModel.getName(), userModel.getPassword(),
                userModel.getRole(), userModel.getFaculty());
    }

    @Override
    public String getInvitationID() {
        return getJdbcTemplate().queryForObject("select invitationID from properties", String.class);
    }
}
