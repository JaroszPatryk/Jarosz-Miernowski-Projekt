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
    return Team.builder()
            .country(this.country.toEntity())
            .league(this.league.toEntity())
            .value(this.value)
            .city(this.city)
            .name(this.name)
            .id(this.id)
            .players(this.players.stream().map(PlayerDto::toEntity).collect(Collectors.toUnmodifiableList()))
        .build();
  }

  public static TeamDto toDto(Team team) {
    return TeamDto.builder()
            .country(CountryDto.toDto(team.getCountry()))
            .league(LeagueDto.toDto(team.getLeague()))
            .value(team.getValue())
            .city(team.getCity())
            .name(team.getName())
            .id(team.getId())
            .players(team.getPlayers().stream().map(PlayerDto::toDto).collect(Collectors.toUnmodifiableList()))
        .build();
  }
}
