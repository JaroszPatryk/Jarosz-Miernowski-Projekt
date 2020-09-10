package io.mbab.sda.groupproject.mapper;

import io.mbab.sda.groupproject.dto.LeagueDto;
import io.mbab.sda.groupproject.entity.League;
import org.springframework.stereotype.Component;

@Component
public class LeagueMapper implements CrudMapper<League, LeagueDto> {
    @Override
    public League dtoToEntity(LeagueDto leagueDto) {
        return leagueDto.toEntity();
    }

    @Override
    public LeagueDto entityToDto(League league) {
        return LeagueDto.toDto(league);
    }
}
