package offerGenerator.repository;

import offerGenerator.entity.UserInformation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserInformationRepository extends CrudRepository<UserInformation,Long> {
}
