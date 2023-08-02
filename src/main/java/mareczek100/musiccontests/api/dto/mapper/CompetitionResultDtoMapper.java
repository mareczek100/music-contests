package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.CompetitionResultDto;
import mareczek100.musiccontests.domain.CompetitionResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CompetitionDtoMapper.class)
public interface CompetitionResultDtoMapper {

    @Mapping(source = "competitionResultDto.competition", target = "competition",
            qualifiedByName = "competitionMapFromDtoToDomain")
    CompetitionResult mapFromDtoToDomain(CompetitionResultDto competitionResultDto);
    @Mapping(source = "competitionResult.competition", target = "competition",
            qualifiedByName = "competitionMapFromDomainToDto")
    CompetitionResultDto mapFromDomainToDto(CompetitionResult competitionResult);
}
