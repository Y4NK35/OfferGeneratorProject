package offerGenerator.repository;

import offerGenerator.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByConfirmationToken(String token);
    Optional<User> findByEmail(String mail);
}
