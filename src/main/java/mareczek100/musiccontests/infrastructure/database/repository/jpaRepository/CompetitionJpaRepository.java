package mareczek100.musiccontests.infrastructure.database.repository.jpaRepository;

import mareczek100.musiccontests.infrastructure.database.entity.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionJpaRepository extends JpaRepository<CompetitionEntity, String> {

}
