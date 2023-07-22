package mareczek100.musiccontests.domain.mapper;

import mareczek100.musiccontests.api.dto.ApplicationFormDto;
import mareczek100.musiccontests.domain.ApplicationForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationFormDtoMapper {

    @Mapping(target = "customers", ignore = true)
    ApplicationForm mapFromDtoToDomain(ApplicationFormDto applicationFormDto);
    @Mapping(target = "customers", ignore = true)
    ApplicationFormDto mapFromDomainToDto(ApplicationForm applicationForm);
}
