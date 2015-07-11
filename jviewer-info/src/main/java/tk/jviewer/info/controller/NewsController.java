package tk.jviewer.info.controller;

import tk.jviewer.info.service.NewsService;
import tk.jviewer.info.model.News;
import tk.jviewer.info.model.UserProfile;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Controller the order to work with news.
 */
public class NewsController {

    private static final String NEWS_PAGE = "news.xhtml";

    private NewsService manager;
    private News news;
    private UserProfile userProfile;

    /**
     * @see NewsService#getNews()
     */
    public List<News> getNews() {
        List<News> news = manager.getNews();
        Collections.reverse(news);
        return news;
    }

    /**
     * @see NewsService#addNews(String, String, String)
     */
    public void addNews() throws IOException {
        manager.addNews(news.getTopic(), news.getText(), userProfile.getName());
        news.setTopic("");
        news.setText("");
        FacesContext.getCurrentInstance().getExternalContext().redirect(NEWS_PAGE);
    }

    /**
     * @see NewsService#editNews(Integer, String, String, String)
     */
    public void editNews() throws IOException {
        manager.editNews(news.getId(), news.getTopic(), news.getText(), userProfile.getName());
        FacesContext.getCurrentInstance().getExternalContext().redirect(NEWS_PAGE);
    }

    /**
     * @see NewsService#deleteNews(Integer)
     */
    public void deleteNews(Integer id) throws IOException {
        manager.deleteNews(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect(NEWS_PAGE);
    }

    //
    // Dependency Injection
    //

    public void setManager(NewsService manager) {
        this.manager = manager;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
