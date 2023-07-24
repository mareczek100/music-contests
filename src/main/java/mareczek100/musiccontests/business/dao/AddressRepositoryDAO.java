package mareczek100.musiccontests.business.dao;

import mareczek100.musiccontests.domain.Address;

import java.util.List;
import java.util.Optional;
public interface AddressRepositoryDAO{
    Address insertAddress(Address address);
    List<Address> findAllAddresses();
    Optional<Address> findAddressByHeadmasterEmail(String email);
}
