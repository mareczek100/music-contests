package mareczek100.musiccontests.api.dto.dto_class_support;

import lombok.Builder;
import lombok.Data;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;

@Data
@Builder
public class MusicContestsPortalUserDto {

    private String name;
    private String surname;
    private String email;
    private String pesel;
    private RoleEntity.RoleName role;

}