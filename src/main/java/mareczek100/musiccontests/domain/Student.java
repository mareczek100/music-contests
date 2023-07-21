package mareczek100.musiccontests.domain;

import lombok.Builder;
import mareczek100.musiccontests.domain.instrument_storage_api.Instrument;
import mareczek100.musiccontests.infrastructure.database.entity.RoleEntity;
import mareczek100.musiccontests.infrastructure.database.entity.StudentEntity;

import java.util.Set;


@Builder
public record Student(String studentId,
                      String name,
                      String surname,
                      String email,
                      String pesel,
                      Short classYear,
                      Short educationDuration,
                      StudentEntity.Degree musicSchoolDegree,
                      MusicSchool musicSchool,
                      Instrument mainInstrument,
                      Instrument secondInstrument,
                      Teacher teacher,
                      RoleEntity.RoleName role,
                      Set<CompetitionResult> competitionResults,
                      Set<ApplicationForm> applicationForms) {
}