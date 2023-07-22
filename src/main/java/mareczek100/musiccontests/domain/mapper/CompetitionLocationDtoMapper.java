package mareczek100.musiccontests.domain.mapper;

import mareczek100.musiccontests.api.dto.CompetitionLocationDto;
import mareczek100.musiccontests.domain.CompetitionLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetitionLocationDtoMapper {

    @Mapping(target = "customers", ignore = true)
    CompetitionLocation mapFromDtoToDomain(CompetitionLocationDto competitionLocationDto);
    @Mapping(target = "customers", ignore = true)
    CompetitionLocationDto mapFromDomainToDto(CompetitionLocation competitionLocation);
}
