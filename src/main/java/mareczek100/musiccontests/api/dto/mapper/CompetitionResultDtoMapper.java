package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.CompetitionResultDto;
import mareczek100.musiccontests.domain.CompetitionResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetitionResultDtoMapper {

    
    CompetitionResult mapFromDtoToDomain(CompetitionResultDto competitionResultDto);
    
    CompetitionResultDto mapFromDomainToDto(CompetitionResult competitionResult);
}
