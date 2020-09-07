package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MainAction;
import io.mbab.sda.groupproject.menu.MenuAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.service.CountryService;
import io.mbab.sda.groupproject.service.LeagueService;
import io.mbab.sda.groupproject.service.TeamService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamAction {

  private final MenuActionContext ctx;
  private final TeamService teamService;
  private final CountryService countryService;
  private final LeagueService leagueService;

  public void saveLeague(League league) {
    leagueService.save(league);
  }

  public void saveTeam(Team team) {
    teamService.save(team);
  }

  public Team getTeam(String name, String cityName, Country country, League league, double value) {
    return Team.builder()
        .name(name)
        .country(country)
        .city(cityName)
        .value(value)
        .league(league)
        .build();
  }

  public League getLeague(String leagueName) {
    return teamService.getLeagueByName(leagueName).orElse(null);
  }

  public void saveCountry(Country country) {
    countryService.save(country);
  }

  public Country getCountry(String countryName) {
    return teamService
        .getCountryByName(countryName)
        .orElseGet(() -> Country.builder().name(countryName).build());
  }

  public boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }

  public boolean pressedZero(double input) {
    if (input == 0) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }
}
