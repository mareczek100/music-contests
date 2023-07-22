package mareczek100.musiccontests.domain.mapper;

import mareczek100.musiccontests.api.dto.HeadmasterDto;
import mareczek100.musiccontests.domain.Headmaster;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HeadmasterDtoMapper {

    @Mapping(target = "customers", ignore = true)
    Headmaster mapFromDtoToDomain(HeadmasterDto headmasterDto);
    @Mapping(target = "customers", ignore = true)
    HeadmasterDto mapFromDomainToDto(Headmaster headmaster);
}
