package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.dto.LeagueDto;
import io.mbab.sda.groupproject.dto.TeamDto;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.action.MenuAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.TeamAction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTeamView implements MenuAction {

  private final TeamAction teamAction;
  private final CustomScanner cs;
  private final MenuActionContext ctx;
  private final CreateLeagueView createLeagueView;

  @Override
  public void execute() {

    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("--> Wciśnięcie '0' powoduję powtór do menu głównego <--");
    System.out.println("Podaj nazwę drużyny:");
    String teamName = cs.nextLine();
    teamAction.pressedZero(teamName);

    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("Podaj nawę miasta z której pochodzi drużyna:");
    String cityName = cs.nextLine();
    teamAction.pressedZero(cityName);

    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("Podaj nawę państwa z której pochodzi drużyna:");
    String countryName = cs.nextLine();
    teamAction.pressedZero(countryName);

      CountryDto country = teamAction.getCountry(countryName);
    teamAction.saveCountry(country);

    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("Podaj nawę ligi w której dana drużyna będzie występować:");
    String leagueName = cs.nextLine();
    teamAction.pressedZero(leagueName);

      LeagueDto league = teamAction.getLeague(leagueName);
    if (league == null) {
      System.out.println("Podałeś nową nazwę ligi.");
      System.out.println("Dodaj nową ligę albo wciśnij '0'.");
      league = createLeagueView.createLeague(leagueName);
    }

    System.out.println("!!! TWORZYSZ NOWĄ DRUŻYNĘ !!!");
    System.out.println("Podaj wartość drużyny");
    String value = cs.nextLine();
    teamAction.pressedZero(value);
    //    double value = 1000;

    TeamDto team = teamAction.getTeam(teamName, cityName, country, league, value);
    team = teamAction.saveTeam(team);
    System.out.println(team);

    System.out.println("Dodałeś drużynę o danych: " + teamName);
    System.out.println(countryName);
    System.out.println(cityName);
    System.out.println(leagueName);
    System.out.println(value);
    ctx.execute();
  }
}
