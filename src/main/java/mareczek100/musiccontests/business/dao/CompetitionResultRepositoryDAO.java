package mareczek100.musiccontests.business.dao;

import mareczek100.musiccontests.domain.CompetitionResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionResultRepositoryDAO {


    CompetitionResult insertCompetitionResult(CompetitionResult competitionResult);

    List<CompetitionResult> findAllCompetitionResults();

}
