package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.CompetitionResult;
import mareczek100.musiccontests.infrastructure.database.entity.CompetitionResultEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetitionResultEntityMapper {

    
    CompetitionResult mapFromEntityToDomain(CompetitionResultEntity competitionResultEntity);
    
    CompetitionResultEntity mapFromDomainToEntity(CompetitionResult competitionResult);
}
