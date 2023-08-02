package mareczek100.musiccontests.api.dto;

import lombok.Builder;
import lombok.With;
import mareczek100.musiccontests.domain.enums.ClassLevel;
import mareczek100.musiccontests.domain.enums.Degree;
import mareczek100.musiccontests.domain.enums.EducationProgram;

@With
@Builder
public record StudentDto(String studentId,
                         String name,
                         String surname,
                         String email,
                         String pesel,
                         ClassLevel classYear,
                         EducationProgram educationDuration,
                         Degree musicSchoolDegree,
                         MusicSchoolWithAddressDto musicSchool,
                         String mainInstrument,
                         String secondInstrument,
                         TeacherDto teacher) {
}