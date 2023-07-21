package mareczek100.musiccontests.infrastructure.database.repository.jpaRepository;

import mareczek100.musiccontests.infrastructure.database.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleJpaRepository extends JpaRepository<RoleEntity, String> {


}
