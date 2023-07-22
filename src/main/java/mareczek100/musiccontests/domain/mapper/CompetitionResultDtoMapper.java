package mareczek100.musiccontests.domain.mapper;

import mareczek100.musiccontests.api.dto.CompetitionResultDto;
import mareczek100.musiccontests.domain.CompetitionResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetitionResultDtoMapper {

    @Mapping(target = "customers", ignore = true)
    CompetitionResult mapFromDtoToDomain(CompetitionResultDto competitionResultDto);
    @Mapping(target = "customers", ignore = true)
    CompetitionResultDto mapFromDomainToDto(CompetitionResult competitionResult);
}
