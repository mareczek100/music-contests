package mareczek100.musiccontests.business.dao;

import mareczek100.musiccontests.domain.Address;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepositoryDAO{
    Address insertAddress(Address address);
    List<Address> findAllAddresses();
    Optional<Address> findAddressByHeadmasterEmail(String email);
}
