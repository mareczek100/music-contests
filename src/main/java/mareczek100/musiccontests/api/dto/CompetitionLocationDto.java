package mareczek100.musiccontests.api.dto;

import lombok.Builder;

@Builder
public record CompetitionLocationDto(String competitionLocationId,
                                     String name,
                                     AddressDto address) {
}
