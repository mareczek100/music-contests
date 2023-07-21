package mareczek100.musiccontests.domain;

import lombok.Builder;
import mareczek100.musiccontests.domain.instrument_storage_api.Instrument;
import mareczek100.musiccontests.infrastructure.database.entity.RoleEntity;

import java.util.Set;

@Builder
public record Teacher(String teacherId,
                      String name,
                      String surname,
                      String email,
                      String pesel,
                      MusicSchool musicSchool,
                      Instrument instrument,
                      RoleEntity.RoleName role,
                      Set<Student> students,
                      Set<ApplicationForm> applicationForms) {
}
