package mareczek100.musiccontests.domain;

import lombok.Builder;
import mareczek100.musiccontests.infrastructure.database.entity.RoleEntity;

import java.util.Set;
@Builder
public record Role (String roleId,
                    RoleEntity.RoleName role,
                    Set<Headmaster> headmasters,
                    Set<Teacher> teachers,
                    Set<Student> students){
}
