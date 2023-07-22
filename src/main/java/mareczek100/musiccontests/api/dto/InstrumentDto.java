package mareczek100.musiccontests.api.dto;

import lombok.Builder;


@Builder
public record InstrumentDto(String instrumentId,
                            String name,
                            Boolean primarySchoolDegree,
                            Boolean secondarySchoolDegree,
                            InstrumentCategoryDto category) {
}
