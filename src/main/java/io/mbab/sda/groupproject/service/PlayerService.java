package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.dto.PlayerDto;
import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.mapper.CrudMapper;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.PlayerRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerService implements CrudService<PlayerDto, Integer> {

  private final PlayerRepository playerRepository;
  protected final CrudMapper<Player, PlayerDto> crudMapper;

  public PlayerDto save(PlayerDto dto) {

    Player player = crudMapper.dtoToEntity(dto);

    if (player.getId() == null) {
      player = playerRepository.create(player);
    }

    dto = crudMapper.entityToDto(player);

    return dto;
  }

  @Override
  public List<PlayerDto> getAll() {
    return playerRepository.getAll().stream()
            .map(crudMapper::entityToDto)
            .collect(Collectors.toUnmodifiableList());
  }

  @Override
  public PlayerDto findById(Integer integer) {

    return crudMapper.entityToDto(playerRepository.findById(integer));
  }

  @Override
  public PlayerDto update(PlayerDto dto) {
    Player player = crudMapper.dtoToEntity(dto);
    playerRepository.update(player);
    return crudMapper.entityToDto(player);
  }

  @Override
  public void delete(Integer integer) {
  }

  @Override
  public Optional<PlayerDto> findByIdOptional(Integer integer) {
    return playerRepository.findByIdOptional(integer).map(crudMapper::entityToDto);
  }

  public Optional<PlayerDto> findByNameOptional(String name) {

    return playerRepository.findByNameOptional(name).map(crudMapper::entityToDto);
  }
}
