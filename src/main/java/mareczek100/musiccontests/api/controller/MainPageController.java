package mareczek100.musiccontests.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mareczek100.musiccontests.api.dto.HeadmasterDto;
import mareczek100.musiccontests.api.dto.MusicSchoolDto;
import mareczek100.musiccontests.api.dto.StudentDto;
import mareczek100.musiccontests.api.dto.TeacherDto;
import mareczek100.musiccontests.api.dto.mapper.HeadmasterDtoMapper;
import mareczek100.musiccontests.api.dto.mapper.StudentDtoMapper;
import mareczek100.musiccontests.api.dto.mapper.TeacherDtoMapper;
import mareczek100.musiccontests.business.HeadmasterService;
import mareczek100.musiccontests.business.StudentService;
import mareczek100.musiccontests.business.TeacherService;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.domain.Student;
import mareczek100.musiccontests.domain.Teacher;
import mareczek100.musiccontests.infrastructure.database.entity.security.MusicContestsPortalUserEntity;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;
import mareczek100.musiccontests.infrastructure.database.entity.security.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static mareczek100.musiccontests.api.controller.MainPageController.MUSIC_CONTESTS_MAIN_PAGE;

@Validated
@Controller
@RequestMapping(MUSIC_CONTESTS_MAIN_PAGE)
@AllArgsConstructor
public class MainPageController {
    public static final String MUSIC_CONTESTS_MAIN_PAGE = "/";
    public static final String MUSIC_CONTESTS_AUTHENTICATION = "/authentication";
    public static final String MUSIC_CONTESTS_LOGIN = "/login";
    public static final String MUSIC_CONTESTS_FAILURE = "/failure";
    public static final String MUSIC_CONTESTS_CREATE_ACCOUNT = "/account";
    public static final String MUSIC_CONTESTS_ACCOUNT_HEADMASTER = "/account/headmaster";
    public static final String MUSIC_CONTESTS_ACCOUNT_TEACHER = "/account/teacher";
    public static final String MUSIC_CONTESTS_ACCOUNT_STUDENT = "/account/student";
    public static final String MUSIC_CONTESTS_PROCESS = "/process";

    private final SecurityService securityService;
    private final HeadmasterService headmasterService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final HeadmasterDtoMapper headmasterDtoMapper;
    private final TeacherDtoMapper teacherDtoMapper;
    private final StudentDtoMapper studentDtoMapper;

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_LOGIN)
    public String loginHomePage() {

        return "login/login_main_page";
    }

    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_PROCESS)
    public String loginProcessHomePage(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password
    ) {

        MusicContestsPortalUserEntity portalUserEntity = securityService.findByUserName(userName);
        if (!portalUserEntity.getPassword().equalsIgnoreCase(password)
                || !portalUserEntity.getUserName().equalsIgnoreCase(userName)) {
            return loginFailureHomePage();
        }

        return switch (portalUserEntity.getRole().getRoleName()) {
            case ADMIN -> "login/login_admin";
            case HEADMASTER -> "headmaster";
            case TEACHER -> "teacher";
            case STUDENT -> "student";
        };
    }

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_CREATE_ACCOUNT)
    public String loginCreateAccountHomePage(
    ) {
        return "login/login_create_account";
    }

    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_CREATE_ACCOUNT)
    public String loginCreateAccountHomePage(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") @Valid String email,
            @RequestParam("pesel") @Valid String pesel,
            @RequestParam("role") String role,
            Model model
    ) {

        RoleEntity.RoleName roleName = RoleEntity.RoleName.valueOf(role);
        model.addAllAttributes(Map.of(
                "name", name,
                "surname", surname,
                "email", email,
                "pesel", pesel,
                "role", role
        ));

        return switch (roleName) {
            case HEADMASTER -> loginCreateHeadmasterAccountHomePage();
            case TEACHER -> loginCreateTeacherAccountHomePage();
            case STUDENT -> loginCreateStudentAccountHomePage();
            default -> loginFailureHomePage();
        };

    }

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_FAILURE)
    public String loginFailureHomePage() {

        return "login/login_failure";
    }

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_ACCOUNT_HEADMASTER)
    public String loginCreateHeadmasterAccountHomePage() {

        return "login/login_create_headmaster";
    }

    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_ACCOUNT_HEADMASTER)
    public String loginCreateHeadmasterAccount(
            @Valid @RequestBody HeadmasterDto headmasterDto,
            @Valid @RequestBody MusicSchoolDto musicSchoolDto
    ) {
        HeadmasterDto headmasterDtoToSave = headmasterDto.withMusicSchool(musicSchoolDto);
        createHeadmaster(headmasterDtoToSave);

        return "login/login_main_page";
    }

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_ACCOUNT_TEACHER)
    public String loginCreateTeacherAccountHomePage()
    {
        return "login/login_create_teacher";
    }

    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_ACCOUNT_TEACHER)
    public String loginCreateTeacherAccount(
            @Valid @RequestBody TeacherDto teacherDto,
            @Valid @RequestBody MusicSchoolDto musicSchoolDto
    ) {
        TeacherDto teacherDtoToSave = teacherDto.withMusicSchool(musicSchoolDto);
        createTeacher(teacherDtoToSave);

        return "login/login_main_page";
    }

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_ACCOUNT_STUDENT)
    public String loginCreateStudentAccountHomePage() {

        return "login/login_create_student";
    }

    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_ACCOUNT_STUDENT)
    public String loginCreateStudentAccount(
            @Valid @RequestBody StudentDto studentDto,
            @Valid @RequestBody MusicSchoolDto musicSchoolDto,
            @RequestParam("teacherEmail") @Valid String teacherEmail
    ) {
        StudentDto studentDtoToSave = studentDto.withMusicSchool(musicSchoolDto);
        createStudent(studentDtoToSave);
        return "login/login_main_page";
    }

    private void createHeadmaster(HeadmasterDto headmasterDto) {
        Headmaster headmaster = headmasterDtoMapper.mapFromDtoToDomain(headmasterDto);
        headmasterService.insertHeadmaster(headmaster);
    }

    private void createTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherDtoMapper.mapFromDtoToDomain(teacherDto);
        teacherService.insertTeacher(teacher);
    }

    private void createStudent(StudentDto studentDto) {
        Student student = studentDtoMapper.mapFromDtoToDomain(studentDto);
        studentService.insertStudent(student);
    }
}