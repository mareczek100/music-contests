package mareczek100.musiccontests.api.dto;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record CompetitionDto(String competitionId,
                             String name,
                             String instrument,
                             Boolean online,
                             Boolean primaryDegree,
                             Boolean secondaryDegree,
                             OffsetDateTime beginning,
                             OffsetDateTime resultAnnouncement,
                             String requirementsDescription,
                             HeadmasterDto headmaster,
                             CompetitionLocationDto competitionLocation){
}
