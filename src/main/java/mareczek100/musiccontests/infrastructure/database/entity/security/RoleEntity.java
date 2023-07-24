package mareczek100.musiccontests.infrastructure.database.entity.security;

import jakarta.persistence.*;
import lombok.*;
import mareczek100.musiccontests.infrastructure.database.entity.HeadmasterEntity;
import mareczek100.musiccontests.infrastructure.database.entity.StudentEntity;
import mareczek100.musiccontests.infrastructure.database.entity.TeacherEntity;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "music_contests_role")
@EqualsAndHashCode(of = "roleName")
@ToString(exclude = "users")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private UUID roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", unique = true)
    private RoleName roleName;

    @OneToMany(mappedBy = "role")
    private Set<HeadmasterEntity> headmasters;

    @OneToMany(mappedBy = "role")
    private Set<TeacherEntity> teachers;

    @OneToMany(mappedBy = "role")
    private Set<StudentEntity> students;

    @OneToMany(mappedBy = "role")
    private Set<MusicContestsPortalUserEntity> users;

    public enum RoleName {
        ADMIN,
        HEADMASTER,
        TEACHER,
        STUDENT
    }
}
