package tk.jviewer.info.manager;

import tk.jviewer.info.model.NewsModel;

import java.util.List;

/**
 * Manager the order to work with news.
 */
public interface NewsManager {

    List<NewsModel> getNews();
}
