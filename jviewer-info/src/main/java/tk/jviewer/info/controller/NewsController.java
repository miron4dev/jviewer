package tk.jviewer.info.controller;

import tk.jviewer.info.manager.NewsManager;
import tk.jviewer.info.model.NewsModel;
import tk.jviewer.info.model.UserModel;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Controller the order to work with news.
 */
public class NewsController {

    private static final String NEWS_PAGE = "news.xhtml";

    private NewsManager manager;
    private NewsModel newsModel;
    private UserModel userModel;

    /**
     * @see NewsManager#getNews()
     */
    public List<NewsModel> getNews() {
        List<NewsModel> news = manager.getNews();
        Collections.reverse(news);
        return news;
    }

    /**
     * @see NewsManager#addNews(String, String, String)
     */
    public void addNews() throws IOException {
        manager.addNews(newsModel.getTopic(), newsModel.getText(), userModel.getName());
        newsModel.setTopic("");
        newsModel.setText("");
        FacesContext.getCurrentInstance().getExternalContext().redirect(NEWS_PAGE);
    }

    /**
     * @see NewsManager#deleteNews(Integer)
     */
    public void deleteNews(Integer id) {
        manager.deleteNews(id);
    }

    //
    // Dependency Injection
    //

    public void setManager(NewsManager manager) {
        this.manager = manager;
    }

    public void setNewsModel(NewsModel newsModel) {
        this.newsModel = newsModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
