package io.mbab.sda.groupproject.mapper;

import io.mbab.sda.groupproject.dto.TeamDto;
import io.mbab.sda.groupproject.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper implements CrudMapper<Team, TeamDto> {
    @Override
    public Team dtoToEntity(TeamDto teamDto) {
        if (teamDto == null) return null;

        return teamDto.toEntity();
    }

    @Override
    public TeamDto entityToDto(Team team) {
        if (team == null) return null;

        return TeamDto.toDto(team);
    }
}
