package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.ApplicationFormDto;
import mareczek100.musiccontests.domain.ApplicationForm;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationFormDtoMapper {

    ApplicationForm mapFromDtoToDomain(ApplicationFormDto applicationFormDto);
    
    ApplicationFormDto mapFromDomainToDto(ApplicationForm applicationForm);
}
