package tk.jviewer.refresh;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Provides the current instance of {@link ApplicationContext} for {@link RefreshService}.
 * @author Evgeny Mironenko
 */
public class ApplicationContextProvider implements ApplicationContextAware {

    private RefreshService refreshService;

    /**
     * Initiates refresh service mechanism after initialization.
     */
    public void init() {
        refreshService.refresh();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        refreshService.setApplicationContext(applicationContext);
    }

    public void setRefreshService(RefreshService refreshService) {
        this.refreshService = refreshService;
    }
}
