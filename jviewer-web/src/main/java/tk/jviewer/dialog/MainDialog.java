package tk.jviewer.dialog;

import tk.jviewer.entity.NewsEntity;
import tk.jviewer.security.SecurityService;
import tk.jviewer.service.NewsService;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Main dialog backing bean.
 */
public class MainDialog implements Serializable {

    private static final long serialVersionUID = 8687311151966287392L;

    private NewsService newsService;

    /**
     * @see NewsService#getNews()
     */
    public List<NewsEntity> getNews() {
        List<NewsEntity> result = newsService.getNews();
        Collections.sort(result, (o1, o2) -> o1.getDate().getTime() < o2.getDate().getTime() ? 1 : -1);
        return result;
    }

    /**
     * @see SecurityService#isAuthenticated()
     */
    public boolean isAuthenticated() {
        return SecurityService.isAuthenticated();
    }

    /**
     * @see SecurityService#getUsername()
     */
    public String getUsername() {
        return SecurityService.getUsername();
    }

    /**
     * @see SecurityService#logout()
     */
    public String logout() {
        return SecurityService.logout();
    }

    //
    // Dependency Injection
    //

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
