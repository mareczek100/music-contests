package mareczek100.musiccontests.domain.mapper;

import mareczek100.musiccontests.api.dto.TeacherDto;
import mareczek100.musiccontests.domain.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherDtoMapper {

    @Mapping(target = "customers", ignore = true)
    Teacher mapFromDtoToDomain(TeacherDto teacherDto);
    @Mapping(target = "customers", ignore = true)
    TeacherDto mapFromDomainToDto(Teacher teacher);
}
