package mareczek100.musiccontests.api.dto;

import lombok.Builder;
import mareczek100.musiccontests.infrastructure.database.entity.StudentEntity;


@Builder
public record StudentDto(String studentId,
                         String name,
                         String surname,
                         String email,
                         String pesel,
                         Short classYear,
                         Short educationDuration,
                         StudentEntity.Degree musicSchoolDegree,
                         MusicSchoolDto musicSchool,
                         InstrumentDto mainInstrument,
                         InstrumentDto secondInstrument,
                         TeacherDto teacher) {
}