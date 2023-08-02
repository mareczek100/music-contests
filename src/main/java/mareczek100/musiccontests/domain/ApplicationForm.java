package mareczek100.musiccontests.domain;

import lombok.Builder;
import mareczek100.musiccontests.domain.enums.ClassLevel;

@Builder
public record ApplicationForm(String applicationFormId,
                              Competition competition,
                              Teacher teacher,
                              Student student,
                              ClassLevel classLevel,
                              String performancePieces) {
}