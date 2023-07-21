package mareczek100.musiccontests.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "music_competition_role")
@EqualsAndHashCode(of = "roleId")
@ToString(exclude = {"headmasters", "teachers", "students"})
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "role")
    private RoleName role;

    @OneToMany
    private Set<HeadmasterEntity> headmasters;

    @OneToMany
    private Set<TeacherEntity> teachers;

    @OneToMany
    private Set<StudentEntity> students;

    public enum RoleName {
        ADMIN ("ADMIN"),
        HEADMASTER ("HEADMASTER"),
        TEACHER ("TEACHER"),
        STUDENT ("STUDENT");
        RoleName(String role) {
        }
    }
}
