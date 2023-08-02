package mareczek100.musiccontests.api.dto.dto_class_support;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mareczek100.musiccontests.api.dto.CompetitionResultDto;

import java.util.List;

@Data
@Builder
public class CompetitionResultListDto {

    private List<CompetitionResultSupport> competitionResultsSupport;
    private List<CompetitionResultDto> competitionResultsDto;
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompetitionResultSupport {
        private String studentId;
        private String competitionPlace;
        private String specialAward;
    }
}