package mareczek100.musiccontests.domain;

import lombok.Builder;


@Builder
public record Address (String addressId,
                       String country,
                       String city,
                       String postalCode,
                       String street,
                       String buildingNumber,
                       String additionalInfo,
                       CompetitionLocation competitionLocation,
                       MusicSchool musicSchool){
}
