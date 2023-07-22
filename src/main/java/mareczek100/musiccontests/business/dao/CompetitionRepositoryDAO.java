package mareczek100.musiccontests.business.dao;

import mareczek100.musiccontests.domain.Competition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepositoryDAO {

    Competition insertCompetition(Competition competition);

    List<Competition> findAllCompetitions();

    List<Competition> findCompetitionByInstrument(String instrument);

    List<Competition> findCompetitionByOnline(String online);

    List<Competition> findCompetitionByPrimaryDegree(String primaryDegree);

    List<Competition> findCompetitionBySecondaryDegree(String secondaryDegree);

    List<Competition> findCompetitionByFilters(
            String instrument, Boolean online, Boolean primaryDegree, Boolean secondaryDegree);
}
