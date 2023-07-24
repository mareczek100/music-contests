package mareczek100.musiccontests.domain;

import lombok.Builder;

import java.util.Set;

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
