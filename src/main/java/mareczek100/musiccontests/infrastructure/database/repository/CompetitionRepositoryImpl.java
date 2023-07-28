package mareczek100.musiccontests.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.CompetitionRepositoryDAO;
import mareczek100.musiccontests.domain.Competition;
import mareczek100.musiccontests.infrastructure.database.entity.CompetitionEntity;
import mareczek100.musiccontests.infrastructure.database.mapper.CompetitionEntityMapper;
import mareczek100.musiccontests.infrastructure.database.repository.jpaRepository.CompetitionJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CompetitionRepositoryImpl implements CompetitionRepositoryDAO {

    private final CompetitionJpaRepository competitionJpaRepository;
    private final CompetitionEntityMapper competitionEntityMapper;

    @Override
    public Competition insertCompetition(Competition competition)
    {
        CompetitionEntity competitionEntity = competitionEntityMapper.mapFromDomainToEntity(competition);
        CompetitionEntity savedCompetitionEntity = competitionJpaRepository.saveAndFlush(competitionEntity);
        return competitionEntityMapper.mapFromEntityToDomain(savedCompetitionEntity);
    }

    @Override
    public List<Competition> findAllCompetitions()
    {
        return competitionJpaRepository.findAll().stream()
                .map(competitionEntityMapper::mapFromEntityToDomain)
                .toList();
    }

    @Override
    public List<Competition> findCompetitionByInstrument(String instrument)
    {
        return competitionJpaRepository.findCompetitionByInstrument(instrument).stream()
                .map(competitionEntityMapper::mapFromEntityToDomain)
                .toList();
    }

    @Override
    public List<Competition> findCompetitionByOnline(Boolean online)
    {
        return competitionJpaRepository.findCompetitionByOnline(online).stream()
                .map(competitionEntityMapper::mapFromEntityToDomain)
                .toList();
    }

    @Override
    public List<Competition> findCompetitionByPrimaryDegree(Boolean primaryDegree)
    {
        return competitionJpaRepository.findCompetitionByPrimaryDegree(primaryDegree).stream()
                .map(competitionEntityMapper::mapFromEntityToDomain)
                .toList();
    }

    @Override
    public List<Competition> findCompetitionBySecondaryDegree(Boolean secondaryDegree)
    {
        return competitionJpaRepository.findCompetitionBySecondaryDegree(secondaryDegree).stream()
                .map(competitionEntityMapper::mapFromEntityToDomain)
                .toList();
    }

    @Override
    public List<Competition> findCompetitionByFilters(String instrument, Boolean online,
                                                      Boolean primaryDegree, Boolean secondaryDegree)
    {
        return competitionJpaRepository.findCompetitionByFilters(
                        instrument, online, primaryDegree, secondaryDegree).stream()
                .map(competitionEntityMapper::mapFromEntityToDomain)
                .toList();
    }
}
