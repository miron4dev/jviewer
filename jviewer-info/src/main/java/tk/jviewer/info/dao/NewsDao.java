package tk.jviewer.info.dao;

import tk.jviewer.info.model.NewsModel;

import java.util.List;

/**
 * Dao layer for News.
 */
public interface NewsDao {

    /**
     * Returns the all news from the database.
     * @return see description.
     */
    List<NewsModel> getNews();
}
