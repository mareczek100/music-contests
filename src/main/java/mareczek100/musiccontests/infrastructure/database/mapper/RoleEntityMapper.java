package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.Role;
import mareczek100.musiccontests.infrastructure.database.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {

    @Mapping(target = "customers", ignore = true)
    Role mapFromEntityToDomain(RoleEntity roleEntity);
    @Mapping(target = "customers", ignore = true)
    RoleEntity mapFromDomainToEntity(Role role);
}
