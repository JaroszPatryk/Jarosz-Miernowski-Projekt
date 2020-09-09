package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class TeamService implements CrudService<Team, Integer> {

  private final CountryRepository countryRepository;
  private final LeagueRepository leagueRepository;
  private final TeamRepository teamRepository;

  public Team save(Country country, League league, Team team) {

    if (country.getId() == null) {
      country = countryRepository.create(country);
    }

    league = league.toBuilder().country(country).build();
    if (league.getId() == null) {
      league = leagueRepository.create(league);
    }

    team = team.toBuilder().league(league).build();
    team = teamRepository.create(team);

    return team;
  }

  public Team save(Team team) {

    if (team.getId() == null) {
      team = teamRepository.create(team);
    }

    return team;
  }

  public Optional<Country> getCountryByName(String name) {
    return countryRepository.findByNameOptional(name);
  }

  public Optional<League> getLeagueByName(String name) {
    return leagueRepository.findByNameOptional(name);
  }

  @Override
  public List<Team> getAll() {
    return teamRepository.getAll();
  }

  @Override
  public Team findById(Integer integer) {
    return teamRepository.findById(integer);
  }

  @Override
  public Team update(Team entity) {
    return teamRepository.update(entity);
  }

  @Override
  public void delete(Integer integer) {
    teamRepository.delete(integer);
  }
}
