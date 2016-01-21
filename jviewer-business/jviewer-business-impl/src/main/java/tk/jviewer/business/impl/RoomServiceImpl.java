package tk.jviewer.business.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.business.model.RoomEntity;
import tk.jviewer.business.api.RoomService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implements {@link RoomService}.
 */
@Component("roomService")
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
