package io.mbab.sda.groupproject.dto;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Value
@Builder(toBuilder = true)
public class LeagueDto implements CrudDto<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 128)
    @NotBlank
    private String name;

    @NotNull
    private CountryDto country;

    private List<TeamDto> teams;

  public League toEntity() {
    return League.builder()
            .country(this.country.toEntity())
            .name(this.name)
            .teams(this.toEntityList(this.teams))
        .id(this.id)
        .build();
  }

    public static LeagueDto toDto(League league) {
        if (league == null) return null;

        return LeagueDto.builder()
                .country(CountryDto.toDto(league.getCountry()))
                .id(league.getId())
                .teams(LeagueDto.toDtoList(league.getTeams()))
                .name(league.getName())
                .build();
    }


    public List<Team> toEntityList(List<TeamDto> dtos) {
        return Objects.isNull(dtos)
                ? List.of()
                : dtos.stream()
                .map(TeamDto::toEntity)
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<TeamDto> toDtoList(List<Team> dtos) {
        return Objects.isNull(dtos)
                ? List.of()
                : dtos.stream()
                .map(TeamDto::toDto)
                .collect(Collectors.toUnmodifiableList());
    }
}
