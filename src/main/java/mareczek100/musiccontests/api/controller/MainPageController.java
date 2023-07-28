package mareczek100.musiccontests.api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import mareczek100.musiccontests.api.dto.HeadmasterDto;
import mareczek100.musiccontests.api.dto.MusicSchoolWithAddressDto;
import mareczek100.musiccontests.api.dto.StudentDto;
import mareczek100.musiccontests.api.dto.TeacherDto;
import mareczek100.musiccontests.api.dto.mapper.HeadmasterDtoMapper;
import mareczek100.musiccontests.api.dto.mapper.MusicSchoolDtoMapper;
import mareczek100.musiccontests.api.dto.mapper.StudentDtoMapper;
import mareczek100.musiccontests.api.dto.mapper.TeacherDtoMapper;
import mareczek100.musiccontests.business.HeadmasterService;
import mareczek100.musiccontests.business.StudentService;
import mareczek100.musiccontests.business.TeacherService;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.domain.MusicSchool;
import mareczek100.musiccontests.domain.Student;
import mareczek100.musiccontests.domain.Teacher;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static mareczek100.musiccontests.api.controller.MainPageController.MUSIC_CONTESTS_MAIN_PAGE;

@Validated
@Controller
@RequestMapping(MUSIC_CONTESTS_MAIN_PAGE)
@AllArgsConstructor
public class MainPageController {

    private final static Boolean ACCOUNT_CREATED_SUCCESS = true;
    private final static Boolean ACCOUNT_CREATED_FAILURE = false;
    public static final String MUSIC_CONTESTS_MAIN_PAGE = "/";
    public static final String MUSIC_CONTESTS_AUTHENTICATION = "/authentication";
    public static final String MUSIC_CONTESTS_LOGIN = "/login";
    public static final String MUSIC_CONTESTS_LOGOUT = "/logout";
    public static final String MUSIC_CONTESTS_FAILURE = "/failure";
    public static final String MUSIC_CONTESTS_ERROR = "/error";
    public static final String MUSIC_CONTESTS_CREATE_ACCOUNT = "/account";
    public static final String MUSIC_CONTESTS_ACCOUNT_HEADMASTER = "/account/headmaster";
    public static final String MUSIC_CONTESTS_ACCOUNT_TEACHER = "/account/teacher";
    public static final String MUSIC_CONTESTS_ACCOUNT_STUDENT = "/account/student";

    private final HeadmasterService headmasterService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final MusicSchoolDtoMapper musicSchoolDtoMapper;
    private final HeadmasterDtoMapper headmasterDtoMapper;
    private final TeacherDtoMapper teacherDtoMapper;
    private final StudentDtoMapper studentDtoMapper;

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_LOGIN)
    public String loginHomePage() {

        return "login/login_main_page";
    }

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_CREATE_ACCOUNT)
    public String loginCreateAccountHomePage(Model model)
    {
        List<String> roleList = Arrays.stream(RoleEntity.RoleName.values())
                .filter(roleName -> !roleName.equals(RoleEntity.RoleName.ADMIN))
                .map(Enum::name).toList();
        model.addAttribute("roleList", roleList);
        return "login/login_create_account";
    }

    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_CREATE_ACCOUNT)
    public ModelAndView loginCreateAccountProcess(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") @Email String email,
            @RequestParam("pesel") @Valid String pesel,
            @RequestParam("role") String role
    ) {
        email = email.strip();

        RoleEntity.RoleName roleName = RoleEntity.RoleName.valueOf(role);

        HeadmasterDto headmasterDto = HeadmasterDto.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .pesel(pesel)
                .build();
        TeacherDto teacherDto = TeacherDto.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .pesel(pesel)
                .build();
        StudentDto studentDto = StudentDto.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .pesel(pesel)
                .build();

        MusicSchoolWithAddressDto musicSchoolDto = MusicSchoolWithAddressDto.builder().build();

        ModelAndView headmasterModelView = new ModelAndView("login/login_create_headmaster");
        ModelAndView teacherModelView = new ModelAndView("login/login_create_teacher");
        ModelAndView studentModelView = new ModelAndView("login/login_create_student");
        ModelAndView failureModelView = new ModelAndView("login/login_failure");
        headmasterModelView.addObject("headmasterDto", headmasterDto);
        headmasterModelView.addObject("musicSchoolDto", musicSchoolDto);
        teacherModelView.addObject("teacherDto", teacherDto);
        teacherModelView.addObject("musicSchoolDto", musicSchoolDto);
        studentModelView.addObject("studentDto", studentDto);
        studentModelView.addObject("musicSchoolDto", musicSchoolDto);
        failureModelView.addObject("accountCreated", ACCOUNT_CREATED_FAILURE);

        return switch (roleName) {
            case HEADMASTER -> headmasterModelView;
            case TEACHER -> teacherModelView;
            case STUDENT -> studentModelView;
            default -> failureModelView;
        };
    }

    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_ACCOUNT_HEADMASTER)
    public String loginCreateHeadmasterAccountProcess(
            @Valid @ModelAttribute HeadmasterDto headmasterDto,
            @Valid @ModelAttribute MusicSchoolWithAddressDto musicSchoolDto,
            Model model
    ) {
        HeadmasterDto insertedHeadmasterDto = createHeadmaster(headmasterDto, musicSchoolDto);
        model.addAttribute("accountCreated", ACCOUNT_CREATED_SUCCESS);
        model.addAttribute("headmasterDto", insertedHeadmasterDto);
        return "login/login_main_page";
    }

    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_ACCOUNT_TEACHER)
    public String loginCreateTeacherAccountProcess(
            @Valid @ModelAttribute TeacherDto teacherDto,
            @Valid @ModelAttribute MusicSchoolWithAddressDto musicSchoolDto,
            Model model
    ) {
        TeacherDto insertedTeacherDto = createTeacher(teacherDto, musicSchoolDto);
        model.addAttribute("accountCreated", ACCOUNT_CREATED_SUCCESS);
        model.addAttribute("headmasterDto", insertedTeacherDto);

        return "login/login_main_page";
    }

    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_ACCOUNT_STUDENT)
    public String loginCreateStudentAccountProcess(
            @Valid @ModelAttribute StudentDto studentDto,
            @Valid @ModelAttribute MusicSchoolWithAddressDto musicSchoolDto,
            @RequestParam("teacherEmail") @Email String teacherEmail,
            Model model
    ) {
        StudentDto insertedStudentDto = createStudent(studentDto, musicSchoolDto);
        model.addAttribute("accountCreated", ACCOUNT_CREATED_SUCCESS);
        model.addAttribute("headmasterDto", insertedStudentDto);

        return "login/login_main_page";
    }
    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_FAILURE)
    public String loginFailureHomePage()
    {

        return "login/login_failure";
    }
    @GetMapping(MUSIC_CONTESTS_ERROR)
    public String errorHomePage()
    {
        return "error";
    }
    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_LOGOUT)
    public String loginLogoutHomePage()
    {
        return "login/login_logout";
    }
    private HeadmasterDto createHeadmaster(HeadmasterDto headmasterDto, MusicSchoolWithAddressDto musicSchoolDto)
    {
        Headmaster headmaster = headmasterDtoMapper.mapFromDtoToDomain(headmasterDto);
        MusicSchool musicSchool = musicSchoolDtoMapper.mapFromDtoToDomain(musicSchoolDto);
        Headmaster insertedHeadmaster = headmasterService.insertHeadmaster(headmaster.withMusicSchool(musicSchool));
        return headmasterDtoMapper.mapFromDomainToDto(insertedHeadmaster);

    }

    private TeacherDto createTeacher(TeacherDto teacherDto, MusicSchoolWithAddressDto musicSchoolDto)
    {
        Teacher teacher = teacherDtoMapper.mapFromDtoToDomain(teacherDto);
        MusicSchool musicSchool = musicSchoolDtoMapper.mapFromDtoToDomain(musicSchoolDto);
        Teacher insertedTeacher = teacherService.insertTeacher(teacher.withMusicSchool(musicSchool));
        return teacherDtoMapper.mapFromDomainToDto(insertedTeacher);
    }

    private StudentDto createStudent(StudentDto studentDto, MusicSchoolWithAddressDto musicSchoolDto)
    {
        Student student = studentDtoMapper.mapFromDtoToDomain(studentDto);
        MusicSchool musicSchool = musicSchoolDtoMapper.mapFromDtoToDomain(musicSchoolDto);
        Student insertedStudent = studentService.insertStudent(student.withMusicSchool(musicSchool));
        return studentDtoMapper.mapFromDomainToDto(insertedStudent);
    }
}