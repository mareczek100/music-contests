package mareczek100.musiccontests.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import mareczek100.musiccontests.business.dao.AddressRepositoryDAO;
import mareczek100.musiccontests.domain.Address;
import mareczek100.musiccontests.infrastructure.database.entity.AddressEntity;
import mareczek100.musiccontests.infrastructure.database.mapper.AddressEntityMapper;
import mareczek100.musiccontests.infrastructure.database.repository.jpaRepository.AddressJpaRepository;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class AddressRepositoryImpl implements AddressRepositoryDAO {

    private final AddressEntityMapper addressEntityMapper;
    private final AddressJpaRepository addressJpaRepository;


    @Override
    public Address insertAddress(Address address) {
        AddressEntity addressEntity = addressEntityMapper.mapFromDomainToEntity(address);
        AddressEntity savedAddressEntity = addressJpaRepository.saveAndFlush(addressEntity);
        return addressEntityMapper.mapFromEntityToDomain(savedAddressEntity);
    }

    @Override
    public List<Address> findAllAddresses() {
        List<AddressEntity> addressEntityList = addressJpaRepository.findAll();
        return addressEntityList.stream()
                .map(addressEntityMapper::mapFromEntityToDomain)
                .toList();
    }

    @Override
    public Optional<Address> findAddressByHeadmasterEmail(String email) {
        return addressJpaRepository.findAddressByHeadmasterEmail(email)
               .map(addressEntityMapper::mapFromEntityToDomain);
    }
}