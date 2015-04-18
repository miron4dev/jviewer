package tk.jviewer.info.controller;

import tk.jviewer.info.manager.NewsManager;
import tk.jviewer.info.model.NewsModel;

import java.util.Collections;
import java.util.List;

/**
 * Controller the order to work with news.
 */
public class NewsController {

    private NewsManager manager;

    /**
     * Returns the list of news.
     * @return see description.
     */
    public List<NewsModel> getNews() {
        List<NewsModel> news = manager.getNews();
        Collections.reverse(news);
        return news;
    }

    //
    //


    public void setManager(NewsManager manager) {
        this.manager = manager;
    }
}
