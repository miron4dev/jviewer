package tk.jviewer.service.impl;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.springframework.dao.EmptyResultDataAccessException;
import tk.jviewer.dao.ResourceDao;
import tk.jviewer.service.ResourceService;

import java.util.*;

/**
 * Created by Evgeny Mironenko on 13.06.2015.
 */
public class ResourceServiceImpl implements ResourceService {

    private MultiKeyMap<String, String> cache = new MultiKeyMap<>();

    private ResourceDao dao;

    @Override
    public Enumeration<String> getKeys(Locale locale) {
        if (cache.isEmpty()) {
            fillCache();
        }
        Vector<String> keys = new Vector<>();
        for (MultiKey<? extends String> multiKey : cache.keySet()) {
            keys.add(multiKey.getKey(0));
        }
        return keys.elements();
    }

    @Override
    public String getValue(Locale locale, String key) {
        if (cache.isEmpty()) {
            fillCache();
        }
        return cache.get(locale.getLanguage(), key);
    }

    private void fillCache() {
        try {
            for (Map<String, Object> resource: dao.getResources()) {
                cache.put((String) resource.get("locale"), (String) resource.get("key"), (String) resource.get("value"));
            }
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException("Empty or invalid 'localization' table. Please check the data in your database.", e);
        }
    }

    public void setDao(ResourceDao dao) {
        this.dao = dao;
    }
}
