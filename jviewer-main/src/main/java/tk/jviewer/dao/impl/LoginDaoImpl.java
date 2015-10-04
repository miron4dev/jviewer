package tk.jviewer.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.LoginDao;
import tk.jviewer.profile.UserProfile;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Login Data Access Object implementation.
 * @author Evgeny Mironenko
 */
public class LoginDaoImpl extends JdbcDaoSupport implements LoginDao {

    @Override
    public UserProfile getData(String username) {
        return getJdbcTemplate().queryForObject("select * from users where username = ?", new Object[]{username},
                new UserProfileRowMapper());
    }

    private static class UserProfileRowMapper implements RowMapper<UserProfile> {

        private enum Columns {
            ID("id"),
            USER_NAME("username"),
            PASSWORD("password"),
            ROLE("role"),
            DEPARTMENT("faculty"),
            ENABLED("enabled"),
            FIRST_NAME("first_name"),
            LAST_NAME("last_name");

            private String columnName;

            Columns(String columnName) {
                this.columnName = columnName;
            }
        }

        @Override
        public UserProfile mapRow(ResultSet resultSet, int i) throws SQLException {
            UserProfile userProfile = new UserProfile();
            userProfile.setId(resultSet.getInt(Columns.ID.columnName));
            userProfile.setName(resultSet.getString(Columns.USER_NAME.columnName));
            userProfile.setPassword(resultSet.getString(Columns.PASSWORD.columnName));
            userProfile.setRole(resultSet.getString(Columns.ROLE.columnName));
            userProfile.setFaculty(resultSet.getString(Columns.DEPARTMENT.columnName));
            userProfile.setEnabled(resultSet.getBoolean(Columns.ENABLED.columnName));
            userProfile.setFirstName(resultSet.getString(Columns.FIRST_NAME.columnName));
            userProfile.setLastName(resultSet.getString(Columns.LAST_NAME.columnName));
            return userProfile;
        }
    }
}
