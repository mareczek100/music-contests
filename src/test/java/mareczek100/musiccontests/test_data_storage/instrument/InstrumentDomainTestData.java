package mareczek100.musiccontests.test_data_storage.instrument;



import mareczek100.musiccontests.domain.instrument_storage_domain.Instrument;
import mareczek100.musiccontests.domain.instrument_storage_domain.InstrumentCategory;

import java.util.List;

public class InstrumentDomainTestData {

    public static Instrument instrument1(){
        return Instrument.builder()
                .instrumentId("a88e00d8-ef55-4fb0-80cf-d7b454230b0b")
                .name("testInstrument1")
                .category(buildCategoryStrunowe())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static Instrument instrument2(){
        return Instrument.builder()
                .instrumentId("6cd043a4-0495-4b0e-80da-e0b8c7fdacd3")
                .name("testInstrument2")
                .category(buildCategoryStrunowe())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static Instrument instrument3(){
        return Instrument.builder()
                .instrumentId("f8ec2ded-c4ef-428e-83f8-c5f83bcdacd8")
                .name("testInstrument3")
                .category(buildCategoryDęte())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static Instrument instrument4(){
        return Instrument.builder()
                .instrumentId("53c60873-330f-4cb2-8979-0779519db27d")
                .name("testInstrument4")
                .category(buildCategoryPerkusyjne())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }
    public static Instrument instrument5() {
        return Instrument.builder()
                .instrumentId("9c4f2de1-a848-4bf6-bf11-a40715507bb0")
                .name("testInstrument5")
                .category(buildCategoryPerkusyjne())
                .primarySchoolDegree(true)
                .secondarySchoolDegree(true)
                .build();
    }

    public static List<Instrument> instrumentList(){
        return List.of(
                instrument1(),
                instrument2(),
                instrument3(),
                instrument4(),
                instrument5()
        );
    }

    private static InstrumentCategory buildCategoryStrunowe() {
        return InstrumentCategory.builder()
                .instrumentCategory("strunowe")
                .build();
    }
    private static InstrumentCategory buildCategoryDęte() {
        return InstrumentCategory.builder()
                .instrumentCategory("dęte")
                .build();
    }
    private static InstrumentCategory buildCategoryPerkusyjne() {
        return InstrumentCategory.builder()
                .instrumentCategory("perkusyjne")
                .build();
    }


}