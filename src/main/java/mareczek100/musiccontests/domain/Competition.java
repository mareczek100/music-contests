package mareczek100.musiccontests.domain;

import lombok.Builder;
import mareczek100.musiccontests.domain.instrument_storage_api.Instrument;

import java.time.OffsetDateTime;
import java.util.Set;

@Builder
public record Competition (String competitionId,
                           String name,
                           Instrument instrument,
                           Boolean online,
                           Boolean primaryDegree,
                           Boolean secondaryDegree,
                           OffsetDateTime beginning,
                           OffsetDateTime resultAnnouncement,
                           String requirementsDescription,
                           Headmaster headmaster,
                           CompetitionLocation competitionLocation,
                           Set<ApplicationForm> applicationForms,
                           Set<CompetitionResult> competitionResults){
}
