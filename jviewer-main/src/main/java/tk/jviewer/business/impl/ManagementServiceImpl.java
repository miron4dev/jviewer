package tk.jviewer.business.impl;

import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.business.model.RoomEntity;
import tk.jviewer.business.api.ManagementService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implements {@link ManagementService}.
 */
public class ManagementServiceImpl implements ManagementService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<RoomEntity> getRooms() {
        return em.createQuery("SELECT e FROM RoomEntity e").getResultList();
    }

    @Override
    @Transactional
    public RoomEntity createRoom(String name, RoomEntity.Type type) {
        RoomEntity roomEntity = new RoomEntity(name, type);
        em.persist(roomEntity);
        return roomEntity;
    }

    @Override
    @Transactional
    public void deleteRoom(RoomEntity entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }
}
