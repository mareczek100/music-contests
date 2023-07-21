package mareczek100.musiccontests.domain.instrument_storage_api;

import lombok.Builder;


@Builder
public record Instrument(String instrumentId,
                         Integer instrumentApiId,
                         String name,
                         InstrumentCategory category) {
}
