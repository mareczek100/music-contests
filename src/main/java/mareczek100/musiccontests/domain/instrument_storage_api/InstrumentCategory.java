package mareczek100.musiccontests.domain.instrument_storage_api;

import lombok.Builder;


@Builder
public record InstrumentCategory(String instrumentCategoryId,
                                 Short instrumentCategoryApiId,
                                 InstrumentCategory instrumentCategory) {
}