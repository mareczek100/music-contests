package mareczek100.musiccontests.business;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.CompetitionLocationRepositoryDAO;
import mareczek100.musiccontests.domain.CompetitionLocation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CompetitionLocationService {

    private final CompetitionLocationRepositoryDAO competitionLocationRepositoryDAO;

    @Transactional
    public CompetitionLocation insertCompetitionLocations(CompetitionLocation competitionLocation) {
        return competitionLocationRepositoryDAO.insertCompetitionLocation(competitionLocation);
    }
    @Transactional
    public List<CompetitionLocation> findAllCompetitionLocation() {
        return competitionLocationRepositoryDAO.findAllCompetitionLocations();
    }
}
