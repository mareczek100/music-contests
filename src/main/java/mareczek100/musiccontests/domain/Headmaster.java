package mareczek100.musiccontests.domain;

import lombok.Builder;
import lombok.With;

import java.util.Set;

@With
@Builder
public record Headmaster(String headmasterId,
                         String name,
                         String surname,
                         String email,
                         String pesel,
                         MusicSchool musicSchool,
                         Set<Competition> competitions) {
}
