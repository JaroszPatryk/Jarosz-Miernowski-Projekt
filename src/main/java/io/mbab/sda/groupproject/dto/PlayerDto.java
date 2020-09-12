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
public class PlayerDto implements CrudDto<Integer> {

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

  @NotBlank
  private CountryDto country;

  private TeamDto team;

  public Player toEntity() {
    Player.PlayerBuilder builder =
            Player.builder()
                    .id(this.id)
                    .dateOfBirth(this.dateOfBirth)
                    .firstName(this.firstName)
                    .lastName(this.lastName);


    if (country != null) {
      builder.country(this.country.toEntity());
    }

    if (team != null) {
      builder.team(this.team.toEntity());
    }

    return builder.build();
  }

  public static PlayerDto toDto(Player player) {
    if (player == null) return null;

    return PlayerDto.builder()
            .team(TeamDto.toDto(player.getTeam()))
            .id(player.getId())
            .country(CountryDto.toDto(player.getCountry()))
            .dateOfBirth(player.getDateOfBirth())
            .firstName(player.getFirstName())
            .lastName(player.getLastName())
            .build();
  }

  public static List<Player> toEntityList(List<PlayerDto> dtos) {
    return Objects.isNull(dtos)
            ? List.of()
            : dtos.stream().map(PlayerDto::toEntity).collect(Collectors.toUnmodifiableList());
  }

  public List<PlayerDto> toDtoList(List<Player> dtos) {
    return Objects.isNull(dtos)
            ? List.of()
            : dtos.stream().map(PlayerDto::toDto).collect(Collectors.toUnmodifiableList());
  }
}
