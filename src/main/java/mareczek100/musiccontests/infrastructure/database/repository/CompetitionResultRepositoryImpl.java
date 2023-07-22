package mareczek100.musiccontests.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.CompetitionResultRepositoryDAO;
import mareczek100.musiccontests.domain.CompetitionResult;
import mareczek100.musiccontests.infrastructure.database.entity.CompetitionResultEntity;
import mareczek100.musiccontests.infrastructure.database.mapper.CompetitionResultEntityMapper;
import mareczek100.musiccontests.infrastructure.database.repository.jpaRepository.CompetitionResultJpaRepository;

import java.util.List;

@AllArgsConstructor
public class CompetitionResultRepositoryImpl implements CompetitionResultRepositoryDAO {

    private final CompetitionResultJpaRepository competitionResultJpaRepository;
    private final CompetitionResultEntityMapper competitionResultEntityMapper;

    @Override
    public CompetitionResult insertCompetitionResult(CompetitionResult competitionResult)
    {
        CompetitionResultEntity competitionResultEntity
                = competitionResultEntityMapper.mapFromDomainToEntity(competitionResult);
        CompetitionResultEntity savedCompetitionResultEntity
                = competitionResultJpaRepository.saveAndFlush(competitionResultEntity);
        return competitionResultEntityMapper.mapFromEntityToDomain(savedCompetitionResultEntity);
    }

    @Override
    public List<CompetitionResult> findAllCompetitionResults()
    {
        return competitionResultJpaRepository.findAll().stream()
                .map(competitionResultEntityMapper::mapFromEntityToDomain)
                .toList();
    }
}
