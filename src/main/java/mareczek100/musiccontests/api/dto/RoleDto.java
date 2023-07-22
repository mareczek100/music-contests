package mareczek100.musiccontests.api.dto;

import lombok.Builder;
import mareczek100.musiccontests.infrastructure.database.entity.RoleEntity;
@Builder
public record RoleDto(String roleId,
                      RoleEntity.RoleName role){
}