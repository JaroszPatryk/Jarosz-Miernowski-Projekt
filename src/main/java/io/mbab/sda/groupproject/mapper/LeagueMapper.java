package io.mbab.sda.groupproject.mapper;

import io.mbab.sda.groupproject.dto.LeagueDto;
import io.mbab.sda.groupproject.entity.League;
import org.springframework.stereotype.Component;

@Component
public class LeagueMapper implements CrudMapper<League, LeagueDto> {
    @Override
    public League dtoToEntity(LeagueDto leagueDto) {
        if (leagueDto == null) return null;

        return leagueDto.toEntity();
    }

    @Override
    public LeagueDto entityToDto(League league) {
        if (league == null) return null;

        return LeagueDto.toDto(league);
    }
}
