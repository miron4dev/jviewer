package ru.spb.herzen.jviewer.service.impl;

import org.springframework.dao.DataAccessException;
import ru.spb.herzen.jviewer.dao.ManagementDao;
import ru.spb.herzen.jviewer.service.ManagementService;

/**
 * Management service implementation.
 * @author Evgeny Mironenko
 */
public class ManagementServiceImpl implements ManagementService {

    private ManagementDao managementDao;

    /**
     * @see ru.spb.herzen.jviewer.service.ManagementService#createRoom(String, String)
     */
    @Override
    public boolean createRoom(String room, String password) {
        try{
            return managementDao.createRoom(room, password);
        } catch(DataAccessException e){
            //TODO logging
            return false;
        }
    }

    /**
     * @see ru.spb.herzen.jviewer.service.ManagementService#removeRoom(String)
     */
    @Override
    public boolean removeRoom(String room) {
        try{
            return managementDao.removeRoom(room);
        } catch(DataAccessException e){
            //TODO logging
            return false;
        }
    }

    //
    // Setter for Dependency Injection.
    //

    public void setManagementDao(ManagementDao managementDao) {
        this.managementDao = managementDao;
    }
}
