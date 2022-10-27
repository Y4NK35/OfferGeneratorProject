package offerGenerator.service;

import offerGenerator.entity.User;
import org.springframework.stereotype.Service;

public interface SignUpService {
    User signUpUser (User user) throws Exception;
}
