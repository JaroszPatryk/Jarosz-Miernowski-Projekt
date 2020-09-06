package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTeamAction implements MenuAction {

  private final CustomScanner cs;
  private final MenuActionContext ctx;
  private final CountryRepository countryRepository;
  private final LeagueRepository leagueRepository;
  private final TeamRepository teamRepository;

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

    League league = leagueRepository.findByName(leagueName);

    if (league == null) {
      System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
      System.out.println("Tworzysz nową lige o nazwie " + leagueName);
      System.out.println("Z jakiego Państwa jest ta liga?");

      String countryName = cs.nextLine();
      if (pressedZero(countryName)) return;

      Country country = countryRepository.findByName(countryName);

      if (country == null) {
        country = Country.builder().name(countryName).build();
        league = League.builder().name(leagueName).country(country).build();
        countryRepository.create(country);
      } else {
        league = League.builder().country(country).name(leagueName).build();
      }
      leagueRepository.create(league);
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

    teamRepository.create(team);
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
