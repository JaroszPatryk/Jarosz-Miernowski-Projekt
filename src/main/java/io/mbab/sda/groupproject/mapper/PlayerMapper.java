package io.mbab.sda.groupproject.mapper;

import io.mbab.sda.groupproject.dto.PlayerDto;
import io.mbab.sda.groupproject.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper implements CrudMapper<Player, PlayerDto> {
  @Override
  public Player dtoToEntity(PlayerDto playerDto) {
    if (playerDto == null) return null;
    return playerDto.toEntity();
  }

  @Override
  public PlayerDto entityToDto(Player player) {
    if (player == null) return null;

    return PlayerDto.toDto(player);
  }
}
