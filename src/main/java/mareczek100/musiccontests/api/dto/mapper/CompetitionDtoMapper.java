package mareczek100.musiccontests.api.dto.mapper;

import mareczek100.musiccontests.api.dto.CompetitionWithLocationDto;
import mareczek100.musiccontests.api.dto.HeadmasterDto;
import mareczek100.musiccontests.domain.Address;
import mareczek100.musiccontests.domain.Competition;
import mareczek100.musiccontests.domain.CompetitionLocation;
import mareczek100.musiccontests.domain.Headmaster;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Objects;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompetitionDtoMapper {

    ZoneOffset ZONE_OFFSET = ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now());

    default Competition mapFromDtoToDomain(CompetitionWithLocationDto competitionDto) {
        return Competition.builder()
                .name(competitionDto.competitionName())
                .instrument(competitionDto.competitionInstrument())
                .online(competitionDto.competitionOnline())
                .primaryDegree(competitionDto.competitionPrimaryDegree())
                .secondaryDegree(competitionDto.competitionSecondaryDegree())
                .beginning(OffsetDateTime.of(competitionDto.competitionBeginning(), ZONE_OFFSET))
                .resultAnnouncement(OffsetDateTime.of(competitionDto.competitionResultAnnouncement(), ZONE_OFFSET))
                .requirementsDescription(competitionDto.competitionRequirementsDescription())
                .competitionLocation(getCompetitionLocation(competitionDto))
                .build();
    }
    private CompetitionLocation getCompetitionLocation(CompetitionWithLocationDto competitionLocationDto){
        if (Objects.isNull(competitionLocationDto.competitionLocationName())){
            return null;
        }
        return CompetitionLocation.builder()
                .name(competitionLocationDto.competitionLocationName())
                .address(getAddress(competitionLocationDto))
                .build();
    }
    private Address getAddress(CompetitionWithLocationDto competitionLocationDto){
        return Address.builder()
                .country(competitionLocationDto.addressCountry())
                .city(competitionLocationDto.addressCity())
                .postalCode(competitionLocationDto.addressPostalCode())
                .street(competitionLocationDto.addressStreet())
                .buildingNumber(competitionLocationDto.addressBuildingNumber())
                .additionalInfo(competitionLocationDto.addressAdditionalInfo())
                .build();
    }
    default CompetitionWithLocationDto mapFromDomainToDto(Competition competition){
        return CompetitionWithLocationDto.builder()
                .competitionName(competition.name())
                .competitionInstrument(competition.instrument())
                .competitionOnline(competition.online())
                .competitionPrimaryDegree(competition.primaryDegree())
                .competitionSecondaryDegree(competition.secondaryDegree())
                .competitionBeginning(competition.beginning().toLocalDateTime())
                .competitionResultAnnouncement(competition.resultAnnouncement().toLocalDateTime())
                .competitionRequirementsDescription(competition.requirementsDescription())
                .competitionOrganizer(getHeadmasterDto(competition.headmaster()))
                .competitionLocationName(competition.competitionLocation().name())
                .addressCountry(competition.competitionLocation().address().country())
                .addressCity(competition.competitionLocation().address().city())
                .addressPostalCode(competition.competitionLocation().address().postalCode())
                .addressStreet(competition.competitionLocation().address().street())
                .addressBuildingNumber(competition.competitionLocation().address().buildingNumber())
                .addressAdditionalInfo(competition.competitionLocation().address().additionalInfo())
                .build();
    }
    private HeadmasterDto getHeadmasterDto(Headmaster headmaster) {
        return HeadmasterDto.builder()
                .name(headmaster.name())
                .surname(headmaster.surname())
                .email(headmaster.email())
                .pesel(headmaster.pesel())
//                .musicSchool(headmasterDto.musicSchool())
                .build();
    }
}