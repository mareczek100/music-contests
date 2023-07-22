package mareczek100.musiccontests.domain.mapper;

import mareczek100.musiccontests.api.dto.CompetitionDto;
import mareczek100.musiccontests.domain.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetitionDtoMapper {

    @Mapping(target = "customers", ignore = true)
    Competition mapFromDtoToDomain(CompetitionDto competitionDto);
    @Mapping(target = "customers", ignore = true)
    CompetitionDto mapFromDomainToDto(Competition competition);
}
