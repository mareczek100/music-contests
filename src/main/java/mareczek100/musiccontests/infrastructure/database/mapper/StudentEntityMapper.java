package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.Student;
import mareczek100.musiccontests.infrastructure.database.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentEntityMapper {

    @Mapping(target = "customers", ignore = true)
    Student mapFromEntityToDomain(StudentEntity studentEntity);
    @Mapping(target = "customers", ignore = true)
    StudentEntity mapFromDomainToEntity(Student student);
}