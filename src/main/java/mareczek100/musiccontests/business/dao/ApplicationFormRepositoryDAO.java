package mareczek100.musiccontests.business.dao;

import mareczek100.musiccontests.domain.ApplicationForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationFormRepositoryDAO {


    ApplicationForm insertApplicationForm(ApplicationForm applicationForm);

    List<ApplicationForm> findAllApplicationForms();

}
