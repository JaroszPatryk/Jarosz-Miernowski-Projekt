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

@Value
@Builder
public class TeamDto {

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
  private Country country;

  private String value;

  private League league;

  private List<Player> players;

  public Team toEntity() {
    return Team.builder()
        .country(this.country)
        .league(this.league)
        .value(this.value)
        .city(this.city)
        .name(this.name)
        .id(this.id)
        .players(this.players)
        .build();
  }

  public static TeamDto toDto(Team team) {
    return TeamDto.builder()
        .country(team.getCountry())
        .league(team.getLeague())
        .value(team.getValue())
        .city(team.getCity())
        .name(team.getName())
        .id(team.getId())
        .players(team.getPlayers())
        .build();
  }
}
