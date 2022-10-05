package offerGenerator.service.implementation;

import offerGenerator.entity.User;
import offerGenerator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class JpaUserDetailServiceImpl implements UserDetailsService   {
    public static final String NO_USER_FOUND_WITH_THIS_USERNAME = "No user found with this username!";

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    UserRepository userRepository;
    public JpaUserDetailServiceImpl(){
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usernameOptional = userRepository.findByUsername(username);
        if(!usernameOptional.isPresent()){
            throw new UsernameNotFoundException(NO_USER_FOUND_WITH_THIS_USERNAME);
        }
        User user = usernameOptional.get();
        return user;

    }

}
