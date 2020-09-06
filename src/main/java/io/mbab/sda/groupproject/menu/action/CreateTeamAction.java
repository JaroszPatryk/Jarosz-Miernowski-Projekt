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
    if (pressedZero(leagueName)) return;
    var league = teamService
            .getLeagueByName(leagueName)
            .orElseGet(() -> League.builder().name(leagueName).build());

    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("Podaj nawę państwa z której pochodzi drużyna:");
    String countryName = cs.nextLine();
    if (pressedZero(countryName)) return;
    var country =
        teamService
            .getCountryByName(countryName)
            .orElseGet(() -> Country.builder().name(countryName).build());

    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("Podaj nawę miasta z której pochodzi drużyna:");
    String cityName = cs.nextLine();
    if (pressedZero(cityName)) return;
    builder.city(cityName);

    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("Podaj wartość drużyny");
    double value = cs.nextDouble();
    if (pressedZero(String.valueOf(value))) return;

    team = Team.builder().name(name).city(cityName).value(value).league(league).build();

    teamService.saveTeam(country,league,team);
    System.out.println("Dodałeś drużynę o danych: " + name);
    System.out.println(countryName);
    System.out.println(cityName);
    System.out.println(leagueName);
    System.out.println(value);
    ctx.execute();
  }

  private boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }
}
