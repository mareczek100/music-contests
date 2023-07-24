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
@Table(name = "teacher")
@EqualsAndHashCode(of = "pesel", callSuper = false)
@ToString(exclude = {"students", "applicationForms"})
public non-sealed class TeacherEntity extends MusicContestsPortalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "teacher_id")
    private String teacherId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "pesel", unique = true)
    private String pesel;

    @ManyToOne
    @JoinColumn(name = "music_school_id")
    private MusicSchoolEntity musicSchool;

    @Column(name = "instrument")
    private String instrument;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToMany(mappedBy = "teacher")
    private Set<StudentEntity> students;

    @OneToMany(mappedBy = "teacher")
    private Set<ApplicationFormEntity> applicationForms;
}
