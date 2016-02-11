package tk.jviewer.tag;

import tk.jviewer.business.api.NewsService;
import tk.jviewer.business.model.NewsEntity;
import tk.jviewer.model.JViewerUriPath;
import tk.jviewer.security.SecurityService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * News List Tag backing bean.
 */
public class NewsListTag {

    private final List<NewsEntity> availableNews = new ArrayList<>();

    private NewsService newsService;

    @PostConstruct
    public void init() {
        List<NewsEntity> news = newsService.getNews();
        Collections.sort(news, (o1, o2) -> o1.getDate().getTime() < o2.getDate().getTime() ? 1 : -1);
        availableNews.addAll(news.stream().collect(Collectors.toList()));
    }

    /**
     * @see NewsService#getNews()
     */
    public List<NewsEntity> getNews() {
        return availableNews;
    }

    /**
     * @return index page for the navigation.
     * @see NewsService#deleteNews(NewsEntity)
     */
    public String deleteNews(NewsEntity news) {
        newsService.deleteNews(news);
        return JViewerUriPath.INDEX_PAGE.getJsfUri();
    }

    /**
     * Returns true if news update is allowed for the current user.
     *
     * @return see description.
     */
    public boolean isNewsUpdateAvailable() {
        return SecurityService.isAdmin();
    }

    //
    // Dependency Injection
    //

    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
}
