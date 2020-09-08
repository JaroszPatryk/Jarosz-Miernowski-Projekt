package io.mbab.sda.groupproject.dto;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.entity.Team;
import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Builder
public class PlayerDto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 32)
  private String firstName;

  @Size(max = 64)
  @NotBlank
  private String lastName;

  @Size(max = 15)
  @NotBlank
  private String dateOfBirth;

  @NotBlank private Country country;

  private Team team;

  public Player toEntity() {
    return Player.builder()
        .team(this.team)
        .id(this.id)
        .country(this.country)
        .dateOfBirth(this.dateOfBirth)
        .firstName(this.firstName)
        .lastName(this.lastName)
        .build();
  }

  public static PlayerDto toDto(Player player) {
    return PlayerDto.builder()
        .team(player.getTeam())
        .id(player.getId())
        .country(player.getCountry())
        .dateOfBirth(player.getDateOfBirth())
        .firstName(player.getFirstName())
        .lastName(player.getLastName())
        .build();
  }
}
