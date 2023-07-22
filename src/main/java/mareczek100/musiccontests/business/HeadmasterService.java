package mareczek100.musiccontests.business;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.HeadmasterRepositoryDAO;
import mareczek100.musiccontests.domain.Headmaster;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class HeadmasterService {

    private final HeadmasterRepositoryDAO headmasterRepositoryDAO;
    private final RoleService roleService;

    @Transactional
    public Headmaster insertHeadmaster(Headmaster headmaster) {
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
