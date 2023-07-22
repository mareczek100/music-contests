package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.infrastructure.database.entity.HeadmasterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HeadmasterEntityMapper {

    @Mapping(target = "customers", ignore = true)
    Headmaster mapFromEntityToDomain(HeadmasterEntity headmasterEntity);
    @Mapping(target = "customers", ignore = true)
    HeadmasterEntity mapFromDomainToEntity(Headmaster headmaster);
}
