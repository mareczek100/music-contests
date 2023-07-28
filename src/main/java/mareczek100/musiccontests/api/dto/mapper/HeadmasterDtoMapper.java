package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.HeadmasterDto;
import mareczek100.musiccontests.domain.Headmaster;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HeadmasterDtoMapper {
    
    Headmaster mapFromDtoToDomain(HeadmasterDto headmasterDto);
    
    HeadmasterDto mapFromDomainToDto(Headmaster headmaster);
}
