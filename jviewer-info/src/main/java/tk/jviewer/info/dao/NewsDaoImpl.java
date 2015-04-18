package tk.jviewer.info.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.info.model.NewsModel;

import java.util.List;

/**
 * Implementation of {@link NewsDao}.
 */
public class NewsDaoImpl extends JdbcDaoSupport implements NewsDao {

    @Override
    public List<NewsModel> getNews() {
        return getJdbcTemplate().query("select * from news", new BeanPropertyRowMapper<>(NewsModel.class));
    }
}
