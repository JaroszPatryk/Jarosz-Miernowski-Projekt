package io.mbab.sda.groupproject.dto;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.entity.Team;
import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Value
@Builder(toBuilder = true)
public class TeamDto implements CrudDto<Integer> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 128)
  @NotBlank
  private String name;

  @Size(max = 128)
  @NotBlank
  private String city;

  @Size(max = 128)
  @NotBlank
  private CountryDto country;

  private String value;

  private LeagueDto league;

  private List<PlayerDto> players;

  public Team toEntity() {


    Team team = Team.builder()
            .value(this.value)
            .city(this.city)
            .name(this.name)
            .id(this.id)
            .build();

    if (country != null) {
      team = Team.builder()
              .country(this.country.toEntity())
              .build();
    }

    if (league != null) {
      team = Team.builder()
              .league(this.league.toEntity())
              .build();
    }

    if (players != null) {
      team = Team.builder()
              .players(this.toEntityList(this.players))
              .build();
    }

    return team;
  }

  public static TeamDto toDto(Team team) {
    if (team == null) return null;

    return TeamDto.builder()
            .country(CountryDto.toDto(team.getCountry()))
            .league(LeagueDto.toDto(team.getLeague()))
            .value(team.getValue())
            .city(team.getCity())
            .name(team.getName())
              .id(team.getId())
              .players(TeamDto.toDtoList(team.getPlayers()))
              .build();
  }


    public List<Player> toEntityList(List<PlayerDto> dtos) {
        return Objects.isNull(dtos)
                ? List.of()
                : dtos.stream()
                .map(PlayerDto::toEntity)
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<PlayerDto> toDtoList(List<Player> dtos) {
        return Objects.isNull(dtos)
                ? List.of()
                : dtos.stream()
                .map(PlayerDto::toDto)
                .collect(Collectors.toUnmodifiableList());
    }
}
