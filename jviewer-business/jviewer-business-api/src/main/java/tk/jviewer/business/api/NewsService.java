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
}
