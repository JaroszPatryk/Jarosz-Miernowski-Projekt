package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import io.mbab.sda.groupproject.service.TeamService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CreateTeamAction implements MenuAction {

  private final CustomScanner cs;
  private final MenuActionContext ctx;
  private final TeamService teamService;

  @Override
  public void execute() {

    Team team;
    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("--> Wciśnięcie '0' powoduję powtór do menu głównego <--");
    System.out.println("Podaj nazwę drużyny:");

    String name = cs.nextLine();
    if (pressedZero(name)) return;

    var builder = Team.builder().name(name);

    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("Podaj nawę ligi w której dana drużyna będzie występować:");
    String leagueName = cs.nextLine();
    Optional<Country> country = null;
    if (pressedZero(leagueName)) return;

    League league = teamService.getLeagueByName(leagueName);

    if (league == null) {
      System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
      System.out.println("Tworzysz nową lige o nazwie " + leagueName);
      System.out.println("Z jakiego Państwa jest ta liga?");

      String countryName = cs.nextLine();
      if (pressedZero(countryName)) return;

      country = teamService.getCountryByName(countryName);

      if (country == null) {
        country = Optional.ofNullable(Country.builder().name(countryName).build());
        league = League.builder().name(leagueName).country(Country.builder().build()).build();
      } else {
        league = League.builder().country(Country.builder().build()).name(leagueName).build();
      }

    }

    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("Podaj nawę miasta z której pochodzi drużyna:");
    String city = cs.nextLine();
    if (pressedZero(city)) return;
    builder.city(city);

    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("Podaj wartość drużyny");
    double value = cs.nextDouble();
    if (pressedZero(String.valueOf(value))) return;

    team = Team.builder().name(name).city(city).value(value).league(league).build();

    teamService.saveTeam(Country.builder().build(), league, team);
    System.out.println("Dodałeś drużynę o danych: " + name);
    System.out.println(city);
    System.out.println(leagueName);
    System.out.println(value);
  }

  private boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }
}
