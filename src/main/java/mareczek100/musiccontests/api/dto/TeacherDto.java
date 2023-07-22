package mareczek100.musiccontests.api.dto;

import lombok.Builder;

@Builder
public record TeacherDto(String teacherId,
                         String name,
                         String surname,
                         String email,
                         String pesel,
                         MusicSchoolDto musicSchool,
                         InstrumentDto instrument) {
}