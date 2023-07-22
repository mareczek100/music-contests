package mareczek100.musiccontests.api.dto;

import lombok.Builder;


@Builder
public record HeadmasterDto(String headmasterId,
                            String name,
                            String surname,
                            String email,
                            String pesel,
                            MusicSchoolDto musicSchool) {
}
