package tk.jviewer.repository;

import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Implementation of {@link UserRepository}.
 */
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void addUser(UserEntity user) {
        em.persist(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity getUser(String username) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<UserEntity> criteria = builder.createQuery(UserEntity.class);
        Root<UserEntity> u = criteria.from(UserEntity.class);
        return em.createQuery(criteria.select(u).where(builder.equal(u.get("username"), username))).getSingleResult();
    }
}
