package mareczek100.musiccontests.api.controller.rest_controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import mareczek100.musiccontests.api.controller.rest_controller.controller_rest_support.AllUsersRestUtils;
import mareczek100.musiccontests.api.controller.rest_controller.controller_rest_support.ControllerRestSupport;
import mareczek100.musiccontests.api.dto.CompetitionResultDto;
import mareczek100.musiccontests.api.dto.CompetitionWithLocationDto;
import mareczek100.musiccontests.api.dto.dto_rest_support.CitiesDto;
import mareczek100.musiccontests.api.dto.dto_rest_support.CompetitionsDto;
import mareczek100.musiccontests.api.dto.dto_rest_support.InstrumentsDto;
import mareczek100.musiccontests.api.dto.mapper.CompetitionDtoMapper;
import mareczek100.musiccontests.api.dto.mapper.CompetitionResultDtoMapper;
import mareczek100.musiccontests.business.CompetitionResultService;
import mareczek100.musiccontests.domain.CompetitionResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static mareczek100.musiccontests.api.controller.rest_controller.StudentRestController.STUDENT_REST_MAIN_PAGE;

@Validated
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = STUDENT_REST_MAIN_PAGE)
@AllArgsConstructor
public class StudentRestController implements ControllerRestSupport {

    public static final String STUDENT_REST_MAIN_PAGE = "/api/student";
    public static final String FIND_STUDENT_COMPETITIONS = "/competitions/student";

    private final CompetitionDtoMapper competitionDtoMapper;
    private final CompetitionResultService competitionResultService;
    private final CompetitionResultDtoMapper competitionResultDtoMapper;
    private final AllUsersRestUtils allUsersRestUtils;

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

    @GetMapping(FIND_ALL_COMPETITIONS)
    @Operation(summary = "Find list of all available music competitions.")
    public CompetitionsDto findAllAvailableCompetitions()
    {
        return allUsersRestUtils.findAllAvailableCompetitions();
    }
    @GetMapping(FIND_ALL_COMPETITIONS_BY_INSTRUMENT)
    @Operation(summary = "Find list of all available music competitions for chosen instrument.")
    public CompetitionsDto findAllAvailableCompetitionsByInstrument(
            @RequestParam("competitionInstrument") String competitionInstrument
    )
    {
        return allUsersRestUtils.findAvailableCompetitionsByInstrument(competitionInstrument);
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

    @GetMapping(FIND_STUDENT_COMPETITIONS)
    @Operation(summary = "Find competitions in which student participated.")
    public CompetitionsDto findFinishedStudentCompetitionsByFilters(
            @RequestParam("competitionDateFrom") @DateTimeFormat LocalDate competitionDateFrom,
            @RequestParam("competitionDateTo") @DateTimeFormat LocalDate competitionDateTo,
            @RequestParam("competitionCity") String competitionCity,
            @RequestParam("studentPesel") String studentPesel
    )
    {

        List<CompetitionWithLocationDto> competitionDTOs = competitionResultService.findAllCompetitionResult().stream()
                .filter(competitionResult -> competitionResult.competition().finished())
                .filter(competitionResult ->
                        BCrypt.checkpw(studentPesel, competitionResult.student().pesel()))
                .map(CompetitionResult::competition)
                .filter(competition -> competitionDateFrom.isBefore(competition.beginning().toLocalDate())
                        && competitionDateTo.isAfter(competition.beginning().toLocalDate()))
                .filter(competition -> competitionCity.equals(competition.competitionLocation().address().city()))
                .map(competitionDtoMapper::mapFromDomainToDto)
                .toList();

        return CompetitionsDto.builder().competitionDtoList(competitionDTOs).build();
    }

    @GetMapping(CHECK_RESULT)
    @Operation(summary = "Find student's result in competition.")
    public ResponseEntity<CompetitionResultDto> checkStudentResult(
            @RequestParam("competitionId") String competitionId,
            @RequestParam("studentPesel") String studentPesel
    )
    {
        List<CompetitionResultDto> resultDto = competitionResultService.findAllCompetitionResult().stream()
                .filter(competitionResult ->
                        competitionId.equals(competitionResult.competition().competitionId()))
                .filter(competitionResult ->
                        BCrypt.checkpw(studentPesel, competitionResult.student().pesel()))
                .map(competitionResultDtoMapper::mapFromDomainToDto)
                .toList();

        if (resultDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resultDto.get(0));
    }

}