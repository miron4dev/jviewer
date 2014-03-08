package ru.spb.herzen.jviewer.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.spb.herzen.jviewer.model.UserModel;
import ru.spb.herzen.jviewer.model.impl.RequestModel;
import ru.spb.herzen.jviewer.model.impl.UserModelImpl;
import ru.spb.herzen.jviewer.service.impl.SecurityService;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Evgeny Mironenko
 */
public class SecurityServiceImplTest {

    private Authentication authentication;
    private SecurityService securityService;
    private LoginService loginService;
    private RequestModel requestModel;
    private UserModel userModel;
    private String role = "ROLE_USER";

    @Before
    public void init() throws Exception {
        securityService = new SecurityService();
        requestModel = new RequestModel();
        userModel = new UserModelImpl();
        userModel.setRole(role);
        loginService = createStrictMock(LoginService.class);
        authentication = createStrictMock(Authentication.class);
        securityService.setRequestModel(requestModel);
        securityService.setUserModel(userModel);
        securityService.setLoginService(loginService);
    }

    @After
    public void destroy() throws Exception {
        securityService = null;
        requestModel = null;
        userModel = null;
        loginService = null;
    }

    @Test
    public void testAuthenticate() throws Exception {
        String name = "user";
        String password = "password";
        expect(authentication.getName()).andReturn(name);
        expect(authentication.getCredentials()).andReturn(password);
        replay(authentication);
        expect(loginService.getData(requestModel)).andReturn(userModel);
        replay(loginService);
        assertEquals(securityService.authenticate(authentication), getAuthentication(name, password));
        verify(authentication);
        verify(loginService);
    }

    @Test
    public void testSupports() throws Exception {
        assertTrue(securityService.supports(SecurityService.class));
    }

    private Authentication getAuthentication(String name, String password) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return new UsernamePasswordAuthenticationToken(name, password, authorities);
    }
}
