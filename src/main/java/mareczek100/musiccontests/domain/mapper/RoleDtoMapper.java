package mareczek100.musiccontests.domain.mapper;

import mareczek100.musiccontests.api.dto.RoleDto;
import mareczek100.musiccontests.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleDtoMapper {

    @Mapping(target = "customers", ignore = true)
    Role mapFromDtoToDomain(RoleDto roleDto);
    @Mapping(target = "customers", ignore = true)
    RoleDto mapFromDomainToDto(Role role);
}
