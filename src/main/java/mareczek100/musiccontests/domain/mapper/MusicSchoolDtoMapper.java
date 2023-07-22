package mareczek100.musiccontests.domain.mapper;

import mareczek100.musiccontests.api.dto.MusicSchoolDto;
import mareczek100.musiccontests.domain.MusicSchool;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MusicSchoolDtoMapper {

    @Mapping(target = "customers", ignore = true)
    MusicSchool mapFromDtoToDomain(MusicSchoolDto musicSchoolDto);
    @Mapping(target = "customers", ignore = true)
    MusicSchoolDto mapFromDomainToDto(MusicSchool musicSchool);
}
