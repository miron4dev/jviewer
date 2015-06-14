package tk.jviewer.service;

import java.util.Enumeration;
import java.util.Locale;

/**
 * Service for work with application resources.
 * @author Evgeny Mironenko
 */
public interface ResourceService {

    /**
     * Returns the all keys for the specified locale.
     * @param locale locale of resource.
     * @return see description.
     */
    Enumeration<String> getKeys(Locale locale);

    /**
     * Returns the value of the specified locale and key.
     * @param locale locale of resource.
     * @param key key of resource.
     * @return see description.
     */
    String getValue(Locale locale, String key);
}
