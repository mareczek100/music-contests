package mareczek100.musiccontests.infrastructure.database.entity.security;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.infrastructure.database.entity.security.repository.MusicContestsPortalUserJpaRepository;
import mareczek100.musiccontests.infrastructure.database.entity.security.repository.RoleJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SecurityService {

    private final MusicContestsPortalUserJpaRepository portalUserJpaRepository;
    private final RoleJpaRepository roleJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MusicContestsPortalUserEntity insertRoleWhileCreateNewUser(String email, String pesel, RoleEntity.RoleName role)
    {
        RoleEntity roleEntity = roleJpaRepository.findRoleByRoleName(role)
                .orElseThrow(() -> new RuntimeException("Role [%s] doesn't exist!"
                        .formatted(role)));

        List<String> userNameList = portalUserJpaRepository.findAll().stream()
                .map(MusicContestsPortalUserEntity::getUserName)
                .toList();

        if (userNameList.contains(email)){
            throw new RuntimeException("Account for user [%s]: already exist!".formatted(email));
        }

        MusicContestsPortalUserEntity portalUserEntity = MusicContestsPortalUserEntity.builder()
                .userName(email)
                .password(passwordEncoder.encode(pesel))
                .active(true)
                .role(roleEntity)
                .build();

        return portalUserJpaRepository.saveAndFlush(portalUserEntity);
    }
    @Transactional
    public MusicContestsPortalUserEntity changeRoleToExistingUser(
            String securityPortalUserEmail, RoleEntity.RoleName newRole)
    {
        MusicContestsPortalUserEntity securityPortalUser = findByUserName(securityPortalUserEmail);
        RoleEntity newRoleToAdd = roleJpaRepository.findRoleByRoleName(newRole)
                .orElseThrow(() -> new RuntimeException("Role [%s] doesn't exist!"
                        .formatted(newRole)));

        securityPortalUser.setRole(newRoleToAdd);

        return portalUserJpaRepository.saveAndFlush(securityPortalUser);
    }
    @Transactional
    public MusicContestsPortalUserEntity findByUserName(String userName)
    {
        return portalUserJpaRepository.findByUserName(userName).orElseThrow(
                () -> new RuntimeException("Music Contests Portal user [%s] doesn't exist!"
                        .formatted(userName))
        );
    }
}
