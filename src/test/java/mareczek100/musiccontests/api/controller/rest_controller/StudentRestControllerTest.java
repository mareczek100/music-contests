package mareczek100.musiccontests.api.controller.rest_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import mareczek100.musiccontests.api.dto.mapper.*;
import mareczek100.musiccontests.business.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = StudentRestController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class StudentRestControllerTest {

    @MockBean
    private final CompetitionService competitionService;
    @MockBean
    private final CompetitionDtoMapper competitionDtoMapper;
    @MockBean
    private final TeacherService teacherService;
    @MockBean
    private final TeacherDtoMapper teacherDtoMapper;
    @MockBean
    private final HeadmasterService headmasterService;
    @MockBean
    private final StudentService studentService;
    @MockBean
    private final StudentDtoMapper studentDtoMapper;
    @MockBean
    private final ApplicationFormService applicationFormService;
    @MockBean
    private final ApplicationFormDtoMapper applicationFormDtoMapper;
    @MockBean
    private final CompetitionResultService competitionResultService;
    @MockBean
    private final CompetitionResultDtoMapper competitionResultDtoMapper;

    private final ObjectMapper objectMapper;
    private final MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        Assertions.assertNotNull(competitionService);
        Assertions.assertNotNull(competitionDtoMapper);
        Assertions.assertNotNull(teacherService);
        Assertions.assertNotNull(teacherDtoMapper);
        Assertions.assertNotNull(headmasterService);
        Assertions.assertNotNull(studentService);
        Assertions.assertNotNull(studentDtoMapper);
        Assertions.assertNotNull(applicationFormService);
        Assertions.assertNotNull(applicationFormDtoMapper);
        Assertions.assertNotNull(competitionResultService);
        Assertions.assertNotNull(competitionResultDtoMapper);
        Assertions.assertNotNull(objectMapper);
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    void findAllAvailableInstruments() {
    }

    @Test
    void findAllAvailableCompetitionCities() {
    }

    @Test
    void findAllAvailableCompetitions() {
    }

    @Test
    void findAllAvailableCompetitionsByInstrument() {
    }

    @Test
    void findAvailableCompetitionsByFilters() {
    }

    @Test
    void findFinishedStudentCompetitionsByFilters() {
    }

    @Test
    void checkStudentResult() {
    }
}