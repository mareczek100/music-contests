package mareczek100.musiccontests.domain;

import lombok.Builder;
import lombok.With;
import mareczek100.musiccontests.infrastructure.database.entity.StudentEntity;

import java.util.Set;

@With
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
                      String mainInstrument,
                      String secondInstrument,
                      Teacher teacher,
                      Set<CompetitionResult> competitionResults,
                      Set<ApplicationForm> applicationForms) {
}