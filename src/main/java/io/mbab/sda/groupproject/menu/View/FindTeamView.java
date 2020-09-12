package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.action.MenuAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.TeamAction;
import io.mbab.sda.groupproject.repository.TeamRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindTeamView implements MenuAction {

  private final TeamAction teamAction;
  private final CustomScanner cs;
  private final MenuActionContext ctx;
  private final TeamRepository teamRepository;

  @Override
  public void execute() {

    System.out.println("Chcesz szukać drużyny po ID czy po nazwie?");
    System.out.println("Po ID - wciśnij '1'");
    System.out.println("Po nazwie - wciśnij '2'");
    System.out.println("Wyjście do menu glówneo - wciśnij '0'");
    String input = cs.nextLine();
    teamAction.pressedZero(input);

    teamInput(input);

    ctx.execute();
  }

  private void teamInput(String input) {
    if (input.equals("1")) {
      showAllTeams();
      System.out.println("Wybierz ID:");
      String stringID = cs.nextLine();
      int id = 0;
      try {
        id = Integer.parseInt(stringID);
      } catch (NumberFormatException ex) {
        System.out.println("Nie wpisano numeru!");
        ctx.execute();
      }
      Team team = teamRepository.findByIdOptional(id).orElse(null);
      if (team == null) {
        System.out.println("Nie znalezniono drużyny o id " + input);
      }
      System.out.println("Twoja wybrana drużyna:");
      System.out.println(team);
    } else if (input.equals("2")) {
      showAllTeams();
      System.out.println("Wybierz nazwe drużyny:");
      String teamName = cs.nextLine();
      Team team = teamRepository.findByName(teamName);
      if (team == null) {
        System.out.println("Nie ma drużyny o nazwie " + teamName);
      } else {
        System.out.println("Twoja wybrana drużyna to:");
        System.out.println(team);
      }
    } else {
      System.out.println("Wpisano niewłaściwą wartość! Proszę wpisać raz jeszcze.");
      input = cs.nextLine();
      teamInput(input);
    }
  }

  private void showAllTeams() {
    List<Team> allTeams = teamRepository.getAll();
    int i = 1;
    for (Team t : allTeams) {
      System.out.println(i + ". " + t);
      i++;
    }
  }
}
