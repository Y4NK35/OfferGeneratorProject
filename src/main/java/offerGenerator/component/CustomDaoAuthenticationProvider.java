package offerGenerator.component;

import offerGenerator.service.implementation.JpaUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CustomDaoAuthenticationProvider implements AuthenticationProvider {
    public static final String USERNAME_CANNOT_BE_NULL = "Username cannot be null";
    public static final String PASSWORD_CAN_NOT_BE_NULL = "Password can not be null";
    public static final String WRONG_PASSWORD = "Wrong password";
    public static final String USER_IS_NOT_ENABLED_PLEASE_CONFIRM_EMAIL = "USER_IS_NOT_ENABLED_PLEASE_CONFIRM_EMAIL";
    public static final String ACCOUNT_IS_LOCKED = "ACCOUNT_IS_LOCKED";
    public static final String ACCOUNT_EXPIRED = "ACCOUNT_EXPIRED";
    @Autowired
    PasswordEncoder passwordEncoder;
    JpaUserDetailServiceImpl userDetailsService;
    @Autowired
    public CustomDaoAuthenticationProvider (JpaUserDetailServiceImpl userDetailsService){
        this.userDetailsService=userDetailsService;

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String name = authentication.getName();
        Object credentials = authentication.getCredentials();
        Assert.notNull(name, USERNAME_CANNOT_BE_NULL);
        Assert.notNull(credentials, PASSWORD_CAN_NOT_BE_NULL);
        if(!(credentials instanceof String)){
            throw new BadCredentialsException(WRONG_PASSWORD);
        }
        String password = credentials.toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        boolean passwordMatch = passwordEncoder.matches(password, userDetails.getPassword());
        if(!passwordMatch){
            throw new BadCredentialsException(WRONG_PASSWORD);
        }
        if(!(userDetails.isEnabled())){
            throw new DisabledException(USER_IS_NOT_ENABLED_PLEASE_CONFIRM_EMAIL);
        }
        if(!(userDetails.isAccountNonLocked())){
            throw new LockedException(ACCOUNT_IS_LOCKED);
        }
        if(!(userDetails.isAccountNonExpired())){
            throw new AccountExpiredException(ACCOUNT_EXPIRED);
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(name, password, userDetails.getAuthorities());
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
