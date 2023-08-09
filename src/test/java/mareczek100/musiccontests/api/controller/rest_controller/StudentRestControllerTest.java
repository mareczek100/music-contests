package mareczek100.musiccontests.api.controller.rest_controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
@WebMvcTest(controllers = StudentRestController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class StudentRestControllerTest {

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