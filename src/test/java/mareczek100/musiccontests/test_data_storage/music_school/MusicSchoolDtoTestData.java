package mareczek100.musiccontests.test_data_storage.music_school;

import mareczek100.musiccontests.api.dto.MusicSchoolWithAddressDto;

public class MusicSchoolDtoTestData {
    public static MusicSchoolWithAddressDto musicSchoolDtoToSave1() {
        return MusicSchoolWithAddressDto.builder()
                .musicSchoolId(null)
                .musicSchoolName("musicSchoolName")
                .musicSchoolPatron("musicSchoolPatron")
                .musicSchoolPrimaryDegree(true)
                .musicSchoolSecondaryDegree(false)
                .addressCountry("addressCountry")
                .addressCity("addressCity")
                .addressPostalCode("78-100")
                .addressStreet("addressStreet")
                .addressBuildingNumber("20")
                .addressAdditionalInfo("addressAdditionalInfo")
                .build();
    }
    public static MusicSchoolWithAddressDto musicSchoolDtoSaved1() {
        return MusicSchoolWithAddressDto.builder()
                .musicSchoolId("1ae52aaa-847c-4cb7-b7dd-869e94c04935")
                .musicSchoolName("musicSchoolName")
                .musicSchoolPatron("musicSchoolPatron")
                .musicSchoolPrimaryDegree(true)
                .musicSchoolSecondaryDegree(false)
                .addressCountry("addressCountry")
                .addressCity("addressCity")
                .addressPostalCode("78-100")
                .addressStreet("addressStreet")
                .addressBuildingNumber("20")
                .addressAdditionalInfo("addressAdditionalInfo")
                .build();
    }

}
