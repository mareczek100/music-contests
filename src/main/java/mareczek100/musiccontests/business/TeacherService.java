package mareczek100.musiccontests.business;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.TeacherRepositoryDAO;
import mareczek100.musiccontests.domain.Teacher;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;
import mareczek100.musiccontests.infrastructure.database.entity.security.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepositoryDAO teacherRepositoryDAO;
    private final SecurityService securityService;


    @Transactional
    public Teacher insertTeacher(Teacher teacher)
    {
        RoleEntity.RoleName teacherRole = RoleEntity.RoleName.TEACHER;
        securityService.insertRoleWhileCreateNewUser(teacher.email(), teacher.pesel(), teacherRole);
        return teacherRepositoryDAO.insertTeacher(teacher);
    }
    @Transactional
    public List<Teacher> findAllTeachers()
    {
        return teacherRepositoryDAO.findAllTeachers();
    }
    @Transactional
    public Optional<Teacher> findTeacherByPesel(String pesel)
    {
        return teacherRepositoryDAO.findTeacherByPesel(pesel);
    }
}
