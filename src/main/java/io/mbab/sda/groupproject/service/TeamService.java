package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.dto.LeagueDto;
import io.mbab.sda.groupproject.dto.TeamDto;
import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.mapper.CrudMapper;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Service
@RequiredArgsConstructor
public class TeamService implements CrudService<TeamDto, Integer> {

    private final CountryRepository countryRepository;
    private final LeagueRepository leagueRepository;
    private final TeamRepository teamRepository;
    protected final CrudMapper<Team, TeamDto> crudMapper;
    protected final CrudMapper<Country, CountryDto> crudMapperCountry;
    protected final CrudMapper<League, LeagueDto> crudMapperLeague;

    public TeamDto findByName(String name) {
        teamRepository.findByName(name);
        return crudMapper.entityToDto(teamRepository.findByName(name));
    }

    public TeamDto save(CountryDto countryDto, LeagueDto leagueDto, TeamDto teamDto) {

        if (countryDto.getId() == null) {
            Country country = crudMapperCountry.dtoToEntity(countryDto);
            countryRepository.create(country);
            countryDto = crudMapperCountry.entityToDto(country);
        }

        leagueDto = leagueDto.toBuilder().country(countryDto).build();
        if (leagueDto.getId() == null) {
            League league = crudMapperLeague.dtoToEntity(leagueDto);
            leagueRepository.create(league);
            leagueDto = crudMapperLeague.entityToDto(league);
        }

        teamDto = teamDto.toBuilder().league(leagueDto).build();
        Team team = crudMapper.dtoToEntity(teamDto);
        teamRepository.create(team);
        teamDto = crudMapper.entityToDto(team);

        return teamDto;
    }

    public TeamDto save(TeamDto teamDto) {

        if (teamDto.getId() == null) {
            Team team = crudMapper.dtoToEntity(teamDto);
      System.out.println(team);
            teamRepository.create(team);
            System.out.println(team);
            teamDto = crudMapper.entityToDto(team);
            System.out.println(teamDto);
        }

        return teamDto;
    }

    public Optional<CountryDto> getCountryByName(String name) {

        return countryRepository.findByNameOptional(name).map(crudMapperCountry::entityToDto);
    }

    public Optional<LeagueDto> getLeagueByName(String name) {

        return leagueRepository.findByNameOptional(name).map(crudMapperLeague::entityToDto);
    }

    @Override
    public List<TeamDto> getAll() {

        return teamRepository.getAll().stream()
                .map(crudMapper::entityToDto)
                .collect(Collectors.toUnmodifiableList());
    }


    @Override
    public TeamDto update(TeamDto dto) {
        Team team = crudMapper.dtoToEntity(dto);
        teamRepository.update(team);
        return crudMapper.entityToDto(team);
    }

    @Override
    public void delete(Integer integer) {
        teamRepository.delete(integer);
    }

    @Override
    public Optional<TeamDto> findByIdOptional(Integer integer) {
        return teamRepository.findByIdOptional(integer).map(TeamDto::toDto);
    }
}
