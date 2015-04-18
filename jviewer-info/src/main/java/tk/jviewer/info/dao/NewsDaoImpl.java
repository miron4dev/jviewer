package tk.jviewer.info.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import tk.jviewer.info.model.NewsModel;

import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link NewsDao}.
 */
public class NewsDaoImpl extends JdbcDaoSupport implements NewsDao {

    @Override
    public List<NewsModel> getNews() {
        return getJdbcTemplate().query("SELECT * FROM news", new BeanPropertyRowMapper<>(NewsModel.class));
    }

    @Override
    public void addNews(String topic, String text, String author, String date) {
        getJdbcTemplate().update("INSERT INTO news (topic, text, date, author) VALUES (?, ?, ?, ?)", topic, text,
                date, author);
    }

    @Override
    public void deleteNews(Integer id) {
        getJdbcTemplate().update("DELETE FROM news WHERE id = ?", id);
    }
}
