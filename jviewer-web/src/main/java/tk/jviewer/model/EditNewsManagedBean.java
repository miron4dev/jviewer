package tk.jviewer.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import tk.jviewer.business.model.NewsEntity;

/**
 * Contains state of the Edit News dialog.
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class EditNewsManagedBean {

    private NewsEntity selectedNews;

    public NewsEntity getSelectedNews() {
        return selectedNews;
    }

    public void setSelectedNews(NewsEntity selectedNews) {
        this.selectedNews = selectedNews;
    }
}
