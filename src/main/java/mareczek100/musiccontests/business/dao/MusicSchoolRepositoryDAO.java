package mareczek100.musiccontests.business.dao;

import mareczek100.musiccontests.domain.MusicSchool;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicSchoolRepositoryDAO {


    MusicSchool insertMusicSchool(MusicSchool musicSchool);

    List<MusicSchool> findAllMusicSchools();

    Optional<MusicSchool> findMusicSchoolByPatron(String patron);
}
