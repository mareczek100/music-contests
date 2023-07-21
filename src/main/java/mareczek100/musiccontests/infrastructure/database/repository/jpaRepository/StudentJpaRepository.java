package mareczek100.musiccontests.infrastructure.database.repository.jpaRepository;

import mareczek100.musiccontests.infrastructure.database.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, String> {


}