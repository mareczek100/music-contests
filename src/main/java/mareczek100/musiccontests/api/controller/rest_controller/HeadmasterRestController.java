package mareczek100.musiccontests.api.controller.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import mareczek100.musiccontests.api.controller.rest_controller.controller_rest_support.AllUsersRestUtils;
import mareczek100.musiccontests.api.controller.rest_controller.controller_rest_support.ControllerRestSupport;
import mareczek100.musiccontests.api.controller.rest_controller.controller_rest_support.TeacherRestUtils;
import mareczek100.musiccontests.api.dto.*;
import mareczek100.musiccontests.api.dto.dto_class_support.CompetitionResultListDto;
import mareczek100.musiccontests.api.dto.dto_rest_support.*;
import mareczek100.musiccontests.api.dto.mapper.*;
import mareczek100.musiccontests.business.*;
import mareczek100.musiccontests.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static mareczek100.musiccontests.api.controller.rest_controller.HeadmasterRestController.HEADMASTER_REST_MAIN_PAGE;

@Validated
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = HEADMASTER_REST_MAIN_PAGE)
@AllArgsConstructor
public class HeadmasterRestController implements ControllerRestSupport {

    public static final String HEADMASTER_REST_MAIN_PAGE = "/api/headmaster";
    public static final String CREATE_COMPETITION_AT_SCHOOL = "/competition/create/school";
    public static final String CREATE_COMPETITION_AT_OTHER_LOCATION = "/competition/create/location";
    public static final String CREATE_TEACHER_RIGHTS = "/teacher/rights";
    public static final String FIND_HEADMASTER_COMPETITIONS = "/competitions/headmaster";
    public static final String FIND_ALL_TEACHERS = "/teachers";
    public static final String FIND_ALL_SCHOOL_STUDENTS = "/school/students";
    public static final String FIND_ALL_COMPETITION_STUDENTS = "/competition/students";
    public static final String FIND_ALL_TEACHERS_APPLICATIONS = "/competition/applications/all";
    public static final String CHECK_ALL_RESULT = "/results/all";
    public static final String ANNOUNCE_RESULT = "/results/announce";

    private final CompetitionService competitionService;
    private final CompetitionDtoMapper competitionDtoMapper;
    private final TeacherService teacherService;
    private final TeacherDtoMapper teacherDtoMapper;
    private final HeadmasterService headmasterService;
    private final HeadmasterDtoMapper headmasterDtoMapper;
    private final StudentService studentService;
    private final StudentDtoMapper studentDtoMapper;
    private final ApplicationFormService applicationFormService;
    private final ApplicationFormDtoMapper applicationFormDtoMapper;
    private final CompetitionResultService competitionResultService;
    private final CompetitionResultDtoMapper competitionResultDtoMapper;
    private final AllUsersRestUtils allUsersRestUtils;
    private final TeacherRestUtils teacherRestUtils;


    @GetMapping(FIND_ALL_INSTRUMENTS)
    @Operation(summary = "Find list of available instruments.")
    public InstrumentsDto findAllAvailableInstruments()
    {
        return allUsersRestUtils.findAllAvailableInstruments();
    }

    @GetMapping(FIND_ALL_CITIES)
    @Operation(summary = "Find list of available competition's cities.")
    public CitiesDto findAllAvailableCompetitionCities()
    {
        return allUsersRestUtils.findAllAvailableCompetitionCities();
    }

    @GetMapping(FIND_AVAILABLE_CLASS_LEVELS)
    @Operation(summary = "Find list of available class levels.")
    public ClassLevels findAllClassLevels()
    {
        return teacherRestUtils.findAllClassLevels();
    }

    @PostMapping(CREATE_COMPETITION_AT_SCHOOL)
    @Operation(summary = "Create new competition at headmaster's music school.")
    public ResponseEntity<CompetitionWithLocationDto> createCompetitionAtSchool(
            @RequestParam("headmasterOrganizerEmail") String headmasterOrganizerEmail,
            @RequestParam("competitionName") String competitionName,
            @RequestParam("competitionInstrument") String competitionInstrument,
            @RequestParam("competitionOnline") Boolean competitionOnline,
            @RequestParam("competitionPrimaryDegree") Boolean competitionPrimaryDegree,
            @RequestParam("competitionSecondaryDegree") Boolean competitionSecondaryDegree,
            @RequestParam("competitionBeginning") @DateTimeFormat LocalDateTime competitionBeginning,
            @RequestParam("competitionEnd") @DateTimeFormat LocalDateTime competitionEnd,
            @RequestParam("competitionResultAnnouncement") @DateTimeFormat LocalDateTime competitionResultAnnouncement,
            @RequestParam("competitionApplicationDeadline") @DateTimeFormat LocalDateTime competitionApplicationDeadline,
            @RequestParam("competitionRequirementsDescription") String competitionRequirementsDescription
    )
    {

        CompetitionWithLocationDto competitionDto = CompetitionWithLocationDto.builder()
                .competitionName(competitionName)
                .competitionInstrument(competitionInstrument)
                .competitionOnline(competitionOnline)
                .competitionPrimaryDegree(competitionPrimaryDegree)
                .competitionSecondaryDegree(competitionSecondaryDegree)
                .competitionBeginning(competitionBeginning)
                .competitionEnd(competitionEnd)
                .competitionResultAnnouncement(competitionResultAnnouncement)
                .competitionApplicationDeadline(competitionApplicationDeadline)
                .competitionRequirementsDescription(competitionRequirementsDescription)
                .build();

        CompetitionWithLocationDto competitionAtSchool
                = createCompetitionAtSchool(competitionDto, headmasterOrganizerEmail);

        return ResponseEntity.status(HttpStatus.CREATED).body(competitionAtSchool);

    }

    @PostMapping(CREATE_COMPETITION_AT_OTHER_LOCATION)
    @Operation(summary = "Create new competition at other location.")
    public ResponseEntity<CompetitionWithLocationDto> createCompetitionAtOtherLocation(
            @RequestBody @Valid CompetitionWithLocationDto competitionDto,
            @RequestParam("headmasterOrganizerEmail") String headmasterOrganizerEmail
    )
    {
        CompetitionWithLocationDto competitionAtOtherPlace
                = createCompetitionAtOtherPlace(competitionDto, headmasterOrganizerEmail);

        return ResponseEntity.status(HttpStatus.CREATED).body(competitionAtOtherPlace);

    }
    @GetMapping(FIND_ALL_COMPETITIONS)
    @Operation(summary = "Find list of all available music competitions. Sorted by instrument, without paging.")
    public ResponseEntity<CompetitionsDto> findAllAvailableCompetitions()
    {
        return allUsersRestUtils.findAllAvailableCompetitions();
    }
    @GetMapping(FIND_ALL_COMPETITIONS_PAGEABLE)
    @Operation(summary = "Find list of all available music competitions - 5 results per page, sorted by instrument.")
    public ResponseEntity<CompetitionsDto> findAllAvailableCompetitions(
            @PathVariable("currentPage") Integer currentPage
    )
    {
        return allUsersRestUtils.findAllAvailableCompetitionsPageable(currentPage);
    }

    @GetMapping(FIND_AVAILABLE_COMPETITIONS_BY_FILTERS)
    @Operation(summary = "Find list of available music competitions by filters.")
    public CompetitionsDto findAvailableCompetitionsByFilters(
            @RequestParam("competitionInstrument") String competitionInstrument,
            @RequestParam("competitionOnline") Boolean competitionOnline,
            @RequestParam("competitionPrimaryDegree") Boolean competitionPrimaryDegree,
            @RequestParam("competitionSecondaryDegree") Boolean competitionSecondaryDegree,
            @RequestParam("competitionCity") String competitionCity
    )
    {
        return allUsersRestUtils.findAvailableCompetitionsByFilters(
                competitionInstrument, competitionOnline, competitionPrimaryDegree,
                competitionSecondaryDegree, competitionCity
        );
    }

    @GetMapping(FIND_AVAILABLE_COMPETITIONS_BY_INSTRUMENT)
    @Operation(summary = "Find list of all available music competitions for chosen instrument.")
    public CompetitionsDto findAllAvailableCompetitionsByInstrument(
            @RequestParam("competitionInstrument") String competitionInstrument
    )
    {
        return allUsersRestUtils.findAvailableCompetitionsByInstrument(competitionInstrument);
    }

    @GetMapping(FIND_FINISHED_COMPETITIONS_BY_FILTERS)
    @Operation(summary = "Find list of finished music competitions by filters.")
    public CompetitionsDto findFinishedCompetitionsByFilters(
            @RequestParam("competitionDateFrom") @DateTimeFormat LocalDate competitionDateFrom,
            @RequestParam("competitionDateTo") @DateTimeFormat LocalDate competitionDateTo,
            @RequestParam("competitionCity") String competitionCity
    )
    {
        return teacherRestUtils.findFinishedCompetitionsByFilters(
                competitionDateFrom, competitionDateTo, competitionCity);
    }

    @PostMapping(CREATE_TEACHER_RIGHTS)
    @Operation(summary = "Create for headmaster teacher's account.")
    public ResponseEntity<TeacherDto> createHeadmasterTeacherRights(
            @RequestParam("headmasterEmail") String headmasterEmail,
            @RequestParam("instrument") String instrument
    )
    {
        Headmaster headmaster = headmasterService.findHeadmasterByEmail(headmasterEmail);

        Optional<Teacher> anyTeacher = teacherService.findAllTeachers().stream()
                .filter(teacher -> headmaster.pesel().equals(teacher.pesel()))
                .findAny();

        if (anyTeacher.isPresent()) {
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
                    "Teacher with email [%s] already exist!".formatted(headmasterEmail))).build();
        }

        TeacherDto teacherDto = createTeacher(headmaster, instrument);

        return ResponseEntity.status(HttpStatus.CREATED).body(teacherDto);
    }

    @GetMapping(FIND_ALL_TEACHERS)
    @Operation(summary = "Find list of all teachers from headmaster's music school.")
    public ResponseEntity<TeachersDto> findAllTeachers(
            @RequestParam("headmasterEmail") @Email String headmasterEmail
    )
    {
        Headmaster headmaster = headmasterService.findHeadmasterByEmail(headmasterEmail);
        String musicSchoolId = headmaster.musicSchool().musicSchoolId();

        List<TeacherDto> teacherDTOs = teacherService.findAllTeachers().stream()
                .filter(teacher -> musicSchoolId.equals(teacher.musicSchool().musicSchoolId()))
                .map(teacherDtoMapper::mapFromDomainToDto)
                .toList();

        if (teacherDTOs.isEmpty()) {
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                    "Your school [%s] has no teachers at all!"
                            .formatted(headmaster.musicSchool().name()))).build();
        }

        TeachersDto teachersDto = TeachersDto.builder().TeacherDtoList(teacherDTOs).build();
        return ResponseEntity.ok(teachersDto);
    }

    @GetMapping(FIND_ALL_TEACHER_STUDENTS)
    @Operation(summary = "Find list of headmaster's students.")
    public ResponseEntity<StudentsDto> findAllTeacherStudents(
            @RequestParam("teacherEmail") @Email String teacherEmail
    )
    {
        return teacherRestUtils.findAllTeacherStudents(teacherEmail);
    }

    @GetMapping(FIND_ALL_COMPETITION_STUDENTS)
    @Operation(summary = "Find list of all students participated in competition.")
    public ResponseEntity<StudentsDto> findAllCompetitionStudents(
            @RequestParam("competitionId") String competitionId
    )
    {
        List<StudentDto> studentDTOs = applicationFormService.findAllApplicationForms().stream()
                .filter(applicationForm -> competitionId.equals(applicationForm.competition().competitionId()))
                .map(ApplicationForm::student)
                .map(studentDtoMapper::mapFromDomainToDto)
                .toList();

        if (studentDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        StudentsDto studentsDto = StudentsDto.builder().StudentDtoList(studentDTOs).build();
        return ResponseEntity.ok(studentsDto);
    }

    @GetMapping(FIND_ALL_SCHOOL_STUDENTS)
    @Operation(summary = "Find list of all students from headmaster's music school.")
    public StudentsDto findAllSchoolStudents(@RequestParam("headmasterEmail") String headmasterEmail)
    {
        Headmaster headmaster = headmasterService.findHeadmasterByEmail(headmasterEmail);
        String musicSchoolId = headmaster.musicSchool().musicSchoolId();
        List<StudentDto> studentDTOs = studentService.findAllStudents().stream()
                .filter(student -> musicSchoolId.equals(student.musicSchool().musicSchoolId()))
                .map(studentDtoMapper::mapFromDomainToDto)
                .toList();

        return StudentsDto.builder().StudentDtoList(studentDTOs).build();
    }

    @PostMapping(ANNOUNCE_STUDENT_TO_COMPETITION)
    @Operation(summary = "Fill in application form to announce student to competition.")
    public ResponseEntity<ApplicationFormDto> announceStudentToCompetition(
            @RequestParam("teacherEmail") String teacherEmail,
            @RequestParam("studentId") String studentId,
            @RequestParam("competitionId") String competitionId,
            @RequestParam("classLevel") String classLevel,
            @RequestParam("performancePieces") String performancePieces
    )
    {
        return teacherRestUtils.announceStudentToCompetition(
                teacherEmail, studentId, competitionId, classLevel, performancePieces);
    }


    @GetMapping(FIND_TEACHER_APPLICATIONS)
    @Operation(summary = "Find list of teacher's applications to competition.")
    public ApplicationFormsDto findTeacherApplicationsToCompetition(
            @RequestParam("teacherEmail") String teacherEmail,
            @RequestParam("competitionId") String competitionId
    )
    {
        return teacherRestUtils.findTeacherApplicationsToCompetition(teacherEmail, competitionId);
    }

    @GetMapping(FIND_ALL_TEACHERS_APPLICATIONS)
    @Operation(summary = "Find list of all applications to competition made by teachers from headmaster's music school.")
    public ApplicationFormsDto findAllTeachersApplicationsToCompetition(
            @RequestParam("headmasterEmail") String headmasterEmail,
            @RequestParam("competitionId") String competitionId
    )
    {
        Headmaster headmaster = headmasterService.findHeadmasterByEmail(headmasterEmail);
        String musicSchoolId = headmaster.musicSchool().musicSchoolId();

        List<ApplicationFormDto> applicationFormDTOs = applicationFormService.findAllApplicationForms().stream()
                .filter(applicationForm ->
                        competitionId.equals(applicationForm.competition().competitionId()))
                .filter(applicationForm ->
                        musicSchoolId.equals(applicationForm.teacher().musicSchool().musicSchoolId()))
                .map(applicationFormDtoMapper::mapFromDomainToDto)
                .toList();

        return ApplicationFormsDto.builder().applicationFormDtoList(applicationFormDTOs).build();
    }

    @DeleteMapping(ANNOUNCE_STUDENT_CANCEL)
    @Operation(summary = "Cancel student's application to competition.")
    public ResponseEntity<?> announceStudentToCompetitionCancel(
            @RequestParam("competitionId") String competitionId,
            @RequestParam("studentId") String studentId
    )
    {
        return teacherRestUtils.announceStudentToCompetitionCancel(competitionId, studentId);
    }


    @GetMapping(FIND_HEADMASTER_COMPETITIONS)
    @Operation(summary = "Find list of available competitions created by headmaster.")
    public ResponseEntity<CompetitionsDto> findCompetitionsCreatedByHeadmaster(@RequestParam("headmasterEmail") String headmasterEmail)
    {
        List<CompetitionWithLocationDto> competitionDTOs = competitionService.findAllCompetitions().stream()
                .filter(competition -> !competition.finished())
                .filter(competition ->
                        headmasterEmail.equalsIgnoreCase(competition.headmaster().email()))
                .map(competitionDtoMapper::mapFromDomainToDto)
                .toList();

        if (competitionDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        CompetitionsDto competitionsDto = CompetitionsDto.builder()
                .competitionDtoList(competitionDTOs).build();

        return ResponseEntity.ok(competitionsDto);
    }


    @PostMapping(ANNOUNCE_RESULT)
    @Operation(summary = "Create competition results.")
    public ResponseEntity<CompetitionResultsDto> announceCompetitionResults(
            @RequestParam("competitionId") String competitionId,
            @RequestBody CompetitionResultListDto resultListDto
    )
    {
        List<CompetitionResultDto> resultDTOs = createCompetitionResults(competitionId, resultListDto);

        if (resultDTOs.isEmpty()){
            ResponseEntity.of(ProblemDetail.forStatus(HttpStatus.BAD_REQUEST));
        }

        CompetitionResultsDto insertedResultsDto
                = CompetitionResultsDto.builder().competitionResultDtoList(resultDTOs).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(insertedResultsDto);
    }


    @GetMapping(CHECK_RESULT)
    @Operation(summary = "Check teacher's students competition results.")
    public CompetitionResultsDto checkTeacherStudentsResults(
            @RequestParam("competitionId") String competitionId,
            @RequestParam("teacherEmail") String teacherEmail
    )
    {
        return teacherRestUtils.checkTeacherStudentsResults(competitionId, teacherEmail);
    }

    @GetMapping(CHECK_ALL_RESULT)
    @Operation(summary = "Check all competition results.")
    public CompetitionResultsDto checkAllCompetitionResults(
            @RequestParam("competitionId") String competitionId
    )
    {
        List<CompetitionResultDto> resultDTOs = competitionResultService.findAllCompetitionResults().stream()
                .filter(competitionResult ->
                        competitionId.equals(competitionResult.competition().competitionId()))
                .map(competitionResultDtoMapper::mapFromDomainToDto)
                .toList();


        return CompetitionResultsDto.builder().competitionResultDtoList(resultDTOs).build();
    }

    private CompetitionWithLocationDto createCompetitionAtSchool(
            CompetitionWithLocationDto competitionDto,
            String organizerEmail
    )
    {
        Headmaster competitionOrganizer = headmasterService.findHeadmasterByEmail(organizerEmail);
        HeadmasterDto headmasterDto = headmasterDtoMapper.mapFromDomainToDto(competitionOrganizer);
        Competition competition = competitionDtoMapper.mapFromDtoToDomain(
                competitionDto.withCompetitionOrganizer(headmasterDto));

        MusicSchool organizerMusicSchool = competitionOrganizer.musicSchool();
        CompetitionLocation competitionLocation = CompetitionLocation.builder()
                .name(organizerMusicSchool.name())
                .address(organizerMusicSchool.address().withAddressId(null))
                .build();

        Competition insertedCompetition
                = competitionService.insertCompetition(competition
                .withCompetitionLocation(competitionLocation));
        return competitionDtoMapper.mapFromDomainToDto(insertedCompetition);
    }

    private CompetitionWithLocationDto createCompetitionAtOtherPlace(CompetitionWithLocationDto competitionDto,
                                                                     String organizerEmail
    )
    {
        Headmaster competitionOrganizer = headmasterService.findHeadmasterByEmail(organizerEmail);
        Competition competition = competitionDtoMapper.mapFromDtoToDomain(competitionDto);

        Competition insertedCompetition = competitionService.insertCompetition(
                competition.withHeadmaster(competitionOrganizer));
        return competitionDtoMapper.mapFromDomainToDto(insertedCompetition);

    }

    private TeacherDto createTeacher(Headmaster headmaster, String instrument)
    {
        Teacher teacher = Teacher.builder()
                .name(headmaster.name())
                .surname(headmaster.surname())
                .email(headmaster.email())
                .pesel(headmaster.pesel())
                .musicSchool(headmaster.musicSchool())
                .instrument(instrument)
                .build();

        Teacher insertedHeadmasterTeacher = teacherService.insertTeacher(teacher);
        return teacherDtoMapper.mapFromDomainToDto(insertedHeadmasterTeacher);
    }

    private List<CompetitionResultDto> createCompetitionResults(
            String competitionId,
            CompetitionResultListDto resultListDto
    )
    {
        Competition competition = competitionService.findCompetitionById(competitionId);
        List<CompetitionResultListDto.CompetitionResultSupport> competitionResultsSupport
                = resultListDto.getCompetitionResultsSupport();
        List<CompetitionResult> competitionResults = competitionResultsSupport.stream()
                .map(competitionResultSupport ->
                        CompetitionResult.builder()
                        .competition(competitionService.updateCompetitionAfterResults(competition))
                        .student(studentService.findStudentById(competitionResultSupport.getStudentId()))
                        .competitionPlace(competitionResultSupport.getCompetitionPlace())
                        .specialAward(competitionResultSupport.getSpecialAward())
                        .build()).toList();

        List<CompetitionResult> insertedCompetitionResults
                = competitionResultService.insertAllCompetitionResults(competitionResults);

        return insertedCompetitionResults.stream()
                .map(competitionResultDtoMapper::mapFromDomainToDto)
                .toList();
    }
}