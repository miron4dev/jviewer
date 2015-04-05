package tk.jviewer.dao;

/**
 * Registration Data Access Object interface.
 * @author Evgeny Mironenko
 */
public interface RegistrationDao {

    /**
     * Adds new user to database.
     * @param name name of profile.
     * @param password password of profile.
     * @param role role of profile. Default is USER_ROLE.
     * @param faculty faculty of profile.
     */
    void regProfile(String name, String password, String role, String faculty);

    /**
     * Gets invitation ID for professor registration from database.
     * @return invitation ID.
     */
    String getInvitationID();
}
