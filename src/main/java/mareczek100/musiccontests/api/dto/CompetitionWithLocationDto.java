package mareczek100.musiccontests.api.dto;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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
                                         LocalDateTime competitionResultAnnouncement,
                                         String competitionRequirementsDescription,
                                         HeadmasterDto competitionOrganizer,
                                         String competitionLocationName,
                                         String addressCountry,
                                         String addressCity,
                                         String addressPostalCode,
                                         String addressStreet,
                                         String addressBuildingNumber,
                                         String addressAdditionalInfo)
{
}
