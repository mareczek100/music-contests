package mareczek100.musiccontests.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "headmaster")
@EqualsAndHashCode(of = "pesel")
@ToString(exclude = "competitions")
public class HeadmasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "headmaster_id")
    private String headmasterId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "pesel", unique = true)
    private String pesel;

    @OneToOne
    @JoinColumn(name = "music_school_id")
    private MusicSchoolEntity musicSchool;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @OneToMany(mappedBy = "headmaster")
    private Set<CompetitionEntity> competitions;
}
