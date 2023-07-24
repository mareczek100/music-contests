package mareczek100.musiccontests.business;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.HeadmasterRepositoryDAO;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.infrastructure.database.entity.security.RoleEntity;
import mareczek100.musiccontests.infrastructure.database.entity.security.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class HeadmasterService {

    private final HeadmasterRepositoryDAO headmasterRepositoryDAO;
    private final SecurityService securityService;


    @Transactional
    public Headmaster insertHeadmaster(Headmaster headmaster) {
        RoleEntity.RoleName headmasterRole = RoleEntity.RoleName.HEADMASTER;
        securityService.insertRoleWhileCreateNewUser(headmaster.email(), headmaster.pesel(), headmasterRole);
        return headmasterRepositoryDAO.insertHeadmaster(headmaster);
    }
    @Transactional
    public List<Headmaster> findAllHeadmaster() {
        return headmasterRepositoryDAO.findAllHeadmasters();
    }
    @Transactional
    public Optional<Headmaster> findHeadmasterByEmail(String email) {
        return headmasterRepositoryDAO.findHeadmasterByEmail(email);
    }
}
