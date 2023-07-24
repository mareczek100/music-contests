package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.ApplicationForm;
import mareczek100.musiccontests.infrastructure.database.entity.ApplicationFormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationFormEntityMapper {

    
    ApplicationForm mapFromEntityToDomain(ApplicationFormEntity applicationFormEntity);
    
    ApplicationFormEntity mapFromDomainToEntity(ApplicationForm applicationForm);
}
