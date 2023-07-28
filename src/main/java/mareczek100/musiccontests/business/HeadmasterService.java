package mareczek100.musiccontests.business;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.HeadmasterRepositoryDAO;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.domain.MusicSchool;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;
import mareczek100.musiccontests.infrastructure.database.entity.security.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
public class HeadmasterService {

    private final HeadmasterRepositoryDAO headmasterRepositoryDAO;
    private final MusicSchoolService musicSchoolService;
    private final SecurityService securityService;

    @Transactional
    public Headmaster insertHeadmaster(Headmaster headmaster) {
        RoleEntity.RoleName headmasterRole = RoleEntity.RoleName.HEADMASTER;
        securityService.insertRoleWhileCreateNewUser(headmaster.email(), headmaster.pesel(), headmasterRole);
        MusicSchool musicSchool = headmaster.musicSchool();
        MusicSchool insertedMusicSchool = musicSchoolService.insertMusicSchool(musicSchool);
        return headmasterRepositoryDAO.insertHeadmaster(headmaster.withMusicSchool(insertedMusicSchool));
    }
    @Transactional
    public List<Headmaster> findAllHeadmaster() {
        return headmasterRepositoryDAO.findAllHeadmasters();
    }
    @Transactional
    public Headmaster findHeadmasterByEmail(String email) {
        return headmasterRepositoryDAO.findHeadmasterByEmail(email).orElseThrow(
                () -> new RuntimeException("Headmaster with email [%s] doesn't exist!".formatted(email))
        );
    }
}
