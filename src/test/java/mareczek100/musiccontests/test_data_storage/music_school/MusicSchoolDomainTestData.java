package mareczek100.musiccontests.test_data_storage.music_school;


import mareczek100.musiccontests.domain.Address;
import mareczek100.musiccontests.domain.MusicSchool;

public class MusicSchoolDomainTestData {
    public static MusicSchool musicSchoolToSave1() {
        return MusicSchool.builder()
                .musicSchoolId(null)
                .name("musicSchoolName1")
                .patron("musicSchoolPatron1")
                .primaryDegree(true)
                .secondaryDegree(false)
                .address(addressToSave())
                .build();
    }
    public static MusicSchool musicSchoolSaved1() {
        return MusicSchool.builder()
                .musicSchoolId("1ae52aaa-847c-4cb7-b7dd-869e94c04935")
                .name("musicSchoolName1")
                .patron("musicSchoolPatron1")
                .primaryDegree(true)
                .secondaryDegree(false)
                .address(addressSaved())
                .build();
    }

    private static Address addressToSave(){
        return Address.builder()
                .addressId(null)
                .country("addressCountry")
                .city("addressCity")
                .postalCode("78-100")
                .street("addressStreet")
                .buildingNumber("20")
                .additionalInfo("addressAdditionalInfo")
                .build();
    }
    private static Address addressSaved(){
        return Address.builder()
                .addressId("0e524b25-96ca-4560-af99-86bbeedf5dfe")
                .country("addressCountry")
                .city("addressCity")
                .postalCode("78-100")
                .street("addressStreet")
                .buildingNumber("20")
                .additionalInfo("addressAdditionalInfo")
                .build();
    }

}
