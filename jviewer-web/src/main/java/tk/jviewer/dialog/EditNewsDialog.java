package tk.jviewer.dialog;

import org.primefaces.context.RequestContext;
import tk.jviewer.business.model.NewsEntity;
import tk.jviewer.model.EditNewsManagedBean;
import tk.jviewer.security.SecurityService;
import tk.jviewer.tag.AddNewsTag;

import javax.annotation.PostConstruct;
import java.io.Serializable;

/**
 * Edit News dialog backing bean.
 */
public class EditNewsDialog extends AddNewsTag implements Serializable {

    private static final long serialVersionUID = 7944284926204581984L;

    private String topic;
    private String text;

    private EditNewsManagedBean managedBean;

    @PostConstruct
    public void init() {
        topic = getSelectedNews().getTopic();
        text = getSelectedNews().getText();
    }

    /**
     * Closes the dialog with a chosen news.
     */
    @Override
    public void submit() {
        RequestContext.getCurrentInstance().closeDialog(new NewsEntity(getSelectedNews().getId(), topic, text,
            SecurityService.getUsername()));
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    //
    // Helper methods
    //

    private NewsEntity getSelectedNews() {
        return managedBean.getSelectedNews();
    }

    //
    // Dependency Injection
    //

    public void setManagedBean(EditNewsManagedBean managedBean) {
        this.managedBean = managedBean;
    }
}
