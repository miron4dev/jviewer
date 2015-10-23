package tk.jviewer.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tk.jviewer.entity.UserEntity;
import tk.jviewer.profile.UserProfile;
import tk.jviewer.repository.UserRepository;
import tk.jviewer.service.LoginService;

import javax.persistence.NoResultException;

/**
 * Login service implementation.
 * @author Evgeny Mironenko
 */
public class LoginServiceImpl implements LoginService {

    private UserRepository repository;
    private BCryptPasswordEncoder encoder;

    /**
     * {@inheritDoc}
     * If authentication was failed, returns null.
     */
    @Override
    public UserProfile getData(String username, String password) {
        try{
            UserEntity userEntity = repository.getUser(username);
            if(encoder.matches(password, userEntity.getPassword())){
                return mapToProfile(userEntity);
            }
            return null;
        } catch(NoResultException e){
            return null;
        }
    }

    private UserProfile mapToProfile(UserEntity entity) {
        UserProfile profile = new UserProfile();
        profile.setName(entity.getUsername());
        profile.setRole(entity.getRole());
        return profile;
    }

    //
    // Setters for Dependency Injection.
    //

    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }
}
