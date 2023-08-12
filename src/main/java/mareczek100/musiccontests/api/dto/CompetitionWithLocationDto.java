package mareczek100.musiccontests.api.dto;

import lombok.Builder;
import lombok.With;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@With
@Builder
public record CompetitionWithLocationDto(String competitionId,
                                         String competitionName,
                                         String competitionInstrument,
                                         Boolean competitionOnline,
                                         Boolean competitionPrimaryDegree,
                                         Boolean competitionSecondaryDegree,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
                                         LocalDateTime competitionBeginning,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
                                         LocalDateTime competitionEnd,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
                                         LocalDateTime competitionResultAnnouncement,
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
                                         LocalDateTime competitionApplicationDeadline,
                                         String competitionRequirementsDescription,
                                         HeadmasterDto competitionOrganizer,
                                         String competitionLocationName,
                                         String addressCountry,
                                         String addressCity,
                                         String addressPostalCode,
                                         String addressStreet,
                                         String addressBuildingNumber,
                                         String addressAdditionalInfo,
                                         Boolean competitionFinished)
{
}
