package mareczek100.musiccontests.business;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.HeadmasterRepositoryDAO;
import mareczek100.musiccontests.business.dao.TeacherRepositoryDAO;
import mareczek100.musiccontests.domain.MusicSchool;
import mareczek100.musiccontests.domain.Teacher;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;
import mareczek100.musiccontests.infrastructure.database.entity.security.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepositoryDAO teacherRepositoryDAO;
    private final HeadmasterRepositoryDAO headmasterRepositoryDAO;
    private final MusicSchoolService musicSchoolService;
    private final SecurityService securityService;


    @Transactional
    public Teacher insertTeacher(Teacher teacher)
    {
        if (!headmasterRepositoryDAO.findHeadmasterByEmail(teacher.email()).isEmpty()){
            return teacherRepositoryDAO.insertTeacher(teacher);
        }
        RoleEntity.RoleName teacherRole = RoleEntity.RoleName.TEACHER;
        securityService.insertRoleWhileCreateNewUser(teacher.email(), teacher.pesel(), teacherRole);
        MusicSchool musicSchool = teacher.musicSchool();
        if (!musicSchool.musicSchoolId().isEmpty()){
            teacherRepositoryDAO.insertTeacher(teacher.withMusicSchool(musicSchool));
        }
        MusicSchool insertedMusicSchool = musicSchoolService.insertMusicSchool(musicSchool);
        return teacherRepositoryDAO.insertTeacher(teacher.withMusicSchool(insertedMusicSchool));
    }
    @Transactional
    public List<Teacher> findAllTeachers()
    {
        return teacherRepositoryDAO.findAllTeachers();
    }
    @Transactional
    public Teacher findTeacherByPesel(String pesel)
    {
        return teacherRepositoryDAO.findTeacherByPesel(pesel).orElseThrow(
                () -> new RuntimeException("Teacher with pesel [%s] doesn't exist!"
                        .formatted(pesel))
        );
    }
    @Transactional
    public Teacher findTeacherByEmail(String email)
    {
        return teacherRepositoryDAO.findTeacherByEmail(email).orElseThrow(
                () -> new RuntimeException("Teacher with email [%s] doesn't exist!"
                        .formatted(email))
        );
    }
}
