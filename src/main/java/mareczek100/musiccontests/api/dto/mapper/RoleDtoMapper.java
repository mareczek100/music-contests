package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.RoleDto;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleDtoMapper {
    
    RoleEntity mapFromDtoToEntity(RoleDto roleDto);
    
    RoleDto mapFromEntityToDto(RoleEntity role);
}
