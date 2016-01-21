package tk.jviewer.bundle;

import org.springframework.web.context.ContextLoader;
import tk.jviewer.business.api.ResourceService;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Extension of {@link ResourceBundle}, which gets resources from the database.
 * @author Evgeny Mironenko
 */
public class DatabaseResourceBundle extends ResourceBundle {

    private final ResourceService service = (ResourceService) ContextLoader.getCurrentWebApplicationContext().getBean("resourceService");

    private Locale locale;

    public DatabaseResourceBundle() {
        this.locale = new Locale("ru");
    }

    public DatabaseResourceBundle(Locale locale) {
        this.locale = locale;
    }

    @Override
    protected Object handleGetObject(String key) {
        return service.getValue(locale, key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return service.getKeys(locale);
    }
}
