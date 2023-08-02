package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.HeadmasterDto;
import mareczek100.musiccontests.api.dto.MusicSchoolWithAddressDto;
import mareczek100.musiccontests.domain.Address;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.domain.MusicSchool;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MusicSchoolDtoMapper {

    
    default MusicSchool mapFromDtoToDomain(MusicSchoolWithAddressDto musicSchoolDto){
        return MusicSchool.builder()
                .musicSchoolId(musicSchoolDto.musicSchoolId())
                .name(musicSchoolDto.musicSchoolName())
                .patron(musicSchoolDto.musicSchoolPatron())
                .primaryDegree(musicSchoolDto.musicSchoolPrimaryDegree())
                .secondaryDegree(musicSchoolDto.musicSchoolSecondaryDegree())
                .address(getAddress(musicSchoolDto))
                .build();
    }

    private Address getAddress(MusicSchoolWithAddressDto musicSchoolDto){
        return Address.builder()
                .country(musicSchoolDto.addressCountry())
                .city(musicSchoolDto.addressCity())
                .postalCode(musicSchoolDto.addressPostalCode())
                .street(musicSchoolDto.addressStreet())
                .buildingNumber(musicSchoolDto.addressBuildingNumber())
                .additionalInfo(musicSchoolDto.addressAdditionalInfo())
                .build();
    }
    default MusicSchoolWithAddressDto mapFromDomainToDto(MusicSchool musicSchool){
        return MusicSchoolWithAddressDto.builder()
                .musicSchoolId(musicSchool.musicSchoolId())
                .musicSchoolName(musicSchool.name())
                .musicSchoolPatron(musicSchool.patron())
                .musicSchoolPrimaryDegree(musicSchool.primaryDegree())
                .musicSchoolSecondaryDegree(musicSchool.secondaryDegree())
                .addressCountry(musicSchool.address().country())
                .addressCity(musicSchool.address().city())
                .addressPostalCode(musicSchool.address().postalCode())
                .addressStreet(musicSchool.address().street())
                .addressBuildingNumber(musicSchool.address().buildingNumber())
                .addressAdditionalInfo(musicSchool.address().additionalInfo())
//                .headmaster(getHeadmaster(musicSchool.headmaster()))
                .build();
    }

    private HeadmasterDto getHeadmaster(Headmaster headmaster){
        return HeadmasterDto.builder()
                .headmasterId(headmaster.headmasterId())
                .name(headmaster.name())
                .surname(headmaster.surname())
                .email(headmaster.email())
                .pesel(headmaster.pesel())
                .build();
    }
}
