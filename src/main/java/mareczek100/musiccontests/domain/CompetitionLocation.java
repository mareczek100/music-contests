package mareczek100.musiccontests.domain;

import lombok.Builder;

import java.util.Set;

@Builder
public record CompetitionLocation(String competitionLocationId,
                                  String name,
                                  Address address,
                                  Set<Competition> competitions) {
}
