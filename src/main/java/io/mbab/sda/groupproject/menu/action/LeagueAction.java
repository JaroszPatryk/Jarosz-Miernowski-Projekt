package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MainAction;
import io.mbab.sda.groupproject.menu.MenuAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.service.LeagueService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LeagueAction {

  private final MenuActionContext ctx;
  private final LeagueService leagueService;

  public League getLeague(String name, Country country) {
    return League.builder().name(name).country(country).build();
  }

  public void save(League league) {
    leagueService.save(league);
  }

  public Country getCountry(String countryName) {
    return leagueService
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
}
