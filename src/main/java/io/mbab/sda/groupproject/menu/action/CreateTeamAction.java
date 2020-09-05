package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTeamAction implements MenuAction {

  private final CustomScanner cs;
  private final LeagueRepository leagueRepository;
  private final CountryRepository countryRepository;
  private final MenuActionContext ctx;

  @Override
  public void execute() {

    System.out.println("--> Wciśnięcie '0' powoduję powtór do menu głównego <--");
    System.out.println("Podaj nazwę drużyny:");

    String name = cs.nextLine();
    if (pressedZero(name)) return;

    var builder = Team.builder().name(name);

    System.out.println("Podaj nawę Ligi:");
    String leagueName = cs.nextLine();
    if (pressedZero(leagueName)) return;

    League league = leagueRepository.findByName(leagueName);

    if (league == null) {

      System.out.println("Tworzysz nową lige o nazwie " + leagueName);
      System.out.println("Z jakiego Państwa jest ta liga?");

      String countryName = cs.nextLine();
      if (pressedZero(countryName)) return;

      Country country = countryRepository.findByName(countryName);

      if (country == null) {
        League.builder().name(leagueName).country(Country.builder().name(countryName).build());
      } else {
        League.builder().country(country).name(leagueName).build();
      }

    } else {
      builder.league(league);
    }

    System.out.println("Podaj nawę miasta:");
    String city = cs.nextLine();
    if (pressedZero(city)) return;
    builder.city(city);

    System.out.println("Podaj wartość drużyny");
    double value = Double.parseDouble(cs.nextLine());
    if (pressedZero(String.valueOf(value))) return;

    builder.city(city);
  }

  private boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }
}
