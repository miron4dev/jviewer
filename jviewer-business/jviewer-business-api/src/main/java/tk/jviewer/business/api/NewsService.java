package tk.jviewer.business.api;

import tk.jviewer.business.model.NewsEntity;

import java.util.List;

/**
 * News CRUD service.
 */
public interface NewsService {

    /**
     * Returns the list of the news.
     *
     * @return see description.
     */
    List<NewsEntity> getNews();

    /**
     * Adds the new news.
     *
     * @param news news to be added.
     */
    void addNews(NewsEntity news);

    /**
     * Updates the specified news.
     *
     * @param news news for update.
     */
    void updateNews(NewsEntity news);

    /**
     * Deletes the specified news.
     *
     * @param news news for deletion.
     */
    void deleteNews(NewsEntity news);
}
