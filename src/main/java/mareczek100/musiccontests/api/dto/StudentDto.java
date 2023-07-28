package mareczek100.musiccontests.api.dto;

import lombok.Builder;
import lombok.With;
import mareczek100.musiccontests.infrastructure.database.entity.StudentEntity;

@With
@Builder
public record StudentDto(String studentId,
                         String name,
                         String surname,
                         String email,
                         String pesel,
                         Short classYear,
                         Short educationDuration,
                         StudentEntity.Degree musicSchoolDegree,
                         MusicSchoolWithAddressDto musicSchool,
                         String mainInstrument,
                         String secondInstrument,
                         TeacherDto teacher) {
}