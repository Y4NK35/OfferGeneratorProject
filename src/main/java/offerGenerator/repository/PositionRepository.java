package offerGenerator.repository;

import offerGenerator.entity.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CrudRepository<Position,Long> {
}
