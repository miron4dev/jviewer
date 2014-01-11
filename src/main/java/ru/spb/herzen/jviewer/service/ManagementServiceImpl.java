package ru.spb.herzen.jviewer.service;

import org.springframework.dao.DataAccessException;
import ru.spb.herzen.jviewer.dao.ManagementDao;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeny
 * Date: 1/11/14
 * Time: 6:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagementServiceImpl implements ManagementService {

    private ManagementDao managementDao;

    @Override
    public boolean createRoom(String room, String password) {
        try{
            managementDao.createRoom(room, password);
            return true;
        } catch(DataAccessException e){
            //TODO logging
            return false;
        }
    }

    @Override
    public boolean removeRoom(String room) {
        try{
            managementDao.removeRoom(room);
            return true;
        } catch(DataAccessException e){
            //TODO logging
            return false;
        }
    }

    public void setManagementDao(ManagementDao managementDao) {
        this.managementDao = managementDao;
    }
}
