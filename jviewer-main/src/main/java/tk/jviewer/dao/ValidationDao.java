package tk.jviewer.dao;

/**
 * Validation Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface ValidationDao {

    /**
     * Checks, that user is available in database.
     * @param name name of user.
     */
    void checkUser(String name);

    /**
     * Gets user password from database.
     * @param name name of user.
     * @return password of user.
     */
    String getUserPassword(String name);
}
