package tk.jviewer.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import tk.jviewer.business.model.NewsEntity;

import java.io.Serializable;

/**
 * Contains state of the Edit News dialog.
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class EditNewsManagedBean implements Serializable {

    private static final long serialVersionUID = 1867357515786143343L;

    private NewsEntity selectedNews;

    public NewsEntity getSelectedNews() {
        return selectedNews;
    }

    public void setSelectedNews(NewsEntity selectedNews) {
        this.selectedNews = selectedNews;
    }
}
