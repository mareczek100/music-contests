package mareczek100.musiccontests.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instrument")
@EqualsAndHashCode(of = "name")
@ToString(exclude = {"studentMainInstruments", "studentSecondInstruments", "teachers", "competitions"})
public class InstrumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "instrument_id")
    private String instrumentId;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private InstrumentCategoryEntity category;

    @OneToMany(mappedBy = "mainInstrument")
    private Set<StudentEntity> studentMainInstruments;

    @OneToMany(mappedBy = "secondInstrument")
    private Set<StudentEntity> studentSecondInstruments;

    @OneToMany(mappedBy = "instrument")
    private Set<TeacherEntity> teachers;

    @OneToMany(mappedBy = "instrument")
    private Set<CompetitionEntity> competitions;
}
