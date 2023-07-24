package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.MusicSchool;
import mareczek100.musiccontests.infrastructure.database.entity.MusicSchoolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MusicSchoolEntityMapper {

    
    MusicSchool mapFromEntityToDomain(MusicSchoolEntity musicSchoolEntity);
    
    MusicSchoolEntity mapFromDomainToEntity(MusicSchool musicSchool);
}
