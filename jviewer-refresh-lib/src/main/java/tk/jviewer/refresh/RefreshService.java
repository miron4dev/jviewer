package tk.jviewer.refresh;

import org.springframework.context.ApplicationContext;

/**
 * Refresh mechanism, which executes all necessary methods by request. Such approach allows to avoid redeploy of application.
 * @author Evgeny Mironenko
 */
public interface RefreshService {

    /**
     * Refreshes application.
     */
    void refresh();

    /**
     * Setter for the current instance of {@link ApplicationContext}.
     * @param context see description.
     */
    void setApplicationContext(ApplicationContext context);
}
