package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.Teacher;
import mareczek100.musiccontests.infrastructure.database.entity.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherEntityMapper {

    
    Teacher mapFromEntityToDomain(TeacherEntity teacherEntity);
    
    TeacherEntity mapFromDomainToEntity(Teacher teacher);
}
