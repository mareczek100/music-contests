package mareczek100.musiccontests.business;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.HeadmasterRepositoryDAO;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.domain.MusicSchool;
import mareczek100.musiccontests.infrastructure.security.MusicContestsPortalUserEntity;
import mareczek100.musiccontests.infrastructure.security.RoleEntity;
import mareczek100.musiccontests.infrastructure.security.SecurityService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class HeadmasterService {

    private final HeadmasterRepositoryDAO headmasterRepositoryDAO;
    private final MusicSchoolService musicSchoolService;
    private final SecurityService securityService;

    @Transactional
    public Headmaster insertHeadmaster(Headmaster headmaster)
    {
        checkIfHeadmasterExistByPesel(headmaster.pesel());

        MusicSchool musicSchool = headmaster.musicSchool();
        String musicSchoolId = musicSchool.musicSchoolId();

        boolean schoolHeadmasterAnyMatch = findAllHeadmaster().stream()
                .map(Headmaster::musicSchool)
                .map(MusicSchool::musicSchoolId)
                .anyMatch(musicSchoolId::equals);
        if (schoolHeadmasterAnyMatch) {
            throw new RuntimeException("[%s] has already headmaster!"
                    .formatted(musicSchool.name()));
        }

        RoleEntity.RoleName headmasterRole = RoleEntity.RoleName.HEADMASTER;
        MusicContestsPortalUserEntity headmasterPortalUserEntity
                = securityService.insertRoleWhileCreateNewUser(headmaster.email(), headmaster.pesel(), headmasterRole);
        String encodedPesel = headmasterPortalUserEntity.getPassword();

        if (!musicSchool.musicSchoolId().isEmpty()) {
            return headmasterRepositoryDAO.insertHeadmaster(headmaster
                    .withMusicSchool(musicSchool)
                    .withPesel(encodedPesel));
        }

        MusicSchool insertedMusicSchool = musicSchoolService.insertMusicSchool(musicSchool);
        return headmasterRepositoryDAO.insertHeadmaster(headmaster
                .withMusicSchool(insertedMusicSchool)
                .withPesel(encodedPesel));
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

    @Transactional
    public void checkIfHeadmasterExistByPesel(String pesel)
    {
        Optional<Headmaster> headmasterByPesel = findAllHeadmaster().stream()
                .filter(headmaster -> BCrypt.checkpw(pesel, headmaster.pesel()))
                .findAny();

        if (headmasterByPesel.isPresent()) {
            throw new RuntimeException("Headmaster with pesel [%s] already exist!".formatted(pesel));
        }
    }

    @Transactional
    public void deleteHeadmaster(Headmaster headmaster) {
        headmasterRepositoryDAO.deleteHeadmaster(headmaster);
        securityService.deleteUserByUserName(headmaster.email());
    }
}
