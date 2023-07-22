package mareczek100.musiccontests.api.dto;

import lombok.Builder;


@Builder
public record AddressDto(String addressId,
                         String country,
                         String city,
                         String postalCode,
                         String street,
                         String buildingNumber,
                         String additionalInfo){
}
