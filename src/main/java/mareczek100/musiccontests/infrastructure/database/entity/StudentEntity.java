package mareczek100.musiccontests.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
@EqualsAndHashCode(of = "pesel")
@ToString(exclude = {"competitionResults", "applicationForms"})
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "pesel", unique = true)
    private String pesel;

    @Column(name = "class")
    private Short classYear;

    @Column(name = "education_duration")
    private Short educationDuration;

    @Column(name = "music_school_degree")
    private Degree musicSchoolDegree;

    @ManyToOne
    @JoinColumn(name = "music_school_id")
    private MusicSchoolEntity musicSchool;

    @Column(name = "instrument")
    private String mainInstrument;

    @Column(name = "instrument")
    private String secondInstrument;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToMany(mappedBy = "student")
    private Set<CompetitionResultEntity> competitionResults;

    @OneToMany(mappedBy = "student")
    private Set<ApplicationFormEntity> applicationForms;

    public enum Degree {
        PRIMARY,
        SECONDARY
    }
}