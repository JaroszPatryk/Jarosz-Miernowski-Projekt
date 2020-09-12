package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.action.MenuAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.LeagueAction;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindLeagueView implements MenuAction {

  private final LeagueAction leagueAction;
  private final CustomScanner cs;
  private final MenuActionContext ctx;
  private final LeagueRepository leagueRepository;

  @Override
  public void execute() {

    System.out.println("Chcesz szukać ligi po ID czy po nazwie?");
    System.out.println("Po ID - wciśnij '1'");
    System.out.println("Po nazwie - wciśnij '2'");
    System.out.println("Wyjście do menu glówneo - wciśnij '0'");
    String input = cs.nextLine();
    leagueAction.pressedZero(input);

    leagueInput(input);

    ctx.execute();
  }

  private void leagueInput(String input) {
    if (input.equals("1")) {
      showAllLeagues();
      System.out.println("Wybierz ID:");
      String stringID = cs.nextLine();
      int id = 0;
      try {
        id = Integer.parseInt(stringID);
      } catch (NumberFormatException ex) {
        System.out.println("Nie wpisano numeru!");
        ctx.execute();
      }
      League league = leagueRepository.findByIdOptional(id).orElse(null);
      if (league == null) {
        System.out.println("Nie znalezniono ligi o id " + input);
      }
      System.out.println("Twoja wybrana liga:");
      System.out.println(league);
    } else if (input.equals("2")) {
      showAllLeagues();
      System.out.println("Wybierz nazwe ligi:");
      String leagueName = cs.nextLine();
      League league = leagueRepository.findByName(leagueName);
      if (league == null) {
        System.out.println("Nie ma ligi o nazwie " + leagueName);
      } else {
        System.out.println("Twoja wybrana liga to:");
        System.out.println(league);
      }
    } else {
      System.out.println("Wpisano niewłaściwą wartość! Proszę wpisać raz jeszcze.");
      input = cs.nextLine();
      leagueInput(input);
    }
  }

  private void showAllLeagues() {
    List<League> allLeagues = leagueRepository.getAll();
    int i = 1;
    for (League l : allLeagues) {
      System.out.println(i + ". " + l);
      i++;
    }
  }
}
