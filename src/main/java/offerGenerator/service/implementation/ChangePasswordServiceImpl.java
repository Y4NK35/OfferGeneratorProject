package offerGenerator.service.implementation;

import offerGenerator.entity.User;
import offerGenerator.repository.UserRepository;
import offerGenerator.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {
    public static final String PASSWORD_CAN_NOT_BE_NULL = "Password can not be null";
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    @Autowired
    public ChangePasswordServiceImpl (PasswordEncoder passwordEncoder,UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public String  changePassword(User user, String newPassword, String password) throws Exception {
        Assert.notNull(newPassword, PASSWORD_CAN_NOT_BE_NULL);
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new Exception("Enter right password.");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return "Password changed.";
    }
}
