package tk.jviewer.business.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.business.model.JViewerBusinessException;
import tk.jviewer.business.model.RoomEntity;
import tk.jviewer.business.api.RoomService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static tk.jviewer.business.model.JViewerBusinessException.ErrorCode.ROOM_ALREADY_EXIST;

/**
 * Implements {@link RoomService}.
 */
@Component
@Transactional
public class RoomServiceImpl implements RoomService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<RoomEntity> getRooms() {
        return em.createQuery("SELECT e FROM RoomEntity e").getResultList();
    }

    @Override
    public void deleteRoom(RoomEntity room) {
        em.remove(em.contains(room) ? room : em.merge(room));
    }

    @Override
    public void createRoom(String name, RoomEntity.Type type) {
        if (em.find(RoomEntity.class, name) != null) {
            throw new JViewerBusinessException(ROOM_ALREADY_EXIST);
        }
        em.persist(new RoomEntity(name, type));
    }
}
