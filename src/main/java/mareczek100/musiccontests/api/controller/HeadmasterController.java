package mareczek100.musiccontests.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mareczek100.musiccontests.api.dto.*;
import mareczek100.musiccontests.api.dto.dto_class_support.CompetitionResultListDto;
import mareczek100.musiccontests.api.dto.mapper.*;
import mareczek100.musiccontests.business.*;
import mareczek100.musiccontests.business.instrument_storage_service.InstrumentApiService;
import mareczek100.musiccontests.domain.*;
import mareczek100.musiccontests.domain.enums.ClassLevel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.*;

import static mareczek100.musiccontests.api.controller.HeadmasterController.HEADMASTER_MAIN_PAGE;

@Controller
@AllArgsConstructor
@RequestMapping(HEADMASTER_MAIN_PAGE)
public class HeadmasterController {

    public static final String HEADMASTER_MAIN_PAGE = "/headmaster";
    public static final String HEADMASTER_COMPETITION_CREATE = "/competition/create";
    public static final String HEADMASTER_COMPETITION_CREATE_LOCATION = "/competition/create/location";
    public static final String HEADMASTER_COMPETITION_ANNOUNCE_RESULT = "/competition/result/announce";
    public static final String HEADMASTER_COMPETITION_ANNOUNCE_CANCEL = "/competition/result/announce";
    public static final String HEADMASTER_COMPETITION_RESULT = "/competition/result";
    public static final String HEADMASTER_COMPETITION_STUDENT = "/competition/student";
    public static final String HEADMASTER_COMPETITION_TEACHER = "/competition/teacher";
    public static final String HEADMASTER_COMPETITION_TEACHER_RIGHTS = "/competition/teacher/rights";
    public static final String HEADMASTER_COMPETITION_SELECT_STUDENT = "/competition/student/select";
    public static final String HEADMASTER_COMPETITION_PUT_STUDENT = "/competition/student/put";
    public static final String HEADMASTER_COMPETITION_STUDENT_SEARCH = "/competition/student/search";
    public static final String HEADMASTER_COMPETITION_CHECK = "/competition/check";
    public static final String HEADMASTER_COMPETITION_CHECK_RESULT = "/competition/check/result";

    private final InstrumentApiService instrumentApiService;
    private final ApplicationFormService applicationFormService;
    private final ApplicationFormDtoMapper applicationFormDtoMapper;
    private final CompetitionService competitionService;
    private final CompetitionResultService competitionResultService;
    private final HeadmasterService headmasterService;
    private final MusicSchoolService musicSchoolService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final StudentDtoMapper studentDtoMapper;
    private final TeacherDtoMapper teacherDtoMapper;
    private final HeadmasterDtoMapper headmasterDtoMapper;
    private final InstrumentDtoMapper instrumentDtoMapper;
    private final CompetitionDtoMapper competitionDtoMapper;
    private final CompetitionResultDtoMapper competitionResultDtoMapper;

    @GetMapping
    public String headmasterHomePage() {

        return "headmaster/headmaster";
    }

    @GetMapping(HEADMASTER_COMPETITION_CREATE)
    public String headmasterCreateCompetition(Model model) {
        List<InstrumentDto> instrumentDTOs = instrumentApiService.findAllInstruments().stream()
                .map(instrumentDtoMapper::mapFromDomainToDto)
                .toList();
        CompetitionWithLocationDto competitionDto = CompetitionWithLocationDto.builder().build();

        model.addAttribute("competitionDto", competitionDto);
        model.addAttribute("instrumentDTOs", instrumentDTOs);

        return "headmaster/headmaster_competition_create";
    }

    @PostMapping(HEADMASTER_COMPETITION_CREATE)
    public String headmasterCreateCompetitionProcess(
            @ModelAttribute CompetitionWithLocationDto competitionDto,
            @RequestParam("competitionOrganizerEmail") String competitionOrganizerEmail,
            @RequestParam("competitionSchoolLocation") Boolean competitionSchoolLocation,
            Model model
    ) {
        competitionOrganizerEmail = competitionOrganizerEmail.strip();
        model.addAllAttributes(Map.of(
                "competitionDto", competitionDto,
                "competitionOrganizerEmail", competitionOrganizerEmail
        ));

        if (competitionSchoolLocation) {
            CompetitionWithLocationDto createdCompetitionDto = createCompetition(competitionDto, competitionOrganizerEmail);
            model.addAttribute("createdCompetitionDto", createdCompetitionDto);
            return "headmaster/headmaster_competition_create_done";
        }

        return "headmaster/headmaster_competition_create_location";
    }


    @PostMapping(HEADMASTER_COMPETITION_CREATE_LOCATION)
    public String headmasterCreateCompetitionLocation(
            @Valid @ModelAttribute CompetitionWithLocationDto competitionDto,
            @RequestParam("competitionOrganizerEmail") String competitionOrganizerEmail,
            Model model
    ) {
        CompetitionWithLocationDto createdCompetitionDto = createCompetition(competitionDto, competitionOrganizerEmail);
        model.addAttribute("createdCompetitionDto", createdCompetitionDto);
        return "headmaster/headmaster_competition_create_done";
    }

    @GetMapping(HEADMASTER_COMPETITION_STUDENT)
    public String headmasterStudentHomePage() {

        return "headmaster/headmaster_competition_student";
    }


    @PostMapping(HEADMASTER_COMPETITION_TEACHER)
    public String headmasterCreateTeacherRightsHomePage(
            @RequestParam("headmasterTeacherEmail") String headmasterTeacherEmail,
            Model model) {

        List<InstrumentDto> instrumentDTOs = instrumentApiService.findAllInstruments().stream()
                .map(instrumentDtoMapper::mapFromDomainToDto)
                .toList();
        Headmaster headmaster = headmasterService.findHeadmasterByEmail(headmasterTeacherEmail);
        HeadmasterDto headmasterDto = headmasterDtoMapper.mapFromDomainToDto(headmaster);
        TeacherDto teacherDto = TeacherDto.builder().build();

        model.addAttribute("headmasterDto", headmasterDto);
        model.addAttribute("instrumentDTOs", instrumentDTOs);
        model.addAttribute("headmasterTeacherDto", teacherDto);

        return "headmaster/headmaster_competition_teacher";
    }

    @PostMapping(HEADMASTER_COMPETITION_TEACHER_RIGHTS)
    public String headmasterCreateTeacherRights(@ModelAttribute("headmasterTeacherDto") TeacherDto headmasterTeacherDto,
                                                @RequestParam("musicSchoolId") String musicSchoolId,
                                                Model model) {

        MusicSchool musicSchool = musicSchoolService.findMusicSchoolById(musicSchoolId);
        Teacher headmasterTeacherToSave = teacherDtoMapper.mapFromDtoToDomain(headmasterTeacherDto);
        Teacher insertedHeadmasterTeacher
                = teacherService.insertTeacher(headmasterTeacherToSave.withMusicSchool(musicSchool));
        TeacherDto teacherDto = teacherDtoMapper.mapFromDomainToDto(insertedHeadmasterTeacher);
        model.addAttribute("teacherCreated", true);
        model.addAttribute("headmasterTeacherDto", teacherDto);

        return "headmaster/headmaster_competition_teacher";
    }

    @PostMapping(HEADMASTER_COMPETITION_STUDENT_SEARCH)
    public String headmasterSearchCompetitionToPutStudent(
            @RequestParam("headmasterTeacherEmail") String headmasterTeacherEmail,
            Model model) {
        List<InstrumentDto> instrumentDTOs = instrumentApiService.findAllInstruments().stream()
                .map(instrumentDtoMapper::mapFromDomainToDto)
                .toList();

        List<String> cityDTOs = competitionService.findAllCompetitions().stream()
                .filter(competition -> competition.finished().equals(false))
                .map(Competition::competitionLocation)
                .map(CompetitionLocation::address)
                .map(Address::city)
                .distinct()
                .toList();
        CompetitionWithLocationDto competitionDto = CompetitionWithLocationDto.builder().build();

        model.addAttribute("headmasterTeacherEmail", headmasterTeacherEmail);
        model.addAttribute("instrumentDTOs", instrumentDTOs);
        model.addAttribute("competitionDto", competitionDto);
        model.addAttribute("cityDTOs", cityDTOs);

        return "headmaster/headmaster_competition_search";
    }

    @PostMapping(HEADMASTER_COMPETITION_SELECT_STUDENT)
    public String headmasterSelectStudentToCompetition(
            @RequestParam("headmasterTeacherEmail") String headmasterTeacherEmail,
            @ModelAttribute CompetitionWithLocationDto competitionDto,
            Model model) {
        Teacher headmasterTeacher = teacherService.findTeacherByEmail(headmasterTeacherEmail);
        String teacherId = headmasterTeacher.teacherId();
        List<Student> studentList = studentService.findAllStudents().stream()
                .filter(student ->
                        teacherId.equals(student.teacher().teacherId()))
                .toList();
        List<StudentDto> studentDTOs = studentList.stream()
                .map(studentDtoMapper::mapFromDomainToDto)
                .toList();

        List<Competition> competitionsByFilters = competitionService.findCompetitionsByFilters(
                competitionDto.competitionInstrument(),
                competitionDto.competitionOnline(),
                competitionDto.competitionPrimaryDegree(),
                competitionDto.competitionSecondaryDegree(),
                competitionDto.addressCity());
        List<CompetitionWithLocationDto> competitionDTOs = competitionsByFilters.stream()
                .filter(competition -> !competition.finished())
                .filter(competition -> OffsetDateTime.now().isBefore(competition.applicationDeadline()))
                .map(competitionDtoMapper::mapFromDomainToDto)
                .toList();

        List<ClassLevel> classLevels
                = Arrays.stream(ClassLevel.values())
                .toList();

        model.addAttribute("competitionDTOs", competitionDTOs);
        model.addAttribute("studentDTOs", studentDTOs);
        model.addAttribute("headmasterTeacherEmail", headmasterTeacherEmail);
        model.addAttribute("classLevels", classLevels);

        return "headmaster/headmaster_competition_select_student";
    }

    @PostMapping(HEADMASTER_COMPETITION_PUT_STUDENT)
    public String headmasterPutUpStudentToCompetition(
            @RequestParam("headmasterTeacherEmail") String headmasterTeacherEmail,
            @RequestParam("studentId") String studentId,
            @RequestParam("competitionId") String competitionId,
            @RequestParam("classLevel") String classLevel,
            @RequestParam("performancePieces") String performancePieces,
            Model model) {

        Competition competition = competitionService.findCompetitionById(competitionId);
        Teacher teacher = teacherService.findTeacherByEmail(headmasterTeacherEmail);
        Student student = studentService.findStudentById(studentId);

        boolean studentAlreadyInCompetitionAnyMatch = applicationFormService.findAllApplicationForms().stream()
                .anyMatch(applicationForm ->
                        studentId.equals(applicationForm.student().studentId())
                                && competitionId.equals(applicationForm.competition().competitionId()));
        if (studentAlreadyInCompetitionAnyMatch) {
            throw new RuntimeException("Student [%s] [%s] is already announced to competition [%s]!"
                    .formatted(student.name(), student.surname(), competition.name()));
        }

        ApplicationFormDto applicationFormDto
                = createStudentApplicationForm(competition, teacher, student, classLevel, performancePieces);

        model.addAttribute("applicationFormDto", applicationFormDto);

        return "headmaster/headmaster_competition_put_student";
    }

    @GetMapping(HEADMASTER_COMPETITION_RESULT)
    public String headmasterAnnounceCompetitionResultHomePage(Model model) {
        model.addAttribute("competitionDTOs", Collections.emptyList());
        return "headmaster/headmaster_competition_result";
    }

    @PostMapping(HEADMASTER_COMPETITION_RESULT)
    public String headmasterPickCompetitionToAnnounceResult(
            @RequestParam("headmasterEmail") String headmasterEmail,
            Model model) {
        List<Competition> headmasterCompetitionList = competitionService.findAllCompetitions().stream()
                .filter(competition -> !competition.finished())
                .filter(competition ->
                        headmasterEmail.equalsIgnoreCase(competition.headmaster().email()))
                .toList();
        List<CompetitionWithLocationDto> competitionDTOs = headmasterCompetitionList.stream()
                .map(competitionDtoMapper::mapFromDomainToDto)
                .toList();

        model.addAttribute("competitionDTOs", competitionDTOs);

        return "headmaster/headmaster_competition_result";
    }

    @GetMapping(HEADMASTER_COMPETITION_ANNOUNCE_RESULT)
    public String headmasterAnnounceCompetitionHomePage(
            @RequestParam("competitionId") String competitionId,
            Model model) {
        Competition competition = competitionService.findCompetitionById(competitionId);
        CompetitionWithLocationDto competitionDto = competitionDtoMapper.mapFromDomainToDto(competition);

        List<Student> studentList = applicationFormService.findAllApplicationForms().stream()
                .filter(applicationForm -> competitionId.equals(applicationForm.competition().competitionId()))
                .map(ApplicationForm::student)
                .toList();
        List<StudentDto> studentDTOs = studentList.stream()
                .map(studentDtoMapper::mapFromDomainToDto)
                .toList();

        CompetitionResultListDto resultListDto = CompetitionResultListDto.builder()
                .competitionResultsSupport(new ArrayList<>())
                .build();
        List<CompetitionResultListDto.CompetitionResultSupport> competitionResultsSupport
                = resultListDto.getCompetitionResultsSupport();

        for (int i = 0; i < studentDTOs.size(); i++) {
            competitionResultsSupport.add(CompetitionResultListDto.CompetitionResultSupport.builder().build());
        }

        model.addAttribute("competitionDto", competitionDto);
        model.addAttribute("studentDTOs", studentDTOs);
        model.addAttribute("resultListDto", resultListDto);

        return "headmaster/headmaster_competition_result_announce";
    }

    @PostMapping(HEADMASTER_COMPETITION_ANNOUNCE_RESULT)
    public String headmasterAnnounceCompetitionResult(
            @RequestParam("competitionId") String competitionId,
            @ModelAttribute("resultListDto") CompetitionResultListDto resultListDto,
            Model model) {

        List<CompetitionResultDto> resultDTOs = createCompetitionResults(competitionId, resultListDto);
        String competitionName = resultDTOs.stream()
                .map(result -> result.competition().competitionName())
                .findAny().orElseThrow();

        model.addAttribute("resultDTOs", resultDTOs);
        model.addAttribute("competitionName", competitionName);

        return "headmaster/headmaster_competition_result_announce_done";
    }

    @GetMapping(HEADMASTER_COMPETITION_CHECK)
    public String headmasterCheckCompetitionResultHomePage(Model model)
    {
        List<String> cityDTOs = competitionService.findAllCompetitions().stream()
                .filter(Competition::finished)
                .map(Competition::competitionLocation)
                .map(CompetitionLocation::address)
                .map(Address::city)
                .distinct()
                .toList();

        model.addAttribute("cityDTOs", cityDTOs);

        return "headmaster/headmaster_competition_check";
    }
    @GetMapping(HEADMASTER_COMPETITION_CHECK_RESULT)
    public String headmasterCheckCompetitionResultByFilters(Model model,
                                                            @DateTimeFormat LocalDate competitionFrom,
                                                            @DateTimeFormat LocalDate competitionTo,
                                                            String competitionCity)
    {

        List<CompetitionWithLocationDto> competitionDTOs = competitionService.findAllCompetitions().stream()
                .filter(Competition::finished)
                .filter(competition -> competitionFrom.isBefore(competition.beginning().toLocalDate())
                && competitionTo.isAfter(competition.beginning().toLocalDate()))
                .filter(competition -> competitionCity.equals(competition.competitionLocation().address().city()))
                .map(competitionDtoMapper::mapFromDomainToDto)
                .toList();

        model.addAttribute("competitionDTOs", competitionDTOs);

        return "headmaster/headmaster_competition_check_filter";
    }
    @PostMapping(HEADMASTER_COMPETITION_CHECK_RESULT)
    public String headmasterCheckCompetitionResult(Model model,
                                                   @RequestParam("competitionId") String competitionId)
    {
        Competition competition = competitionService.findCompetitionById(competitionId);
        String competitionName = competition.name();

        List<CompetitionResultDto> resultDTOs = competitionResultService.findAllCompetitionResult().stream()
                .filter(competitionResult ->
                        competitionId.equals(competitionResult.competition().competitionId()))
                .map(competitionResultDtoMapper::mapFromDomainToDto)
                .toList();

        model.addAttribute("resultDTOs", resultDTOs);
        model.addAttribute("competitionName", competitionName);

        return "headmaster/headmaster_competition_check_result";
    }


    private CompetitionWithLocationDto createCompetition(CompetitionWithLocationDto competitionDto,
                                                         String organizerEmail) {
        Headmaster competitionOrganizer = headmasterService.findHeadmasterByEmail(organizerEmail);
        Competition competition = competitionDtoMapper.mapFromDtoToDomain(competitionDto);
        if (Objects.nonNull(competitionDto.competitionLocationName())) {
            Competition insertedCompetition = competitionService.insertCompetition(
                    competition.withHeadmaster(competitionOrganizer));
            return competitionDtoMapper.mapFromDomainToDto(insertedCompetition);
        }
        MusicSchool organizerMusicSchool = competitionOrganizer.musicSchool();
        CompetitionLocation competitionLocation = CompetitionLocation.builder()
                .name(organizerMusicSchool.name())
                .address(organizerMusicSchool.address().withAddressId(null))
                .build();

        Competition insertedCompetition
                = competitionService.insertCompetition(competition
                .withHeadmaster(competitionOrganizer)
                .withCompetitionLocation(competitionLocation));
        return competitionDtoMapper.mapFromDomainToDto(insertedCompetition);
    }

    private ApplicationFormDto createStudentApplicationForm(
            Competition competition, Teacher teacher, Student student,
            String classLevel, String performancePieces) {
        ApplicationForm applicationForm = ApplicationForm.builder()
                .competition(competition)
                .teacher(teacher)
                .student(student)
                .classLevel(ClassLevel.valueOf(classLevel))
                .performancePieces(performancePieces)
                .build();

        ApplicationForm insertedApplicationForm = applicationFormService.insertApplicationForm(applicationForm);
        return applicationFormDtoMapper.mapFromDomainToDto(insertedApplicationForm);
    }

    private List<CompetitionResultDto> createCompetitionResults(String competitionId, CompetitionResultListDto resultListDto) {
        Competition competition = competitionService.findCompetitionById(competitionId);
        List<CompetitionResultListDto.CompetitionResultSupport> competitionResultsSupport
                = resultListDto.getCompetitionResultsSupport();
        List<CompetitionResult> competitionResults = competitionResultsSupport.stream()
                .map(competitionResultSupport -> {
                    return CompetitionResult.builder()
                            .competition(competitionService.updateCompetitionAfterResults(competition))
                            .student(studentService.findStudentById(
                                    competitionResultSupport.getStudentId()))
                            .competitionPlace(competitionResultSupport.getCompetitionPlace())
                            .specialAward(competitionResultSupport.getSpecialAward())
                            .build();
                }).toList();

        List<CompetitionResult> insertedCompetitionResults
                = competitionResultService.insertAllCompetitionResults(competitionResults);

        return insertedCompetitionResults.stream()
                .map(competitionResultDtoMapper::mapFromDomainToDto)
                .toList();
    }

}