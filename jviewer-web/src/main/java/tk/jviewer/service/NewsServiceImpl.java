package tk.jviewer.service;

import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.entity.NewsEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implements {@link NewsService}.
 */
public class NewsServiceImpl implements NewsService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<NewsEntity> getNews() {
        return em.createQuery("SELECT e from NewsEntity e").getResultList();
    }
}
