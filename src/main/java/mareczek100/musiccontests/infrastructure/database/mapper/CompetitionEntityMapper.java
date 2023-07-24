package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.Competition;
import mareczek100.musiccontests.infrastructure.database.entity.CompetitionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetitionEntityMapper {

    
    Competition mapFromEntityToDomain(CompetitionEntity competitionEntity);
    
    CompetitionEntity mapFromDomainToEntity(Competition competition);
}
