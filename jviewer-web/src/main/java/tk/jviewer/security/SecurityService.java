package tk.jviewer.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import tk.jviewer.business.model.UserEntity;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of Authentication service.
 *
 * @author Evgeny Mironenko
 */
public class SecurityService implements AuthenticationProvider {

    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    @PersistenceContext
    private EntityManager em;

    private BCryptPasswordEncoder encoder;

    @Override
    @Transactional(readOnly = true)
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserProfile user;
        GrantedAuthority authority;
        UserEntity userEntity = em.find(UserEntity.class, username);
        if (userEntity == null) {
            handleAuthenticationFailed(username);
            return null;
        }
        if (encoder.matches(password, userEntity.getPassword())) {
            authority = new SimpleGrantedAuthority(userEntity.getRole());
            List<Permission> permissions = ADMIN_ROLE.equals(userEntity.getRole()) ? Collections.singletonList(Permission.ADMIN) : new ArrayList<>();
            user = new UserProfile(userEntity.getUsername(), permissions);
        } else {
            handleAuthenticationFailed(username);
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, password, Collections.singletonList(authority));
    }

    private void handleAuthenticationFailed(String username) {
        throw new BadCredentialsException("User data is invalid! Name: "  + username);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * Returns true if authenticated user has specified permission.
     *
     * @param permission permissions.
     * @return see description.
     */
    public static boolean userHasPermission(Permission permission) {
        UserProfile profile = getCurrentProfile();
        return profile != null && profile.hasPermission(permission);
    }

    /**
     * Returns the name of authenticated user.
     *
     * @return see description.
     */
    public static String getUsername() {
        UserProfile profile = getCurrentProfile();
        return profile != null ? profile.getName() : StringUtils.EMPTY;
    }

    /**
     * Returns true if the user is authenticated.
     *
     * @return see description.
     */
    public static boolean isAuthenticated() {
        return getCurrentProfile() != null;
    }


    /**
     * Invalidates the current session and logs out
     *
     * @return main page URL for redirect.
     */
    public static String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        SecurityContextHolder.getContext().setAuthentication(null);
        return "index?faces-redirect=true";
    }

    /**
     * Returns the instance of authenticated user.
     *
     * @return see description.
     */
    private static UserProfile getCurrentProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserProfile) {
            return (UserProfile) principal;
        }
        return null;
    }

    //
    // Setters for Dependency Injection.
    //

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
