package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.dto.LeagueDto;
import io.mbab.sda.groupproject.dto.TeamDto;
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

    public void saveLeague(LeagueDto dto) {
        leagueService.save(dto);
    }

    public void saveTeam(TeamDto dto) {
        teamService.save(dto);
    }

    public TeamDto getTeam(String name, String cityName, CountryDto country, LeagueDto league, String value) {
        return TeamDto.builder()
                .name(name)
                .country(country)
                .city(cityName)
                .value(value)
                .league(league)
                .build();
    }

    public LeagueDto getLeague(String leagueName) {
        return teamService.getLeagueByName(leagueName).orElse(null);
    }

    public void saveCountry(CountryDto dto) {
        countryService.save(dto);
    }

    public CountryDto getCountry(String countryName) {
        return teamService
                .getCountryByName(countryName)
                .orElseGet(() -> CountryDto.builder().name(countryName).build());
    }

    public boolean pressedZero(String input) {
        if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }
}
