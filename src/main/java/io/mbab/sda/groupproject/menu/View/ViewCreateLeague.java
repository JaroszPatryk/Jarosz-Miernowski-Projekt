package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.dto.LeagueDto;
import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.LeagueAction;
import io.mbab.sda.groupproject.service.CountryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ViewCreateLeague implements MenuAction {

  private final CustomScanner cs;
  private final LeagueAction leagueAction;
  private final MenuActionContext ctx;
  private final CountryService countryService;

  @Override
  public void execute() {

    System.out.println("!!! DODAJESZ LIGĘ !!!");
    System.out.println("--> Wciśnięcie '0' powoduję powtór do menu głównego <--");
    System.out.println("Jaka nazwa ligi?");

    String leagueName = cs.nextLine();
    leagueAction.pressedZero(leagueName);
    LeagueDto league = leagueAction.findLeagueByName(leagueName);
    if (league == null) {
      createLeague(leagueName);
    } else {
      System.out.println("Już istnieje taka liga! Proszę podać inną nazwę.");
    }

    ctx.execute();
  }

  public LeagueDto createLeague(String leagueName) {

    System.out.println("!!! DODAJESZ LIGĘ !!!");
    System.out.println("Z jakiego Państwa jest ta liga?");

    String countryName = cs.nextLine();
    leagueAction.pressedZero(countryName);


    CountryDto country = leagueAction.getCountry(countryName);
    countryService.save(country);

    LeagueDto league = leagueAction.getLeague(leagueName, country);
    league = leagueAction.save(league);
    System.out.println(league);

    System.out.println(
            "Dodano ligę o nazwie: " + leagueName + ", kraj pochodzenia: " + countryName);
    return league;
  }
}
