package mareczek100.musiccontests.business.dao;

import mareczek100.musiccontests.domain.CompetitionLocation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionLocationRepositoryDAO {
    CompetitionLocation insertCompetitionLocation(CompetitionLocation competitionLocation);

    List<CompetitionLocation> findAllCompetitionLocations();
}



