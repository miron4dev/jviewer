package tk.jviewer.tag;

import tk.jviewer.business.api.NewsService;
import tk.jviewer.business.model.NewsEntity;
import tk.jviewer.model.JViewerUriPath;
import tk.jviewer.security.SecurityService;

import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Add News Tag backing bean.
 */
public class AddNewsTag {

    private String topic;
    private String text;

    private NewsService newsService;

    /**
     * @see NewsService#addNews(NewsEntity)
     */
    public void submit() throws IOException {
        newsService.addNews(new NewsEntity(topic, text, getUsername()));
        FacesContext.getCurrentInstance().getExternalContext().redirect(JViewerUriPath.INDEX_PAGE.getUri());
    }

    /**
     * @see SecurityService#getUsername()
     */
    private String getUsername() {
        return SecurityService.getUsername();
    }

    /**
     * Returns a topic of the news.
     *
     * @return see description.
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Returns a text of the news.
     *
     * @return see description.
     */
    public String getText() {
        return text;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setText(String text) {
        this.text = text;
    }

    //
    // Dependency Injection
    //

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
