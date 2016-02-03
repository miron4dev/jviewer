package tk.jviewer.business.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.business.model.NewsEntity;
import tk.jviewer.business.api.NewsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implements {@link NewsService}.
 */
@Component("newsService")
@Transactional
public class NewsServiceImpl implements NewsService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<NewsEntity> getNews() {
        return em.createQuery("SELECT e from NewsEntity e").getResultList();
    }

    @Override
    public void addNews(NewsEntity news) {
        em.persist(news);
    }
}
