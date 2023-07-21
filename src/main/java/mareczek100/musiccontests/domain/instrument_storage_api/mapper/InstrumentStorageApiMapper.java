package mareczek100.musiccontests.domain.instrument_storage_api.mapper;

import mareczek100.musiccontests.domain.instrument_storage_api.Instrument;
import org.springframework.stereotype.Component;

@Component
public class InstrumentStorageApiMapper {


    public Instrument mapFromInstrumentApiToInstrument(){
        return Instrument.builder()
                .build();
    }




}
