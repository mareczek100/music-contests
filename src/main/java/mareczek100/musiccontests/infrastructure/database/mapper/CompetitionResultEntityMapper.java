package mareczek100.musiccontests.infrastructure.database.mapper;

import mareczek100.musiccontests.domain.CompetitionResult;
import mareczek100.musiccontests.infrastructure.database.entity.CompetitionResultEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CompetitionEntityMapper.class)
public interface CompetitionResultEntityMapper {
    @Mapping(source = "competitionResultEntity.competition", target = "competition",
            qualifiedByName = "competitionMapFromEntityToDomain")
    @Mapping(target = "student.competitionResults", ignore = true)
    @Mapping(target = "student.applicationForms", ignore = true)
    @Mapping(target = "student.musicSchool", ignore = true)
    @Mapping(target = "student.teacher.musicSchool", ignore = true)
    @Mapping(target = "student.teacher.students", ignore = true)
    @Mapping(target = "student.teacher.applicationForms", ignore = true)
    CompetitionResult mapFromEntityToDomain(CompetitionResultEntity competitionResultEntity);
    
    CompetitionResultEntity mapFromDomainToEntity(CompetitionResult competitionResult);
}
