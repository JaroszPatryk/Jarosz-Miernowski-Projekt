package io.mbab.sda.groupproject.mapper;

import io.mbab.sda.groupproject.dto.TeamDto;
import io.mbab.sda.groupproject.entity.Team;

public class TeamMapper implements CrudMapper<Team, TeamDto> {
  @Override
  public Team dtoToEntity(TeamDto teamDto) {
    return teamDto.toEntity();
  }

  @Override
  public TeamDto entityToDto(Team team) {
    return TeamDto.toDto(team);
  }
}
