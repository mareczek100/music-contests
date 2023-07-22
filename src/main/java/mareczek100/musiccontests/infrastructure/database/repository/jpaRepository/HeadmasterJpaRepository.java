package mareczek100.musiccontests.infrastructure.database.repository.jpaRepository;


import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.infrastructure.database.entity.HeadmasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeadmasterJpaRepository extends JpaRepository<HeadmasterEntity, String> {

    Optional<Headmaster> findHeadmasterByEmail(String email);
}