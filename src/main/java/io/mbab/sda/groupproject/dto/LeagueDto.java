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

@Value
@Builder
public class LeagueDto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 128)
  @NotBlank
  private String name;

  @NotNull private Country country;

  private List<Team> teams;

  public League toEntity() {
    return League.builder()
        .country(this.country)
        .name(this.name)
        .teams(this.teams)
        .id(this.id)
        .build();
  }

  public static LeagueDto toDto(League league) {
    return LeagueDto.builder()
        .country(league.getCountry())
        .id(league.getId())
        .teams(league.getTeams())
        .name(league.getName())
        .build();
  }
}
