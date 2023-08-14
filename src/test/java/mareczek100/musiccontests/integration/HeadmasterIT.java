package mareczek100.musiccontests.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mareczek100.musiccontests.api.controller.rest_controller.controller_rest_support.AllUsersRestUtils;
import mareczek100.musiccontests.api.controller.rest_controller.controller_rest_support.TeacherRestUtils;
import mareczek100.musiccontests.api.dto.mapper.*;
import mareczek100.musiccontests.business.*;
import mareczek100.musiccontests.business.instrument_storage_service.InstrumentApiService;
import mareczek100.musiccontests.integration.integration_test_support.HeadmasterRestControllerITSupport;
import mareczek100.musiccontests.test_configuration.RestAssuredITConfig;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HeadmasterIT extends RestAssuredITConfig implements HeadmasterRestControllerITSupport {

    private final InstrumentApiService instrumentApiService;
    private final InstrumentDtoMapper instrumentDtoMapper;
    private final CompetitionService competitionService;
    private final CompetitionDtoMapper competitionDtoMapper;
    private final TeacherService teacherService;
    private final TeacherDtoMapper teacherDtoMapper;
    private final HeadmasterService headmasterService;
    private final HeadmasterDtoMapper headmasterDtoMapper;
    private final MusicSchoolService musicSchoolService;
    private final StudentService studentService;
    private final StudentDtoMapper studentDtoMapper;
    private final ApplicationFormService applicationFormService;
    private final ApplicationFormDtoMapper applicationFormDtoMapper;
    private final CompetitionResultService competitionResultService;
    private final CompetitionResultDtoMapper competitionResultDtoMapper;
    private final AllUsersRestUtils allUsersRestUtils;
    private final TeacherRestUtils teacherRestUtils;
    private final ObjectMapper objectMapper;
    private final Flyway flyway;

    @BeforeEach
    void thatSetUpWorksCorrectly() {
        Assertions.assertNotNull(competitionResultService);
        Assertions.assertNotNull(competitionResultDtoMapper);
        Assertions.assertNotNull(competitionService);
        Assertions.assertNotNull(competitionDtoMapper);
        Assertions.assertNotNull(applicationFormService);
        Assertions.assertNotNull(applicationFormDtoMapper);
        Assertions.assertNotNull(instrumentApiService);
        Assertions.assertNotNull(instrumentDtoMapper);
        Assertions.assertNotNull(teacherService);
        Assertions.assertNotNull(teacherDtoMapper);
        Assertions.assertNotNull(headmasterService);
        Assertions.assertNotNull(headmasterDtoMapper);
        Assertions.assertNotNull(musicSchoolService);
        Assertions.assertNotNull(studentService);
        Assertions.assertNotNull(studentDtoMapper);
        Assertions.assertNotNull(allUsersRestUtils);
        Assertions.assertNotNull(teacherRestUtils);
        Assertions.assertNotNull(objectMapper);
        Assertions.assertNotNull(flyway);
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void thatFindAllAvailableInstrumentsWorksCorrectly() {

    }

    @Test
    void thatFindAllAvailableCompetitionCitiesWorksCorrectly() {

    }

    @Test
    void thatFindAllClassLevelsWorksCorrectly() {

    }

    @Test
    void thatCreateCompetitionAtSchoolWorksCorrectly() {

    }

    @Test
    void thatCreateCompetitionAtOtherLocationWorksCorrectly() {

    }

    @Test
    void thatFindAllAvailableCompetitionsWorksCorrectly(
    )
    {

    }

    @Test
    void thatFindAvailableCompetitionsByFiltersWorksCorrectly() {

    }

    @Test
    void thatFindAllAvailableCompetitionsByInstrumentWorksCorrectly() {

    }

    @Test
    void thatFindFinishedCompetitionsByFiltersWorksCorrectly() {

    }

    @Test
    void thatCreateHeadmasterTeacherRightsCorrectlyWorksCorrectly() {

    }

    @Test
    void thatCreateHeadmasterTeacherRightsAccountAlreadyExistsResponseProblemDetailWorksCorrectly() {

    }

    @Test
    void thatFindAllTeachersWorksCorrectly(
    )
    {

    }

    @Test
    void thatFindAllTeacherStudentsWorksCorrectly() {
    }

    @Test
    void thatFindAllCompetitionStudentsExistsWorksCorrectly(
    )
    {

    }

    @Test
    void thatFindAllCompetitionStudentsEmptyListResponseNotFoundStatusWorksCorrectly(
    )
    {

    }

    @Test
    void thatFindAllSchoolStudentsWorksCorrectly() {

    }

    @Test
    void thatAnnounceStudentToCompetitionWorksCorrectly() {

    }

    @Test
    void thatFindTeacherApplicationsToCompetitionWorksCorrectly() {

    }

    @Test
    void thatFindAllTeachersApplicationsToCompetitionWorksCorrectly() {

    }

    @Test
    void thatAnnounceStudentToCompetitionCancelWorksCorrectly() {

    }

    @Test
    void thatFindCompetitionsCreatedByHeadmasterWorksCorrectly() {

    }

    @Test
    void thatAnnounceCompetitionResultsWorksCorrectly() {

    }

    @Test
    void thatCheckTeacherStudentsResultsWorksCorrectly() {

    }

    @Test
    void thatCheckAllCompetitionResultsWorksCorrectly() {

    }
}
