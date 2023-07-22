package mareczek100.musiccontests.business.dao;

import mareczek100.musiccontests.domain.Headmaster;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeadmasterRepositoryDAO {


    Headmaster insertHeadmaster(Headmaster headmaster);

    List<Headmaster> findAllHeadmasters();

    Optional<Headmaster> findHeadmasterByEmail(String email);
}
