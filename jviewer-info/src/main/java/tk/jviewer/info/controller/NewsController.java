package tk.jviewer.info.controller;

import tk.jviewer.info.service.NewsService;
import tk.jviewer.info.model.NewsModel;
import tk.jviewer.info.model.UserModel;

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
    private NewsModel newsModel;
    private UserModel userModel;

    /**
     * @see NewsService#getNews()
     */
    public List<NewsModel> getNews() {
        List<NewsModel> news = manager.getNews();
        Collections.reverse(news);
        return news;
    }

    /**
     * @see NewsService#addNews(String, String, String)
     */
    public void addNews() throws IOException {
        manager.addNews(newsModel.getTopic(), newsModel.getText(), userModel.getName());
        newsModel.setTopic("");
        newsModel.setText("");
        FacesContext.getCurrentInstance().getExternalContext().redirect(NEWS_PAGE);
    }

    /**
     * @see NewsService#editNews(Integer, String, String, String)
     */
    public void editNews() throws IOException {
        manager.editNews(newsModel.getId(), newsModel.getTopic(), newsModel.getText(), userModel.getName());
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

    public void setNewsModel(NewsModel newsModel) {
        this.newsModel = newsModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
