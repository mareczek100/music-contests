package mareczek100.musiccontests.business;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.StudentRepositoryDAO;
import mareczek100.musiccontests.domain.MusicSchool;
import mareczek100.musiccontests.domain.Student;
import mareczek100.musiccontests.infrastructure.database.entity.security.MusicContestsPortalUserEntity;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;
import mareczek100.musiccontests.infrastructure.database.entity.security.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepositoryDAO studentRepositoryDAO;
    private final MusicSchoolService musicSchoolService;
    private final SecurityService securityService;

    @Transactional
    public Student insertStudent(Student student) {
        RoleEntity.RoleName studentRole = RoleEntity.RoleName.STUDENT;
        MusicContestsPortalUserEntity studentPortalUserEntity
                = securityService.insertRoleWhileCreateNewUser(student.email(), student.pesel(), studentRole);
        String encodedPesel = studentPortalUserEntity.getPassword();
        MusicSchool musicSchool = student.musicSchool();

        if (!musicSchool.musicSchoolId().isEmpty()){
            return studentRepositoryDAO.insertStudent(student
                    .withMusicSchool(musicSchool)
                    .withPesel(encodedPesel));
        }
        MusicSchool insertedMusicSchool = musicSchoolService.insertMusicSchool(musicSchool);
        return studentRepositoryDAO.insertStudent(student
                .withMusicSchool(insertedMusicSchool)
                .withPesel(encodedPesel));
    }

    @Transactional
    public List<Student> findAllStudents() {
        return studentRepositoryDAO.findAllStudents();
    }

    @Transactional
    public Student findStudentByPesel(String pesel) {
        return studentRepositoryDAO.findStudentByPesel(pesel).orElseThrow(
                () -> new RuntimeException("Student with pesel [%s] doesn't exist!"
                        .formatted(pesel))
        );
    }
    @Transactional
    public Student findStudentById(String studentId) {
        return studentRepositoryDAO.findStudentById(studentId).orElseThrow(
                () -> new RuntimeException("Student with id [%s] doesn't exist!"
                        .formatted(studentId))
        );
    }

}