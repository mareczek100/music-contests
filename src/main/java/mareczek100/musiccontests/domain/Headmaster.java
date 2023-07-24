package mareczek100.musiccontests.domain;

import lombok.Builder;

import java.util.Set;


@Builder
public record Headmaster(String headmasterId,
                         String name,
                         String surname,
                         String email,
                         String pesel,
                         MusicSchool musicSchool,
                         Set<Competition> competitions) {
}
