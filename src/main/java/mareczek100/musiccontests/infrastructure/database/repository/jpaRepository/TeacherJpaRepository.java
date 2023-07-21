package mareczek100.musiccontests.infrastructure.database.repository.jpaRepository;


import mareczek100.musiccontests.infrastructure.database.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherJpaRepository extends JpaRepository<TeacherEntity, String> {


}
