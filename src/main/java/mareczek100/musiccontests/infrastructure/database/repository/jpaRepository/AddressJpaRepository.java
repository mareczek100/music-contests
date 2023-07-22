package mareczek100.musiccontests.infrastructure.database.repository.jpaRepository;

import mareczek100.musiccontests.infrastructure.database.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, String> {

    @Query("""
    SELECT ms.address FROM MusicSchoolEntity ms
    JOIN FETCH ms.headmaster head
    WHERE head.email = :email
    """)
    Optional<AddressEntity> findAddressByHeadmasterEmail(@Param("email") String email);
}
