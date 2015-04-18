package tk.jviewer.info.manager;

import tk.jviewer.info.dao.NewsDao;
import tk.jviewer.info.model.NewsModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link NewsManagerImpl}.
 */
public class NewsManagerImpl implements NewsManager {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYY-MM-DD HH:MM:SS");

    private NewsDao dao;

    @Override
    public List<NewsModel> getNews() {
        return dao.getNews();
    }

    @Override
    public void addNews(String topic, String text, String author) {
        dao.addNews(topic, text, author, DATE_FORMAT.format(new Date()));
    }

    @Override
    public void deleteNews(Integer id) {
        dao.deleteNews(id);
    }

    //
    // Dependency Injection
    //

    public void setDao(NewsDao dao) {
        this.dao = dao;
    }
}
