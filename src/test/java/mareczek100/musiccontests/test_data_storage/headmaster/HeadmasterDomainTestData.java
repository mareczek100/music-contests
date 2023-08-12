package mareczek100.musiccontests.test_data_storage.headmaster;


import lombok.Getter;
import mareczek100.musiccontests.domain.Headmaster;
import mareczek100.musiccontests.domain.Teacher;
import mareczek100.musiccontests.test_data_storage.music_school.MusicSchoolDomainTestData;
@Getter
public class HeadmasterDomainTestData {
    public static Headmaster headmasterToSave1() {
        return Headmaster.builder()
                .headmasterId(null)
                .name("name")
                .surname("surname")
                .email("headmaster@music-school.com")
                .pesel("11111111111")
                .musicSchool(MusicSchoolDomainTestData.musicSchoolSaved1())
                .build();
    }
    public static Headmaster headmasterSaved1() {
        return Headmaster.builder()
                .headmasterId("fac2f8da-d0d0-47ec-a890-6bbe3f4787f9")
                .name("name")
                .surname("surname")
                .email("headmaster@music-school.com")
                .pesel("11111111111")
                .musicSchool(MusicSchoolDomainTestData.musicSchoolSaved1())
                .build();
    }

    public static Teacher headmasterTeacherToSave1() {
        return Teacher.builder()
                .teacherId(null)
                .name("name")
                .surname("surname")
                .email("headmaster@music-school.com")
                .pesel("11111111111")
                .musicSchool(MusicSchoolDomainTestData.musicSchoolSaved1())
                .instrument("fagot")
                .build();
    }
    public static Teacher headmasterTeacherSaved1() {
        return Teacher.builder()
                .teacherId("c1a9eb88-8fcc-467c-b8bc-fe17f0975c27")
                .name("name")
                .surname("surname")
                .email("headmaster@music-school.com")
                .pesel("11111111111")
                .musicSchool(MusicSchoolDomainTestData.musicSchoolSaved1())
                .instrument("fagot")
                .build();
    }

}
