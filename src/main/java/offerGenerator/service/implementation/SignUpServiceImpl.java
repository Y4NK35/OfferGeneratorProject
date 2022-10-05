package offerGenerator.service.implementation;

import offerGenerator.component.ConfirmationTokenGenerator;
import offerGenerator.component.mailer.SignUpMailer;
import offerGenerator.entity.Role;
import offerGenerator.entity.User;
import offerGenerator.repository.RoleRepository;
import offerGenerator.repository.UserRepository;
import offerGenerator.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@PropertySource("classpath:application.properties")
@Service
public class SignUpServiceImpl implements SignUpService {
    public static final String CAN_T_SIGN_UP_USER_IT_ALREADY_HAS_SET_ID = "Can`t sign up user, it already has set id";
    @Value("${token.length}")
    private int tokenLength;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private SignUpMailer signUpMailer;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public SignUpServiceImpl(RoleRepository roleRepository, UserRepository userRepository, SignUpMailer signUpMailer, PasswordEncoder passwordEncoder ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.signUpMailer = signUpMailer;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User signUpUser(User user) throws Exception {
        Assert.notNull(user.getIdUser(), CAN_T_SIGN_UP_USER_IT_ALREADY_HAS_SET_ID);
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            System.out.println("Username already taken");
            throw new Exception("Username is taken. Try login.");
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            System.out.println("Email is taken. Try login to account or use another email.");
            throw new Exception("Email is taken. Try login to account or use another email.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String token = ConfirmationTokenGenerator.getConfirmationToken(tokenLength);
        user.setConfirmationToken(token);
        Optional<Role> roleOptional = roleRepository.findByName("USER");
        if(roleOptional.isPresent()){
            user.getRoles().add(roleOptional.get());
        }
        User savedUser = userRepository.save(user);
        signUpMailer.sendConfirmationLink( user.getUsername(), user.getEmail(),token);
        System.out.println(user.getConfirmationToken());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return savedUser;
    }
}
