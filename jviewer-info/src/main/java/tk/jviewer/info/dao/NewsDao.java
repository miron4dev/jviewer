package tk.jviewer.info.dao;

import tk.jviewer.info.model.News;

import java.util.Date;
import java.util.List;

/**
 * Dao layer for News.
 */
public interface NewsDao {

    /**
     * Returns the all news from the database.
     * @return see description.
     */
    List<News> getNews();

    /**
     * Adds the news to the database.
     * @param topic the news topic.
     * @param text the news text.
     * @param author the news author.
     * @param date the news date.
     */
    void addNews(String topic, String text, String author, Date date);

    /**
     * Edits the news in the database.
     * @param id the news id.
     * @param topic the news topic.
     * @param text the news text.
     * @param author the news author.
     * @param date the news date.
     */
    void editNews(Integer id, String topic, String text, String author, Date date);

    /**
     * Delete the news from the database.
     * @param id the news id.
     */
    void deleteNews(Integer id);
}
