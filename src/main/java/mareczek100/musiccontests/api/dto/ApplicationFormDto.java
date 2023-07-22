package mareczek100.musiccontests.api.dto;

import lombok.Builder;

@Builder
public record ApplicationFormDto(String applicationFormId,
                                 CompetitionDto competition,
                                 TeacherDto teacher,
                                 StudentDto student,
                                 Short classLevel,
                                 String performancePieces) {
}
