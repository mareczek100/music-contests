package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.CompetitionLocationDto;
import mareczek100.musiccontests.domain.CompetitionLocation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetitionLocationDtoMapper {

    
    CompetitionLocation mapFromDtoToDomain(CompetitionLocationDto competitionLocationDto);
    
    CompetitionLocationDto mapFromDomainToDto(CompetitionLocation competitionLocation);
}
