package mareczek100.musiccontests.api.dto;

import lombok.Builder;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;

@Builder
public record RoleDto(String roleId,
                      RoleEntity.RoleName role){
}
