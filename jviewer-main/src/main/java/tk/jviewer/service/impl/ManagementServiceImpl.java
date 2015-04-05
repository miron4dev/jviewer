package tk.jviewer.service.impl;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import tk.jviewer.dao.ManagementDao;
import tk.jviewer.service.ManagementService;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Management service implementation.
 * @author Evgeny Mironenko
 */
public class ManagementServiceImpl implements ManagementService {

    private static final Logger LOG = Logger.getLogger(ManagementServiceImpl.class);

    private ManagementDao managementDao;

    /**
     * @see tk.jviewer.service.ManagementService#createRoom(String, String)
     */
    @Override
    public boolean createRoom(String room, String password) {
        try{
            return managementDao.createRoom(room, password);
        } catch(DataAccessException e){
            LOG.error("Host: " + ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteHost()
                    + " | Failed to create room. More: " + e);
            return false;
        }
    }

    /**
     * @see tk.jviewer.service.ManagementService#removeRoom(String)
     */
    @Override
    public boolean removeRoom(String room) {
        try{
            return managementDao.removeRoom(room);
        } catch(DataAccessException e){
            LOG.error("Host: " + ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteHost()
                    + " | Failed to remove room. More: " + e);
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
