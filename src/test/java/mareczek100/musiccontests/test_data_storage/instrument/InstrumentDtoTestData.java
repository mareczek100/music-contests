package mareczek100.musiccontests.test_data_storage.instrument;



import mareczek100.musiccontests.api.dto.InstrumentCategoryDto;
import mareczek100.musiccontests.api.dto.InstrumentDto;
import mareczek100.musiccontests.api.dto.dto_rest_support.InstrumentsDto;

import java.util.List;

public class InstrumentDtoTestData {

    public static InstrumentDto instrumentDto1(){
        return InstrumentDto.builder()
                .instrumentId("a88e00d8-ef55-4fb0-80cf-d7b454230b0b")
                .name("testInstrument1")
                .category(buildCategoryStrunoweDto())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static InstrumentDto instrumentDto2(){
        return InstrumentDto.builder()
                .instrumentId("6cd043a4-0495-4b0e-80da-e0b8c7fdacd3")
                .name("testInstrument2")
                .category(buildCategoryStrunoweDto())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static InstrumentDto instrumentDto3(){
        return InstrumentDto.builder()
                .instrumentId("f8ec2ded-c4ef-428e-83f8-c5f83bcdacd8")
                .name("testInstrument3")
                .category(buildCategoryDęteDto())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static InstrumentDto instrumentDto4(){
        return InstrumentDto.builder()
                .instrumentId("53c60873-330f-4cb2-8979-0779519db27d")
                .name("testInstrument4")
                .category(buildCategoryPerkusyjneDto())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static InstrumentDto instrumentDto5() {
        return InstrumentDto.builder()
                .instrumentId("9c4f2de1-a848-4bf6-bf11-a40715507bb0")
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