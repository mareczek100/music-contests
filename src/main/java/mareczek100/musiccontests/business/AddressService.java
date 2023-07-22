package mareczek100.musiccontests.business;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.AddressRepositoryDAO;
import mareczek100.musiccontests.domain.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepositoryDAO addressRepositoryDAO;

    @Transactional
    public Address insertAddress(Address address) {
        return addressRepositoryDAO.insertAddress(address);
    }
    @Transactional
    public List<Address> findAllAddresses() {
        return addressRepositoryDAO.findAllAddresses();
    }
    @Transactional
    public Optional<Address> findAddressByCompetitionOrganizerEmail(String email) {
        return addressRepositoryDAO.findAddressByHeadmasterEmail(email);
    }

}
