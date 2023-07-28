package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.Address;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.domain.MusicSchool;
import mareczek100.musiccontests.infrastructure.database.entity.AddressEntity;
import mareczek100.musiccontests.infrastructure.database.entity.HeadmasterEntity;
import mareczek100.musiccontests.infrastructure.database.entity.MusicSchoolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HeadmasterEntityMapper {


    default Headmaster mapFromEntityToDomain(HeadmasterEntity headmasterEntity){
        return Headmaster.builder()
                .headmasterId(headmasterEntity.getHeadmasterId())
                .name(headmasterEntity.getName())
                .surname(headmasterEntity.getSurname())
                .email(headmasterEntity.getEmail())
                .pesel(headmasterEntity.getPesel())
                .musicSchool(getSchool(headmasterEntity.getMusicSchool()))
                .build();
    }

    private MusicSchool getSchool(MusicSchoolEntity musicSchool){
        return MusicSchool.builder()
                .musicSchoolId(musicSchool.getMusicSchoolId())
                .name(musicSchool.getName())
                .patron(musicSchool.getPatron())
                .primaryDegree(musicSchool.getPrimaryDegree())
                .secondaryDegree(musicSchool.getSecondaryDegree())
                .address(getAddress(musicSchool.getAddress()))
                .build();
    }

    private Address getAddress(AddressEntity address){
        return Address.builder()
                .addressId(address.getAddressId())
                .country(address.getCountry())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .street(address.getStreet())
                .buildingNumber(address.getBuildingNumber())
                .additionalInfo(address.getAdditionalInfo())
                .build();
    }

    @Mapping(target = "competitions", ignore = true)
    @Mapping(target = "musicSchool.address", ignore = true)
    HeadmasterEntity mapFromDomainToEntity(Headmaster headmaster);
}
