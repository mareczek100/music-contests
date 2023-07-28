package mareczek100.musiccontests.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mareczek100.musiccontests.api.dto.CompetitionWithLocationDto;
import mareczek100.musiccontests.api.dto.InstrumentDto;
import mareczek100.musiccontests.api.dto.mapper.CompetitionDtoMapper;
import mareczek100.musiccontests.api.dto.mapper.InstrumentDtoMapper;
import mareczek100.musiccontests.business.CompetitionService;
import mareczek100.musiccontests.business.HeadmasterService;
import mareczek100.musiccontests.business.instrument_storage_service.InstrumentApiService;
import mareczek100.musiccontests.domain.Competition;
import mareczek100.musiccontests.domain.CompetitionLocation;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.domain.MusicSchool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static mareczek100.musiccontests.api.controller.HeadmasterController.HEADMASTER_MAIN_PAGE;

@Controller
@RequestMapping(HEADMASTER_MAIN_PAGE)
@AllArgsConstructor
public class HeadmasterController {

    public static final String HEADMASTER_MAIN_PAGE = "/headmaster";
    public static final String HEADMASTER_COMPETITION_CREATE = "/competition/create";
    public static final String HEADMASTER_COMPETITION_CREATE_LOCATION = "/competition/create/location";
    public static final String HEADMASTER_COMPETITION_ANNOUNCE_RESULT = "/competition/announce";
    public static final String HEADMASTER_COMPETITION_PUT_STUDENT = "/competition/student";
    public static final String HEADMASTER_COMPETITION_CHECK_RESULT = "/competition/check";

    private final CompetitionService competitionService;
    private final HeadmasterService headmasterService;
    private final InstrumentApiService instrumentApiService;
    private final InstrumentDtoMapper instrumentDtoMapper;
    private final CompetitionDtoMapper competitionDtoMapper;

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
            createCompetition(competitionDto, competitionOrganizerEmail);
            return "headmaster/headmaster_competition_create_done";
        }

        return "headmaster/headmaster_competition_create_location";
    }


    @PostMapping(HEADMASTER_COMPETITION_CREATE_LOCATION)
    public String headmasterCreateCompetitionLocation(
            @Valid @ModelAttribute CompetitionWithLocationDto competitionDto,
            @RequestParam("competitionOrganizerEmail") String competitionOrganizerEmail
    ) {
        createCompetition(competitionDto, competitionOrganizerEmail);
        return "headmaster/headmaster_competition_create_done";
    }

    @GetMapping(HEADMASTER_COMPETITION_ANNOUNCE_RESULT)
    public String headmasterAnnounceCompetitionResult(Model model) {


        return "headmaster/headmaster_competition_result";
    }

    @GetMapping(HEADMASTER_COMPETITION_PUT_STUDENT)
    public String headmasterPutUpStudentToCompetition(Model model) {


        return "headmaster/headmaster_competition_put_student";
    }

    @GetMapping(HEADMASTER_COMPETITION_CHECK_RESULT)
    public String headmasterCheckCompetitionResult(Model model) {


        return "headmaster/headmaster_competition_check";
    }

    private CompetitionWithLocationDto createCompetition(CompetitionWithLocationDto competitionDto,
                                                         String organizerEmail)
    {
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

}