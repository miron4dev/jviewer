package tk.jviewer.service;

import java.util.Enumeration;
import java.util.Locale;

/**
 * Created by Evgeny Mironenko on 13.06.2015.
 */
public interface ResourceService {

    Enumeration<String> getKeys(Locale locale);

    String getValue(Locale locale, String key);
}
