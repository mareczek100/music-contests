package mareczek100.musiccontests.business.dao;

import mareczek100.musiccontests.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepositoryDAO {


    Student insertStudent(Student student);

    List<Student> findAllStudents();

    Optional<Student> findStudentByPesel(String pesel);
}