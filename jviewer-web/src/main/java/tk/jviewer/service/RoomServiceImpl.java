package tk.jviewer.service;

import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.entity.RoomEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implements {@link RoomService}.
 */
public class RoomServiceImpl implements RoomService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<RoomEntity> getRooms() {
        return em.createQuery("SELECT e FROM RoomEntity e").getResultList();
    }
}
