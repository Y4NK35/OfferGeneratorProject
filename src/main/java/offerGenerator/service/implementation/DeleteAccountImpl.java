package offerGenerator.service.implementation;

import offerGenerator.entity.User;
import offerGenerator.repository.UserRepository;
import offerGenerator.service.DeleteAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DeleteAccountImpl implements DeleteAccountService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public DeleteAccountImpl (PasswordEncoder passwordEncoder,UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public String deleteAccount(User user, String password) throws Exception {
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new Exception("Enter right password.");
        }
        userRepository.delete(user);
        return "Account deleted";
    }
}
