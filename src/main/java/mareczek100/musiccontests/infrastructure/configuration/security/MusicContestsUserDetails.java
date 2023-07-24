package mareczek100.musiccontests.infrastructure.configuration.security;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.infrastructure.database.entity.security.MusicContestsPortalUserEntity;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;
import mareczek100.musiccontests.infrastructure.database.entity.security.repository.MusicContestsPortalUserJpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MusicContestsUserDetails implements UserDetailsService {

    private final MusicContestsPortalUserJpaRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MusicContestsPortalUserEntity userEntity = userRepository.findByUserName(userName).orElseThrow(
                () -> new RuntimeException("User [%s] doesn't exist! Create an account first."
                        .formatted(userName))
        );
        List<GrantedAuthority> authorities = getUserAuthority(userEntity.getRole());
        return buildUserForAuthentication(userEntity, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(RoleEntity userRole) {
        return List.of(new SimpleGrantedAuthority(userRole.getRoleName().name()));
    }

    private UserDetails buildUserForAuthentication(MusicContestsPortalUserEntity userEntity, List<GrantedAuthority> authorities) {
        return new User(
                userEntity.getUserName(),
                userEntity.getPassword(),
                userEntity.getActive(),
                true, true, true,
                authorities
        );
    }
}
