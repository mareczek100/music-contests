package mareczek100.musiccontests.business.dao;

import mareczek100.musiccontests.domain.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepositoryDAO {


    Teacher insertTeacher(Teacher teacher);

    List<Teacher> findAllTeachers();

    Optional<Teacher> findTeacherByPesel(String pesel);
}
