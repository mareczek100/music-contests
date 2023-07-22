package mareczek100.musiccontests.domain;

import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.Set;

@Builder
public record Competition (String competitionId,
                           String name,
                           String instrument,
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
