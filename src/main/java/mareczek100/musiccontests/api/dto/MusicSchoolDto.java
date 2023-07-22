package mareczek100.musiccontests.api.dto;

import lombok.Builder;

@Builder
public record MusicSchoolDto(String musicSchoolId,
                             String name,
                             String patron,
                             Boolean primaryDegree,
                             Boolean secondaryDegree,
                             AddressDto address,
                             HeadmasterDto headmaster) {
}
