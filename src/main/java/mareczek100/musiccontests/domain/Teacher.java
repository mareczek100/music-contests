package mareczek100.musiccontests.domain;

import lombok.Builder;
import lombok.With;

import java.util.Set;

@With
@Builder
public record Teacher(String teacherId,
                      String name,
                      String surname,
                      String email,
                      String pesel,
                      MusicSchool musicSchool,
                      String instrument,
                      Set<Student> students,
                      Set<ApplicationForm> applicationForms) {
}
