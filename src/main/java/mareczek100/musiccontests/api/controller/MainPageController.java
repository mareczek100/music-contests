package mareczek100.musiccontests.api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import mareczek100.musiccontests.api.dto.*;
import mareczek100.musiccontests.api.dto.dto_class_support.MusicContestsPortalUserDto;
import mareczek100.musiccontests.api.dto.mapper.*;
import mareczek100.musiccontests.business.HeadmasterService;
import mareczek100.musiccontests.business.MusicSchoolService;
import mareczek100.musiccontests.business.StudentService;
import mareczek100.musiccontests.business.TeacherService;
import mareczek100.musiccontests.business.instrument_storage_service.InstrumentApiService;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.domain.MusicSchool;
import mareczek100.musiccontests.domain.Student;
import mareczek100.musiccontests.domain.Teacher;
import mareczek100.musiccontests.domain.enums.ClassLevel;
import mareczek100.musiccontests.domain.enums.Degree;
import mareczek100.musiccontests.domain.enums.EducationProgram;
import mareczek100.musiccontests.infrastructure.security.RoleEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static mareczek100.musiccontests.api.controller.MainPageController.MUSIC_CONTESTS_MAIN_PAGE;

@Validated
@Controller
@AllArgsConstructor
@RequestMapping(MUSIC_CONTESTS_MAIN_PAGE)
public class MainPageController {

    public static final String MUSIC_CONTESTS_MAIN_PAGE = "/";
    public static final String MUSIC_CONTESTS_AUTHENTICATION = "/authentication";
    public static final String MUSIC_CONTESTS_LOGIN = "/login";
    public static final String MUSIC_CONTESTS_LOGOUT = "/logout";
    public static final String MUSIC_CONTESTS_FAILURE = "/failure";
    public static final String MUSIC_CONTESTS_ERROR = "/error";
    public static final String MUSIC_CONTESTS_CREATE_ACCOUNT = "/account";
    public static final String MUSIC_CONTESTS_DELETE_ACCOUNT = "/account/delete";
    public static final String MUSIC_CONTESTS_CREATE_ACCOUNT_USER = "/account/user";
    public static final String MUSIC_CONTESTS_ACCOUNT_STUDENT = "/account/student";
    public static final String MUSIC_CONTESTS_ACCOUNT_TEACHER = "/account/teacher";
    private final static Boolean ACCOUNT_CREATED_SUCCESS = true;
    private final static Boolean ACCOUNT_CREATED_FAILURE = false;
    private final static Boolean ACCOUNT_DELETED_SUCCESS = true;
    private final static Boolean ACCOUNT_DELETED_FAILURE = false;
    private final static String NONE_MUSIC_SCHOOL = "NONE";

    private final HeadmasterService headmasterService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final MusicSchoolService musicSchoolService;
    private final InstrumentApiService instrumentApiService;
    private final InstrumentDtoMapper instrumentDtoMapper;
    private final MusicSchoolDtoMapper musicSchoolDtoMapper;
    private final HeadmasterDtoMapper headmasterDtoMapper;
    private final TeacherDtoMapper teacherDtoMapper;
    private final StudentDtoMapper studentDtoMapper;

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_LOGIN)
    public String loginHomePage() {

        return "login/login_main_page";
    }

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_CREATE_ACCOUNT)
    public String loginCreateAccountHomePage(Model model) {
        List<String> roleList = Arrays.stream(RoleEntity.RoleName.values())
                .filter(roleName -> !roleName.equals(RoleEntity.RoleName.ADMIN))
                .map(Enum::name).toList();

        List<MusicSchoolWithAddressDto> musicSchoolDTOs = musicSchoolService.findAllMusicSchools().stream()
                .map(musicSchoolDtoMapper::mapFromDomainToDto)
                .toList();

        model.addAttribute("roleList", roleList);
        model.addAttribute("musicSchoolDTOs", musicSchoolDTOs);

        return "login/login_create_account";
    }

    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_CREATE_ACCOUNT)
    public ModelAndView loginCreateAccountProcess(
            @ModelAttribute("musicSchoolDto") MusicSchoolWithAddressDto musicSchoolDto,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") @Email String email,
            @RequestParam("pesel") @Valid String pesel,
            @RequestParam("role") String role
    ) {

        RoleEntity.RoleName roleName = RoleEntity.RoleName.valueOf(role);
        MusicContestsPortalUserDto portalUser = MusicContestsPortalUserDto.builder()
                .name(name)
                .surname(surname)
                .email(email.strip())
                .pesel(pesel)
                .role(roleName)
                .build();

        List<InstrumentDto> instrumentDTOs = instrumentApiService.findAllInstruments().stream()
                .map(instrumentDtoMapper::mapFromDomainToDto)
                .toList();

        String musicSchoolId = Objects.requireNonNullElse(musicSchoolDto.musicSchoolId(), "");

        if (musicSchoolId.equals(NONE_MUSIC_SCHOOL)) {
            MusicSchoolWithAddressDto musicSchoolToCreateDto = MusicSchoolWithAddressDto.builder().build();
            ModelAndView musicSchoolModelView = new ModelAndView("login/login_create_music_school");
            musicSchoolModelView.addObject("portalUser", portalUser);
            musicSchoolModelView.addObject("musicSchoolDto", musicSchoolToCreateDto);
            return musicSchoolModelView;
        }


        return switch (roleName) {
            case HEADMASTER -> createHeadmaster(portalUser, musicSchoolDto);
            case TEACHER -> prepareTeacher(portalUser, musicSchoolDto, instrumentDTOs);
            case STUDENT -> prepareStudent(portalUser, musicSchoolDto, instrumentDTOs);
            default -> {
                ModelAndView failureModelView = new ModelAndView("login/login_failure");
                failureModelView.addObject("accountCreated", ACCOUNT_CREATED_FAILURE);
                yield failureModelView;
            }
        };
    }

//    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_CREATE_ACCOUNT_USER)
//    public ModelAndView loginCreateUserAccountProcess(
//            @ModelAttribute("musicSchoolDto") MusicSchoolWithAddressDto musicSchoolDto,
//            @RequestParam("name") String name,
//            @RequestParam("surname") String surname,
//            @RequestParam("email") @Email String email,
//            @RequestParam("pesel") @Valid String pesel,
//            @RequestParam("role") String role
//    ) {
//        RoleEntity.RoleName roleName = RoleEntity.RoleName.valueOf(role);
//        MusicContestsPortalUserDto portalUser = MusicContestsPortalUserDto.builder()
//                .name(name)
//                .surname(surname)
//                .email(email)
//                .pesel(pesel)
//                .build();
//
//        List<InstrumentDto> instrumentDTOs = instrumentApiService.findAllInstruments().stream()
//                .map(instrumentDtoMapper::mapFromDomainToDto)
//                .toList();
//
//        return switch (roleName) {
//            case HEADMASTER -> createHeadmaster(portalUser, musicSchoolDto);
//            case TEACHER -> prepareTeacher(portalUser, musicSchoolDto, instrumentDTOs);
//            case STUDENT -> prepareStudent(portalUser, musicSchoolDto, instrumentDTOs);
//            default -> {
//                ModelAndView failureModelView = new ModelAndView("login/login_failure");
//                failureModelView.addObject("accountCreated", ACCOUNT_CREATED_FAILURE);
//                yield failureModelView;
//            }
//        };
//    }


    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_ACCOUNT_TEACHER)
    public String loginCreateTeacherAccountProcess(
            @Valid @ModelAttribute TeacherDto teacherDto,
            @Valid @ModelAttribute("musicSchoolDto") MusicSchoolWithAddressDto musicSchoolDto,
            Model model
    ) {
        TeacherDto insertedTeacherDto = createTeacher(teacherDto, musicSchoolDto);
        model.addAttribute("accountCreated", ACCOUNT_CREATED_SUCCESS);
        model.addAttribute("portalUser", insertedTeacherDto);

        return "login/login_main_page";
    }

    @PostMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_ACCOUNT_STUDENT)
    public String loginCreateStudentAccountProcess(
            @Valid @ModelAttribute StudentDto studentDto,
            @ModelAttribute("musicSchoolDto") MusicSchoolWithAddressDto musicSchoolDto,
            @RequestParam("teacherEmail") @Email String teacherEmail,
            Model model
    ) {
        StudentDto insertedStudentDto = createStudent(studentDto, teacherEmail, musicSchoolDto);
        model.addAttribute("accountCreated", ACCOUNT_CREATED_SUCCESS);
        model.addAttribute("portalUser", insertedStudentDto);

        return "login/login_main_page";
    }

    @DeleteMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_DELETE_ACCOUNT)
    public String deleteMusicContestsUserAccount(
            @RequestParam("userEmail") @Email String userEmail,
            Model model
    )
    {
        Optional<Headmaster> foundHeadmaster = headmasterService.findAllHeadmasters().stream()
                .filter(headmaster -> userEmail.equalsIgnoreCase(headmaster.email()))
                .findAny();

        Optional<Teacher> foundTeacher = teacherService.findAllTeachers().stream()
                .filter(teacher -> userEmail.equalsIgnoreCase(teacher.email()))
                .findAny();

        Optional<Student> foundStudent = studentService.findAllStudents().stream()
                .filter(student -> userEmail.equalsIgnoreCase(student.email()))
                .findAny();

        if (foundHeadmaster.isPresent()){
            Headmaster headmaster = foundHeadmaster.get();
            headmasterService.deleteHeadmaster(headmaster);
            model.addAttribute("accountDeleted", ACCOUNT_DELETED_SUCCESS);
            model.addAttribute("portalUser", headmaster);
            foundTeacher.ifPresent(teacherService::deleteTeacher);
            return "login/login_main_page";
        }

        if (foundTeacher.isPresent()){
            Teacher teacher = foundTeacher.get();
            teacherService.deleteTeacher(teacher);
            model.addAttribute("accountDeleted", ACCOUNT_DELETED_SUCCESS);
            model.addAttribute("portalUser", teacher);
            return "login/login_main_page";
        }

        if (foundStudent.isPresent()){
            Student student = foundStudent.get();
            studentService.deleteStudent(student);
            model.addAttribute("accountDeleted", ACCOUNT_DELETED_SUCCESS);
            model.addAttribute("portalUser", student);
            return "login/login_main_page";
        }

        model.addAttribute("accountDeleted", ACCOUNT_DELETED_FAILURE);
        return "login/login_main_page";
    }

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_FAILURE)
    public String loginFailureHomePage() {

        return "login/login_failure";
    }

    @GetMapping(MUSIC_CONTESTS_ERROR)
    public String errorHomePage() {
        return "error";
    }

    @GetMapping(MUSIC_CONTESTS_AUTHENTICATION + MUSIC_CONTESTS_LOGOUT)
    public String loginLogoutHomePage() {
        return "login/login_logout";
    }

    private ModelAndView createHeadmaster(MusicContestsPortalUserDto portalUser,
                                          MusicSchoolWithAddressDto musicSchoolDto) {
        ModelAndView headmasterModelView = new ModelAndView("login/login_main_page");

        Headmaster headmaster = Headmaster.builder()
                .name(portalUser.getName())
                .surname(portalUser.getSurname())
                .email(portalUser.getEmail())
                .pesel(portalUser.getPesel())
                .build();

        HeadmasterDto headmasterDto;

        if (!musicSchoolDto.musicSchoolId().isEmpty()) {
            MusicSchool musicSchool = musicSchoolService.findMusicSchoolById(musicSchoolDto.musicSchoolId());
            Headmaster insertedHeadmaster = headmasterService.insertHeadmaster(headmaster.withMusicSchool(musicSchool));
            headmasterDto = headmasterDtoMapper.mapFromDomainToDto(insertedHeadmaster);
        } else {
            MusicSchool musicSchool = musicSchoolDtoMapper.mapFromDtoToDomain(musicSchoolDto);
            Headmaster insertedHeadmaster = headmasterService.insertHeadmaster(headmaster.withMusicSchool(musicSchool));
            headmasterDto = headmasterDtoMapper.mapFromDomainToDto(insertedHeadmaster);
        }
        headmasterModelView.addObject("portalUser", headmasterDto);
        headmasterModelView.addObject("accountCreated", ACCOUNT_CREATED_SUCCESS);
        headmasterModelView.setStatus(HttpStatus.CREATED);

        return headmasterModelView;
    }

    private ModelAndView prepareTeacher(MusicContestsPortalUserDto portalUser,
                                        MusicSchoolWithAddressDto musicSchoolDto,
                                        List<InstrumentDto> instrumentDTOs)
    {
        TeacherDto teacherDto = TeacherDto.builder().build();
        ModelAndView teacherModelView = new ModelAndView("login/login_create_teacher");
        teacherModelView.addObject("portalUser", portalUser);
        teacherModelView.addObject("teacherDto", teacherDto);
        teacherModelView.addObject("instrumentDTOs", instrumentDTOs);
        teacherModelView.addObject("musicSchoolDto", musicSchoolDto);
        return teacherModelView;
    }

    private TeacherDto createTeacher(TeacherDto teacherDto,
                                     MusicSchoolWithAddressDto musicSchoolDto
    ) {
        Teacher teacher = teacherDtoMapper.mapFromDtoToDomain(teacherDto);
        TeacherDto insertedTeacherDto;

        if (!musicSchoolDto.musicSchoolId().isEmpty()) {
            MusicSchool musicSchool = musicSchoolService.findMusicSchoolById(musicSchoolDto.musicSchoolId());
            Teacher insertedTeacher = teacherService.insertTeacher(teacher.withMusicSchool(musicSchool));
            insertedTeacherDto = teacherDtoMapper.mapFromDomainToDto(insertedTeacher);
        } else {
            MusicSchool musicSchool = musicSchoolDtoMapper.mapFromDtoToDomain(musicSchoolDto);
            Teacher insertedTeacher = teacherService.insertTeacher(teacher.withMusicSchool(musicSchool));
            insertedTeacherDto = teacherDtoMapper.mapFromDomainToDto(insertedTeacher);
        }
        return insertedTeacherDto;
    }

    private ModelAndView prepareStudent(MusicContestsPortalUserDto portalUser,
                                        MusicSchoolWithAddressDto musicSchoolDto,
                                        List<InstrumentDto> instrumentDTOs) {
        StudentDto studentDto = StudentDto.builder().build();
        ModelAndView studentModelView = new ModelAndView("login/login_create_student");

        List<ClassLevel> classLevels = Arrays.stream(ClassLevel.values()).toList();
        List<EducationProgram> educationPrograms = Arrays.stream(EducationProgram.values()).toList();
        List<Degree> degrees = Arrays.stream(Degree.values()).toList();
        List<TeacherDto> teacherDTOs = teacherService.findAllTeachers().stream()
                .filter(teacher -> musicSchoolDto.musicSchoolId()
                        .equals(teacher.musicSchool().musicSchoolId()))
                .map(teacherDtoMapper::mapFromDomainToDto)
                .toList();

        studentModelView.addObject("classLevels", classLevels);
        studentModelView.addObject("educationPrograms", educationPrograms);
        studentModelView.addObject("degrees", degrees);
        studentModelView.addObject("teacherDTOs", teacherDTOs);
        studentModelView.addObject("portalUser", portalUser);
        studentModelView.addObject("studentDto", studentDto);
        studentModelView.addObject("instrumentDTOs", instrumentDTOs);
        studentModelView.addObject("musicSchoolDto", musicSchoolDto);

        return studentModelView;
    }

    private StudentDto createStudent(StudentDto studentDto,
                                     String teacherEmail,
                                     MusicSchoolWithAddressDto musicSchoolDto)
    {
        Student student = studentDtoMapper.mapFromDtoToDomain(studentDto);
        Teacher teacherByEmail = teacherService.findTeacherByEmail(teacherEmail);
        StudentDto insertedStudentDto;

        if (!musicSchoolDto.musicSchoolId().isEmpty()) {
            MusicSchool musicSchool = musicSchoolService.findMusicSchoolById(musicSchoolDto.musicSchoolId());
            Student insertedStudent = studentService.insertStudent(student
                    .withMusicSchool(musicSchool)
                    .withTeacher(teacherByEmail));
            insertedStudentDto = studentDtoMapper.mapFromDomainToDto(insertedStudent);
        } else {
            MusicSchool musicSchool = musicSchoolDtoMapper.mapFromDtoToDomain(musicSchoolDto);
            Student insertedStudent = studentService.insertStudent(student
                    .withMusicSchool(musicSchool)
                    .withTeacher(teacherByEmail));
            insertedStudentDto = studentDtoMapper.mapFromDomainToDto(insertedStudent);
        }
        return insertedStudentDto;
    }
}