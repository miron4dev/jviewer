package tk.jviewer.business.impl;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.business.model.LocalizationEntity;
import tk.jviewer.refresh.Refresh;
import tk.jviewer.refresh.Refreshable;
import tk.jviewer.business.api.ResourceService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.*;

/**
 * Implementation of {@link ResourceService}. It's refreshable service.
 * @author Evgeny Mironenko
 */
@Refreshable
public class ResourceServiceImpl implements ResourceService, Serializable {

    private static final long serialVersionUID = 4046286891272354949L;

    private MultiKeyMap<String, String> cache = new MultiKeyMap<>();

    @PersistenceContext
    private EntityManager entityManager;

    @Refresh
    public void fillCache() {
        try {
            for (LocalizationEntity entity: getAllValues()) {
                cache.put(entity.getLocale(), entity.getKey(), entity.getValue());
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

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    private List<LocalizationEntity> getAllValues() {
        return entityManager.createQuery("SELECT e FROM LocalizationEntity e").getResultList();
    }
}
