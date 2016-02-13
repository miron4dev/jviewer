package tk.jviewer.tag;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import tk.jviewer.business.api.NewsService;
import tk.jviewer.business.model.NewsEntity;
import tk.jviewer.model.JViewerUriPath;
import tk.jviewer.model.EditNewsManagedBean;
import tk.jviewer.security.SecurityService;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static tk.jviewer.model.JViewerUriPath.NEWS_PAGE;

/**
 * News List Tag backing bean.
 */
public class NewsListTag {

    private final List<NewsEntity> availableNews = new ArrayList<>();

    private NewsService newsService;
    private EditNewsManagedBean editNewsManagedBean;

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
     * @see NewsService#deleteNews(NewsEntity)
     */
    public void deleteNews(NewsEntity news) throws IOException {
        newsService.deleteNews(news);
        FacesContext.getCurrentInstance().getExternalContext().redirect(JViewerUriPath.NEWS_PAGE.getJsfUri());
    }

    /**
     * Opens the Edit News dialog based on the specified selected news.
     *
     * @param news news for edition.
     */
    public void openEditDialog(NewsEntity news) {
        editNewsManagedBean.setSelectedNews(news);
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("contentHeight", "540px");
        options.put("height", "520px");
        RequestContext.getCurrentInstance().openDialog("editNewsDialog", options, null);
    }

    /**
     * On news update event.
     *
     * @param event select event.
     * @throws IOException if the redirect to index page was failed.
     */
    public void onNewsUpdate(SelectEvent event) throws IOException {
        NewsEntity news = (NewsEntity) event.getObject();
        newsService.updateNews(news);
        editNewsManagedBean.setSelectedNews(null);
        FacesContext.getCurrentInstance().getExternalContext().redirect(NEWS_PAGE.getUri());
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

    public void setEditNewsManagedBean(EditNewsManagedBean editNewsManagedBean) {
        this.editNewsManagedBean = editNewsManagedBean;
    }
}
