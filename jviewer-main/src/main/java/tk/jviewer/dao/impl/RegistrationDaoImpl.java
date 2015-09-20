package tk.jviewer.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.RegistrationDao;

/**
 * Registration Data Access Object implementation.
 * @author Evgeny Mironenko
 */
public class RegistrationDaoImpl extends JdbcDaoSupport implements RegistrationDao {

    /**
     * @see tk.jviewer.dao.RegistrationDao#regProfile(String, String, String, String)
     */
    @Override
    public void regProfile(String username, String password, String role, String faculty) {
        getJdbcTemplate().update("insert into users (username, password, role, faculty, first_name, last_name) values (?, ?, ?, ?, ?, ?)", username, password,
                role, faculty, "TODO", "TODO"); // TODO for EM
    }

    /**
     * @see tk.jviewer.dao.RegistrationDao#getInvitationID()
     */
    @Override
    public String getInvitationID() {
        return getJdbcTemplate().queryForObject("select invitationID from properties", String.class);
    }
}
