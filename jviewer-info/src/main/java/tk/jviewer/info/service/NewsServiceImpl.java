package tk.jviewer.info.service;

import tk.jviewer.info.dao.NewsDao;
import tk.jviewer.info.model.News;

import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link NewsServiceImpl}.
 */
public class NewsServiceImpl implements NewsService {

    private NewsDao dao;

    @Override
    public List<News> getNews() {
        return dao.getNews();
    }

    @Override
    public void addNews(String topic, String text, String author) {
        dao.addNews(topic, text, author, new Date());
    }

    @Override
    public void editNews(Integer id, String topic, String text, String author) {
        dao.editNews(id, topic, text, author, new Date());
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
