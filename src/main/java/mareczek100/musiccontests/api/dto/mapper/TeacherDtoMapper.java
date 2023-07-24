package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.TeacherDto;
import mareczek100.musiccontests.domain.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherDtoMapper {

    
    Teacher mapFromDtoToDomain(TeacherDto teacherDto);
    
    TeacherDto mapFromDomainToDto(Teacher teacher);
}
