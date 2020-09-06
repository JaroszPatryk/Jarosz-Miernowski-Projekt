package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import io.mbab.sda.groupproject.service.LeagueService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class CreateLeagueAction implements MenuAction {

  private final CustomScanner cs;
  //private final CountryRepository countryRepository;
  private final MenuActionContext ctx;
  //private final LeagueRepository leagueRepository;
  private final LeagueService leagueService;

  @Override
  public void execute() {


    System.out.println("!!! DODAJESZ LIGĘ !!!");
    System.out.println("--> Wciśnięcie '0' powoduję powtór do menu głównego <--");
    System.out.println("Jaka nazwa ligi?");

    String name = cs.nextLine();
    if (pressedZero(name)) return;

    System.out.println("!!! DODAJESZ LIGĘ !!!");
    System.out.println("Z jakiego Państwa jest ta liga?");

    String countryName = cs.nextLine();
    if (pressedZero(countryName)) return;

    var country =
            leagueService
                    .getCountryByName(countryName)
                    .orElseGet(() -> Country.builder().name(countryName).build());
    var league = League.builder().name(name).build();
    leagueService.saveLeague(country, league);
    System.out.println("Dodano ligę o nazwie: " + name + ", kraj pochodzenia: " + countryName);
  }

  private boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }
}
