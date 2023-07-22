package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.Teacher;
import mareczek100.musiccontests.infrastructure.database.entity.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherEntityMapper {

    @Mapping(target = "customers", ignore = true)
    Teacher mapFromEntityToDomain(TeacherEntity teacherEntity);
    @Mapping(target = "customers", ignore = true)
    TeacherEntity mapFromDomainToEntity(Teacher teacher);
}
