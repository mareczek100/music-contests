package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.Student;
import mareczek100.musiccontests.infrastructure.database.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentEntityMapper {

    
    Student mapFromEntityToDomain(StudentEntity studentEntity);
    
    StudentEntity mapFromDomainToEntity(Student student);
}