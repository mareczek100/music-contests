package mareczek100.musiccontests.api.controller.rest_controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = TeacherRestController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class TeacherAndHeadmasterCommonMethodsRestControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllAvailableInstruments() {
    }

    @Test
    void findAllAvailableCompetitionCities() {
    }

    @Test
    void findAllClassLevels() {
    }

    @Test
    void findAllAvailableCompetitions() {
    }

    @Test
    void findAvailableCompetitionsByFilters() {
    }

    @Test
    void findAllAvailableCompetitionsByInstrument() {
    }

    @Test
    void findFinishedCompetitionsByFilters() {
    }

    @Test
    void findAllTeacherStudents() {
    }

    @Test
    void announceStudentToCompetition() {
    }

    @Test
    void findTeacherApplicationsToCompetition() {
    }

    @Test
    void announceStudentToCompetitionCancel() {
    }

    @Test
    void checkTeacherStudentsResults() {
    }
}