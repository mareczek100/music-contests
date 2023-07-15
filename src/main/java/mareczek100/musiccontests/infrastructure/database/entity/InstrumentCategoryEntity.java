package mareczek100.musiccontests.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instrument_category")
@EqualsAndHashCode(of = "instrumentCategory")
@ToString(exclude = "instruments")
public class InstrumentCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "instrument_category_id")
    private String instrumentCategoryId;

    @Column(name = "name", unique = true)
    private InstrumentCategory instrumentCategory;

    @OneToMany(mappedBy = "instrument")
    private Set<InstrumentEntity> instruments;

    enum InstrumentCategory {
        STRINGS,
        WOODWIND,
        BRASS,
        PERCUSSION
    }
}
