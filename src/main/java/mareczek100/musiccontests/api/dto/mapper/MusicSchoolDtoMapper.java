package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.MusicSchoolDto;
import mareczek100.musiccontests.domain.MusicSchool;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MusicSchoolDtoMapper {

    
    MusicSchool mapFromDtoToDomain(MusicSchoolDto musicSchoolDto);
    
    MusicSchoolDto mapFromDomainToDto(MusicSchool musicSchool);
}
