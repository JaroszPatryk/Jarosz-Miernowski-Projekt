package io.mbab.sda.groupproject.mapper;

import io.mbab.sda.groupproject.dto.PlayerDto;
import io.mbab.sda.groupproject.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper implements CrudMapper<Player, PlayerDto> {
    @Override
    public Player dtoToEntity(PlayerDto playerDto) {
        return playerDto.toEntity();
    }

    @Override
    public PlayerDto entityToDto(Player player) {
        return PlayerDto.toDto(player);
    }
}
