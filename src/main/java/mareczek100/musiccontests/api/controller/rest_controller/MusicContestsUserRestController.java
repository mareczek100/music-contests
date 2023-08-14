package mareczek100.musiccontests.api.controller.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import mareczek100.musiccontests.api.dto.HeadmasterDto;
import mareczek100.musiccontests.api.dto.MusicSchoolWithAddressDto;
import mareczek100.musiccontests.api.dto.StudentDto;
import mareczek100.musiccontests.api.dto.TeacherDto;
import mareczek100.musiccontests.api.dto.dto_rest_support.MusicSchoolsDto;
import mareczek100.musiccontests.api.dto.mapper.HeadmasterDtoMapper;
import mareczek100.musiccontests.api.dto.mapper.MusicSchoolDtoMapper;
import mareczek100.musiccontests.api.dto.mapper.StudentDtoMapper;
import mareczek100.musiccontests.api.dto.mapper.TeacherDtoMapper;
import mareczek100.musiccontests.business.HeadmasterService;
import mareczek100.musiccontests.business.MusicSchoolService;
import mareczek100.musiccontests.business.StudentService;
import mareczek100.musiccontests.business.TeacherService;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.domain.MusicSchool;
import mareczek100.musiccontests.domain.Student;
import mareczek100.musiccontests.domain.Teacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static mareczek100.musiccontests.api.controller.rest_controller.MusicContestsUserRestController.MUSIC_CONTESTS_USER_REST_MAIN_PAGE;

@Validated
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = MUSIC_CONTESTS_USER_REST_MAIN_PAGE)
@AllArgsConstructor
public class MusicContestsUserRestController  {

    public static final String MUSIC_CONTESTS_USER_REST_MAIN_PAGE = "/api/user";
    public static final String MUSIC_CONTESTS_USER_DELETE_ACCOUNT = "/delete";
    public static final String CREATE_HEADMASTER = "/create/headmaster";
    public static final String CREATE_TEACHER = "/create/teacher";
    public static final String CREATE_STUDENT = "/create/student";
    public static final String FIND_MUSIC_SCHOOL_BY_ID = "/school";
    public static final String FIND_MUSIC_SCHOOL_BY_PATRON = "/school/patron";
    public static final String FIND_ALL_MUSIC_SCHOOLS = "/school/all";
    public static final String CREATE_MUSIC_SCHOOL = "/create/school";


    private final HeadmasterService headmasterService;
    private final HeadmasterDtoMapper headmasterDtoMapper;
    private final TeacherService teacherService;
    private final TeacherDtoMapper teacherDtoMapper;
    private final StudentService studentService;
    private final StudentDtoMapper studentDtoMapper;
    private final MusicSchoolService musicSchoolService;
    private final MusicSchoolDtoMapper musicSchoolDtoMapper;


    @PostMapping(CREATE_HEADMASTER)
    @Operation(summary = "Create headmaster account.")
    public HeadmasterDto createHeadmaster(@RequestBody @Valid HeadmasterDto headmasterDto)
    {
        Headmaster headmaster = headmasterDtoMapper.mapFromDtoToDomain(headmasterDto);
        Headmaster insertedHeadmaster = headmasterService.insertHeadmaster(headmaster);
        return headmasterDtoMapper.mapFromDomainToDto(insertedHeadmaster);
    }
    @PostMapping(CREATE_TEACHER)
    @Operation(summary = "Create teacher account.")
    public TeacherDto createTeacher(@RequestBody @Valid TeacherDto teacherDto)
    {
        Teacher teacher = teacherDtoMapper.mapFromDtoToDomain(teacherDto);
        Teacher insertedTeacher = teacherService.insertTeacher(teacher);
        return teacherDtoMapper.mapFromDomainToDto(insertedTeacher);
    }
    @PostMapping(CREATE_STUDENT)
    @Operation(summary = "Create student account.")
    public StudentDto createStudent(@RequestBody @Valid StudentDto studentDto)
    {
        Student student = studentDtoMapper.mapFromDtoToDomain(studentDto);
        Student insertedStudent = studentService.insertStudent(student);
        return studentDtoMapper.mapFromDomainToDto(insertedStudent);
    }
    @GetMapping(FIND_MUSIC_SCHOOL_BY_ID)
    @Operation(summary = "Find music school by id number.")
    public MusicSchoolWithAddressDto findMusicSchoolById(@RequestParam String musicSchoolId)
    {
        MusicSchool musicSchool = musicSchoolService.findMusicSchoolById(musicSchoolId);
        return musicSchoolDtoMapper.mapFromDomainToDto(musicSchool);
    }
    @GetMapping(FIND_MUSIC_SCHOOL_BY_PATRON)
    @Operation(summary = "Find music school by patron. Enter patron's name and surname.")
    public ResponseEntity<MusicSchoolWithAddressDto> findMusicSchoolByPatron(
            @RequestParam String patron
    )
    {
        Optional<MusicSchool> musicSchoolByPatron = musicSchoolService.findMusicSchoolByPatron(patron);

        if (musicSchoolByPatron.isEmpty()){
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                    "Music school with patron [%s] doesn't exist!".formatted(patron))).build();
        }

        MusicSchoolWithAddressDto musicSchoolWithAddressDto
                = musicSchoolDtoMapper.mapFromDomainToDto(musicSchoolByPatron.get());

        return ResponseEntity.ok(musicSchoolWithAddressDto);

    }
    @GetMapping(FIND_ALL_MUSIC_SCHOOLS)
    @Operation(summary = "Find list of available music schools.")
    public MusicSchoolsDto findAllMusicSchools()
    {
        List<MusicSchoolWithAddressDto> musicSchoolDTOs = musicSchoolService.findAllMusicSchools().stream()
                .map(musicSchoolDtoMapper::mapFromDomainToDto)
                .toList();

        return MusicSchoolsDto.builder().musicSchoolDtoList(musicSchoolDTOs).build();
    }
    @PostMapping(CREATE_MUSIC_SCHOOL)
    @Operation(summary = "Create new music school with address.")
    public MusicSchoolWithAddressDto createMusicSchoolWithAddress(
            @RequestBody @Valid MusicSchoolWithAddressDto musicSchoolWithAddressDto
    )
    {
        MusicSchool musicSchool = musicSchoolDtoMapper.mapFromDtoToDomain(musicSchoolWithAddressDto);
        MusicSchool insertedMusicSchool = musicSchoolService.insertMusicSchool(musicSchool);
        return musicSchoolDtoMapper.mapFromDomainToDto(insertedMusicSchool);
    }
    @DeleteMapping(MUSIC_CONTESTS_USER_DELETE_ACCOUNT)
    @Operation(summary = "Delete music contests user account by email.")
    public ResponseEntity<?> deleteMusicContestsUserAccount(@RequestParam("userEmail") @Email String userEmail)
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

        foundHeadmaster.ifPresent(headmasterService::deleteHeadmaster);

        foundTeacher.ifPresent(teacherService::deleteTeacher);

        foundStudent.ifPresent(studentService::deleteStudent);

        return ResponseEntity.noContent().build();
    }

}