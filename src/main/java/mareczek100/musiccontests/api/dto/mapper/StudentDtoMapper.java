package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.StudentDto;
import mareczek100.musiccontests.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentDtoMapper {

    
    Student mapFromDtoToDomain(StudentDto studentDto);
    
    StudentDto mapFromDomainToDto(Student student);
}