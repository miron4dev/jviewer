package tk.jviewer.info.manager;

import tk.jviewer.info.dao.NewsDao;
import tk.jviewer.info.model.NewsModel;

import java.util.List;

/**
 * Implementation of {@link NewsManagerImpl}.
 */
public class NewsManagerImpl implements NewsManager {

    private NewsDao dao;

    @Override
    public List<NewsModel> getNews() {
        return dao.getNews();
    }

    public void setDao(NewsDao dao) {
        this.dao = dao;
    }
}
