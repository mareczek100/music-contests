package mareczek100.musiccontests.domain;

import lombok.Builder;

@Builder
public record ApplicationForm(String applicationFormId,
                              Competition competition,
                              Teacher teacher,
                              Student student,
                              Short classLevel,
                              String performancePieces) {
}
