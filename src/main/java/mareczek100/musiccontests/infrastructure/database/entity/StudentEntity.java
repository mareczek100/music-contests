package mareczek100.musiccontests.infrastructure.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
@EqualsAndHashCode(of = "pesel", callSuper = false)
@ToString(exclude = {"competitionResults", "applicationForms"})
public non-sealed class StudentEntity extends MusicContestsPortalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "pesel", unique = true)
    private String pesel;

    @Column(name = "class")
    private Short classYear;

    @Column(name = "education_duration")
    private Short educationDuration;

    @Enumerated(EnumType.STRING)
    @Column(name = "music_school_degree")
    private Degree musicSchoolDegree;

    @ManyToOne
    @JoinColumn(name = "music_school_id")
    private MusicSchoolEntity musicSchool;

    @Column(name = "main_instrument")
    private String mainInstrument;

    @Column(name = "second_instrument")
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