package mareczek100.musiccontests.domain.mapper;

import mareczek100.musiccontests.api.dto.AddressDto;
import mareczek100.musiccontests.domain.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressDtoMapper {

    @Mapping(target = "customers", ignore = true)
    Address mapFromDtoToDomain(AddressDto addressDto);
    @Mapping(target = "customers", ignore = true)
    AddressDto mapFromDomainToDto(Address address);
}
