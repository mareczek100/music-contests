package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.AddressDto;
import mareczek100.musiccontests.domain.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressDtoMapper {

    Address mapFromDtoToDomain(AddressDto addressDto);
    AddressDto mapFromDomainToDto(Address address);
}
