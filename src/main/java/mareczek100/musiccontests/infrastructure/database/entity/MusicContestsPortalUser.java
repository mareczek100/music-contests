package mareczek100.musiccontests.infrastructure.database.entity;

import lombok.Data;

@Data
public abstract sealed class MusicContestsPortalUser permits HeadmasterEntity, StudentEntity, TeacherEntity {

    private String email;
    private String pesel;
//    private Set<RoleEntity> roles;

}