package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.infrastructure.database.entity.HeadmasterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HeadmasterEntityMapper {

    
    Headmaster mapFromEntityToDomain(HeadmasterEntity headmasterEntity);
    
    HeadmasterEntity mapFromDomainToEntity(Headmaster headmaster);
}
