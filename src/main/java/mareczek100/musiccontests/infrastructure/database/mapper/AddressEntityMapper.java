package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.Address;
import mareczek100.musiccontests.infrastructure.database.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressEntityMapper {

    @Mapping(target = "musicSchool", ignore = true)
    @Mapping(target = "competitionLocation", ignore = true)
    Address mapFromEntityToDomain(AddressEntity addressEntity);
    @Mapping(target = "musicSchool", ignore = true)
    @Mapping(target = "competitionLocation", ignore = true)
    AddressEntity mapFromDomainToEntity(Address address);
}
