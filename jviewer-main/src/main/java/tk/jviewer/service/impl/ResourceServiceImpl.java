package tk.jviewer.service.impl;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.springframework.dao.EmptyResultDataAccessException;
import tk.jviewer.dao.ResourceDao;
import tk.jviewer.refresh.Refresh;
import tk.jviewer.service.ResourceService;

import java.io.Serializable;
import java.util.*;

/**
 * Implementation of {@link ResourceService}. It's refreshable service.
 * @author Evgeny Mironenko
 */
@Refresh
public class ResourceServiceImpl implements ResourceService, Serializable {

    private static final long serialVersionUID = 4046286891272354949L;

    private MultiKeyMap<String, String> cache = new MultiKeyMap<>();
    private ResourceDao dao;

    @Refresh
    public void fillCache() {
        try {
            for (Map<String, Object> resource: dao.getResources()) {
                cache.put((String) resource.get("locale"), (String) resource.get("key"), (String) resource.get("value"));
            }
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException("Empty or invalid 'localization' table. Please check the data in your database.", e);
        }
    }

    @Override
    public Enumeration<String> getKeys(Locale locale) {
        Vector<String> keys = new Vector<>();
        for (MultiKey<? extends String> multiKey : cache.keySet()) {
            keys.add(multiKey.getKey(0));
        }
        return keys.elements();
    }

    @Override
    public String getValue(Locale locale, String key) {
        return cache.get(locale.getLanguage(), key);
    }

    public void setDao(ResourceDao dao) {
        this.dao = dao;
    }
}
