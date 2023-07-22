package mareczek100.musiccontests.domain.mapper;

import mareczek100.musiccontests.api.dto.StudentDto;
import mareczek100.musiccontests.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentDtoMapper {

    @Mapping(target = "customers", ignore = true)
    Student mapFromDtoToDomain(StudentDto studentDto);
    @Mapping(target = "customers", ignore = true)
    StudentDto mapFromDomainToDto(Student student);
}