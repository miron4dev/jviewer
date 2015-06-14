package tk.jviewer.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.ResourceDao;

import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link ResourceDao}.
 * @author Evgeny Mironenko
 */
public class ResourceDaoImpl extends JdbcDaoSupport implements ResourceDao {

    @Override
    public List<Map<String, Object>> getResources() throws EmptyResultDataAccessException {
        return getJdbcTemplate().queryForList("select locale, key, value from localization");
    }
}
