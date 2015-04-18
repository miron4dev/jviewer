package tk.jviewer.info.manager;

import tk.jviewer.info.model.NewsModel;

import java.util.List;

/**
 * Manager the order to work with news.
 */
public interface NewsManager {

    /**
     * Returns the list of news.
     * @return see description.
     */
    List<NewsModel> getNews();

    /**
     * Adds the news.
     * @param topic the news topic.
     * @param text the news text.
     * @param author the news author.
     */
    void addNews(String topic, String text, String author);

    /**
     * Deletes the news by id.
     * @param id the news id.
     */
    void deleteNews(Integer id);
}
