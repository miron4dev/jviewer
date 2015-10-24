package tk.jviewer.security;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.jviewer.entity.UserEntity;
import tk.jviewer.repository.UserRepository;

import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of Authentication service.
 * @author Evgeny Mironenko
 */
public class SecurityService implements AuthenticationProvider {

    private static final Logger logger = Logger.getLogger(SecurityService.class);
    private static final String ADMIN_ROLE = "ROLE_ADMIN";

    private UserRepository repository;
    private BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserProfile user = null;
        GrantedAuthority authority = null;
        try{
            UserEntity userEntity = repository.getUser(username);
            if(encoder.matches(password, userEntity.getPassword())){
                authority = new SimpleGrantedAuthority(userEntity.getRole());
                List<Permission> permissions = ADMIN_ROLE.equals(userEntity.getRole()) ? Arrays.asList(Permission.values()) : new ArrayList<>();
                user = new UserProfile(userEntity.getUsername(), permissions);
            }
        } catch(NoResultException e){
            logger.warn("Authentication was failed. Username: " + username);
        }

        if (user == null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("success", "Data is invalid.");
            throw new BadCredentialsException("Data is invalid.");
        }

        return new UsernamePasswordAuthenticationToken(user, password, Collections.singletonList(authority));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * Returns true if authenticated user has permission;
     * @param permission permissions.
     * @return see description.
     */
    public static boolean userHasPermission(Permission permission) {
        return getCurrentProfile().hasPermission(permission);
    }

    /**
     * Returns the name of authenticated user.
     * @return see description.
     */
    public static String getUsername() {
        return getCurrentProfile().getName();
    }

    /**
     * Returns the instance of authenticated user.
     * @return see description.
     */
    private static UserProfile getCurrentProfile() {
        return (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    //
    // Setters for Dependency Injection.
    //


    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
