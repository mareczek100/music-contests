package mareczek100.musiccontests.api.dto;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record TeacherDto(String teacherId,
                         String name,
                         String surname,
                         String email,
                         String pesel,
                         MusicSchoolDto musicSchool,
                         String instrument) {
}