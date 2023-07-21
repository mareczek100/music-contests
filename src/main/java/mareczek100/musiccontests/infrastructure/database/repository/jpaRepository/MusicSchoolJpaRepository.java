package mareczek100.musiccontests.infrastructure.database.repository.jpaRepository;


import mareczek100.musiccontests.infrastructure.database.entity.MusicSchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicSchoolJpaRepository extends JpaRepository<MusicSchoolEntity, String> {


}
