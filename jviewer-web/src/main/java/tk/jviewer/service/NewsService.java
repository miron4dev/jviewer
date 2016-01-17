package tk.jviewer.service;

import tk.jviewer.entity.NewsEntity;

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
