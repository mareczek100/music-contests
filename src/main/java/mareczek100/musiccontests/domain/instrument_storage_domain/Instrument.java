package mareczek100.musiccontests.domain.instrument_storage_domain;

import lombok.Builder;


@Builder
public record Instrument(String instrumentId,
                         String name,
                         Boolean primarySchoolDegree,
                         Boolean secondarySchoolDegree,
                         InstrumentCategory category) {
}
