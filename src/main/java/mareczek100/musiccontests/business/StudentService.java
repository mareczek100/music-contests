package mareczek100.musiccontests.business;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.StudentRepositoryDAO;
import mareczek100.musiccontests.domain.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepositoryDAO studentRepositoryDAO;
    private final RoleService roleService;

    @Transactional
    public Student insertStudent(Student student) {
        return studentRepositoryDAO.insertStudent(student);
    }

    @Transactional
    public List<Student> findAllStudents() {
        return studentRepositoryDAO.findAllStudents();
    }

    @Transactional
    public Optional<Student> findStudentByPesel(String pesel) {
        return studentRepositoryDAO.findStudentByPesel(pesel);
    }
}