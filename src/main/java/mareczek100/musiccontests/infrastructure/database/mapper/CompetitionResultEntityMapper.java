package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.CompetitionResult;
import mareczek100.musiccontests.infrastructure.database.entity.CompetitionResultEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetitionResultEntityMapper {

    @Mapping(target = "customers", ignore = true)
    CompetitionResult mapFromEntityToDomain(CompetitionResultEntity competitionResultEntity);
    @Mapping(target = "customers", ignore = true)
    CompetitionResultEntity mapFromDomainToEntity(CompetitionResult competitionResult);
}
