package mareczek100.musiccontests.test_data_storage.instrument;



import mareczek100.musiccontests.api.dto.InstrumentCategoryDto;
import mareczek100.musiccontests.api.dto.InstrumentDto;
import mareczek100.musiccontests.api.dto.dto_rest_support.InstrumentsDto;

import java.util.List;

public class InstrumentDtoTestData {

    public static InstrumentDto instrumentDto1(){
        return InstrumentDto.builder()
                .name("testInstrument1")
                .category(buildCategoryStrunoweDto())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static InstrumentDto instrumentDto2(){
        return InstrumentDto.builder()
                .name("testInstrument2")
                .category(buildCategoryStrunoweDto())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static InstrumentDto instrumentDto3(){
        return InstrumentDto.builder()
                .name("testInstrument3")
                .category(buildCategoryDęteDto())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static InstrumentDto instrumentDto4(){
        return InstrumentDto.builder()
                .name("testInstrument4")
                .category(buildCategoryPerkusyjneDto())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static InstrumentDto instrumentDto5() {
        return InstrumentDto.builder()
                .name("testInstrument5")
                .category(buildCategoryPerkusyjneDto())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }

    public static List<InstrumentDto> instrumentDtoList(){
        return List.of(
                instrumentDto1(),
                instrumentDto2(),
                instrumentDto3(),
                instrumentDto4(),
                instrumentDto5()
        );
    }

    public static InstrumentsDto instrumentsDto(){
        return InstrumentsDto.builder()
                .instrumentDtoList(instrumentDtoList())
                .build();
    }

    private static InstrumentCategoryDto buildCategoryStrunoweDto() {
        return InstrumentCategoryDto.builder()
                .instrumentCategory("strunowe")
                .build();
    }
    private static InstrumentCategoryDto buildCategoryDęteDto() {
        return InstrumentCategoryDto.builder()
                .instrumentCategory("dęte")
                .build();
    }
    private static InstrumentCategoryDto buildCategoryPerkusyjneDto() {
        return InstrumentCategoryDto.builder()
                .instrumentCategory("perkusyjne")
                .build();
    }


}