package tk.jviewer.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.dao.ResourceDao;

import java.util.List;
import java.util.Map;

/**
 * Created by Evgeny Mironenko on 13.06.2015.
 */
public class ResourceDaoImpl extends JdbcDaoSupport implements ResourceDao {

    @Override
    public List<Map<String, Object>> getResources() throws EmptyResultDataAccessException {
        return getJdbcTemplate().queryForList("select locale, key, value from localization");
    }
}
