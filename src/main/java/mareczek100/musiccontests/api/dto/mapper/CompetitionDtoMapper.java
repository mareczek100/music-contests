package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.CompetitionDto;
import mareczek100.musiccontests.domain.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetitionDtoMapper {

    
    Competition mapFromDtoToDomain(CompetitionDto competitionDto);
    
    CompetitionDto mapFromDomainToDto(Competition competition);
}
