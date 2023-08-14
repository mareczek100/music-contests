package mareczek100.musiccontests.integration.integration_test_support;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import mareczek100.musiccontests.api.dto.ApplicationFormDto;
import mareczek100.musiccontests.api.dto.CompetitionWithLocationDto;
import mareczek100.musiccontests.api.dto.TeacherDto;
import mareczek100.musiccontests.api.dto.dto_class_support.CompetitionResultListDto;
import mareczek100.musiccontests.api.dto.dto_rest_support.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static mareczek100.musiccontests.api.controller.rest_controller.HeadmasterRestController.*;

public interface HeadmasterRestControllerITSupport {

    RequestSpecification requestSpecification();

    default InstrumentsDto findAllAvailableInstruments(){
        return requestSpecification()
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_ALL_INSTRUMENTS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(InstrumentsDto.class);

    }
    default CitiesDto findAllAvailableCompetitionCities(){
        return requestSpecification()
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_ALL_CITIES)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CitiesDto.class);

    }
    default ClassLevels findAllClassLevels(){
        return requestSpecification()
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_AVAILABLE_CLASS_LEVELS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ClassLevels.class);
    }

    default CompetitionWithLocationDto createCompetitionAtSchool(
            String headmasterOrganizerEmail,
            String competitionName,
            String competitionInstrument,
            Boolean competitionOnline,
            Boolean competitionPrimaryDegree,
            Boolean competitionSecondaryDegree,
            LocalDateTime competitionBeginning,
            LocalDateTime competitionEnd,
            LocalDateTime competitionResultAnnouncement,
            LocalDateTime competitionApplicationDeadline,
            String competitionRequirementsDescription
    )
    {
        return requestSpecification()
                .given()
                .params("headmasterOrganizerEmail", headmasterOrganizerEmail,
                        "competitionName", competitionName,
                        "competitionInstrument", competitionInstrument,
                        "competitionOnline", competitionOnline,
                        "competitionPrimaryDegree", competitionPrimaryDegree,
                        "competitionSecondaryDegree", competitionSecondaryDegree,
                        "competitionBeginning", competitionBeginning,
                        "competitionEnd", competitionEnd,
                        "competitionResultAnnouncement", competitionResultAnnouncement,
                        "competitionApplicationDeadline", competitionApplicationDeadline,
                        "competitionRequirementsDescription", competitionRequirementsDescription)
                .when()
                .post(HEADMASTER_REST_MAIN_PAGE + CREATE_COMPETITION_AT_SCHOOL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract()
                .as(CompetitionWithLocationDto.class);
    }

    default CompetitionWithLocationDto createCompetitionAtOtherLocation(
            CompetitionWithLocationDto competitionDto,
            String headmasterOrganizerEmail
    )
    {
        return requestSpecification()
                .given()
                .body(competitionDto)
                .param("headmasterOrganizerEmail", headmasterOrganizerEmail)
                .when()
                .post(HEADMASTER_REST_MAIN_PAGE + CREATE_COMPETITION_AT_OTHER_LOCATION)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract()
                .as(CompetitionWithLocationDto.class);

    }

    default CompetitionsDto findAllAvailableCompetitions(){
        return requestSpecification()
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_ALL_COMPETITIONS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CompetitionsDto.class);
    }
    default CompetitionsDto findAvailableCompetitionsByFilters(
            String competitionInstrument,
            Boolean competitionOnline,
            Boolean competitionPrimaryDegree,
            Boolean competitionSecondaryDegree,
            String competitionCity
    )
    {
        return requestSpecification()
                .given()
                .params(Map.of(
                        "competitionInstrument", competitionInstrument,
                        "competitionOnline", competitionOnline,
                        "competitionPrimaryDegree", competitionPrimaryDegree,
                        "competitionSecondaryDegree", competitionSecondaryDegree,
                        "competitionCity",competitionCity
                ))
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_AVAILABLE_COMPETITIONS_BY_FILTERS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CompetitionsDto.class);
    }
    default CompetitionsDto findAllAvailableCompetitionsByInstrument(
            String competitionInstrument
    )
    {
        return requestSpecification()
                .given()
                .param("competitionInstrument", competitionInstrument)
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_AVAILABLE_COMPETITIONS_BY_INSTRUMENT)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CompetitionsDto.class);
    }

    default CompetitionsDto findFinishedCompetitionsByFilters(
            LocalDate competitionDateFrom,
            LocalDate competitionDateTo,
            String competitionCity
    )
    {
        return requestSpecification()
                .given()
                .params(Map.of(
                        "competitionDateFrom", competitionDateFrom,
                        "competitionDateTo", competitionDateTo,
                        "competitionCity",competitionCity
                ))
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_FINISHED_COMPETITIONS_BY_FILTERS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CompetitionsDto.class);
    }

    default TeacherDto createHeadmasterTeacherRightsCorrectly(
            String headmasterEmail,
            String instrument
    )
    {
        return requestSpecification()
                .given()
                .param("instrument", instrument)
                .param("headmasterEmail", headmasterEmail)
                .when()
                .post(HEADMASTER_REST_MAIN_PAGE + CREATE_TEACHER_RIGHTS)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract()
                .as(TeacherDto.class);

    }
    default Response createHeadmasterTeacherRightsAccountAlreadyExistsResponseProblemDetail(
            String headmasterEmail,
            String instrument
    )
    {
        return requestSpecification()
                .given()
                .param("instrument", instrument)
                .param("headmasterEmail", headmasterEmail)
                .when()
                .post(HEADMASTER_REST_MAIN_PAGE + CREATE_TEACHER_RIGHTS)
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .and()
                .extract()
                .response();

    }

    default TeachersDto findAllTeachers(String headmasterEmail) {
        return requestSpecification()
                .given()
                .param("headmasterEmail", headmasterEmail)
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_ALL_TEACHERS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(TeachersDto.class);

    }

    default StudentsDto findAllTeacherStudents(
            String teacherEmail
    )
    {
        return requestSpecification()
                .given()
                .param("teacherEmail", teacherEmail)
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_ALL_TEACHER_STUDENTS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(StudentsDto.class);
    }

    default StudentsDto findAllCompetitionStudentsExists(String competitionId) {
        return requestSpecification()
                .given()
                .param("competitionId", competitionId)
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_ALL_COMPETITION_STUDENTS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(StudentsDto.class);

    }

    default Response findAllCompetitionStudentsEmptyListResponseNotFoundStatus(String competitionId) {
        return requestSpecification()
                .given()
                .param("competitionId", competitionId)
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_ALL_COMPETITION_STUDENTS)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .and()
                .extract()
                .response();
    }

    default StudentsDto findAllSchoolStudents(String headmasterEmail) {
        return requestSpecification()
                .given()
                .param("headmasterEmail", headmasterEmail)
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_ALL_SCHOOL_STUDENTS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(StudentsDto.class);
    }

    default ApplicationFormDto announceStudentToCompetition(
            String teacherEmail,
            String studentId,
            String competitionId,
            String classLevel,
            String performancePieces
    )
    {
        return requestSpecification()
                .given()
                .params(Map.of(
                        "teacherEmail", teacherEmail,
                        "studentId", studentId,
                        "competitionId", competitionId,
                        "classLevel", classLevel,
                        "performancePieces", performancePieces
                ))
                .when()
                .post(HEADMASTER_REST_MAIN_PAGE + ANNOUNCE_STUDENT_TO_COMPETITION)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ApplicationFormDto.class);
    }

    default ApplicationFormsDto findTeacherApplicationsToCompetition(
            String teacherEmail,
            String competitionId
    )
    {
        return requestSpecification()
                .given()
                .params(Map.of(
                        "teacherEmail", teacherEmail,
                        "competitionId", competitionId
                ))
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_TEACHER_APPLICATIONS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ApplicationFormsDto.class);
    }

    default ApplicationFormsDto findAllTeachersApplicationsToCompetition(
            String teacherEmail,
            String competitionId
    )
    {
        return requestSpecification()
                .given()
                .param("teacherEmail", teacherEmail)
                .param("competitionId", competitionId)
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_ALL_TEACHERS_APPLICATIONS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(ApplicationFormsDto.class);

    }

    default ResponseEntity<?> announceStudentToCompetitionCancel(
            String competitionId,
            String studentId
    )
    {
        return requestSpecification()
                .given()
                .param("studentId", studentId)
                .param("competitionId", competitionId)
                .when()
                .delete(HEADMASTER_REST_MAIN_PAGE + ANNOUNCE_STUDENT_CANCEL)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .and()
                .extract()
                .as(ResponseEntity.class);
    }

    default CompetitionsDto findCompetitionsCreatedByHeadmaster(String headmasterEmail) {
        return requestSpecification()
                .given()
                .param("headmasterEmail", headmasterEmail)
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + FIND_HEADMASTER_COMPETITIONS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CompetitionsDto.class);
    }

    default CompetitionResultsDto announceCompetitionResults(
            String competitionId,
            CompetitionResultListDto resultListDto
    )
    {
        return requestSpecification()
                .given()
                .body(resultListDto)
                .param("competitionId", competitionId)
                .when()
                .post(HEADMASTER_REST_MAIN_PAGE + ANNOUNCE_RESULT)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CompetitionResultsDto.class);
    }

    default CompetitionResultsDto checkTeacherStudentsResults(
            String competitionId,
            String teacherEmail
    )
    {
        return requestSpecification()
                .given()
                .param("competitionId", competitionId)
                .param("teacherEmail", teacherEmail)
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + CHECK_RESULT)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CompetitionResultsDto.class);
    }

    default CompetitionResultsDto checkAllCompetitionResults(String competitionId) {
        return requestSpecification()
                .given()
                .param("competitionId", competitionId)
                .when()
                .get(HEADMASTER_REST_MAIN_PAGE + CHECK_ALL_RESULT)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CompetitionResultsDto.class);
    }

}
