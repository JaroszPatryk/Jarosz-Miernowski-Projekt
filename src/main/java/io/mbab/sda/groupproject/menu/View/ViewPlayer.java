package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.dto.PlayerDto;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.PlayerAction;
import io.mbab.sda.groupproject.repository.PlayerRepository;
import io.mbab.sda.groupproject.service.PlayerService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ViewPlayer implements MenuAction {

  private final PlayerAction playerAction;
  private final CustomScanner cs;
    private final MenuActionContext ctx;
    private final PlayerService playerService;

  @Override
  public void execute() {

    System.out.println("Chcesz szukać zawodnika po ID czy po nazwisku?");
    System.out.println("Po ID - wciśnij '1'");
    System.out.println("Po nazwisku - wciśnij '2'");
    System.out.println("Wyjście do menu glówneo - wciśnij '0'");
    String input = cs.nextLine();
    playerAction.pressedZero(input);

    playerInput(input);

    ctx.execute();
  }

  private void playerInput(String input) {
    if (input.equals("1")) {
      showAllPlayers();
      System.out.println("Wybierz ID:");
      String stringID = cs.nextLine();
      int id = 0;
      try {
        id = Integer.parseInt(stringID);
      } catch (NumberFormatException ex) {
        System.out.println("Nie wpisano numeru!");
        ctx.execute();
      }
        PlayerDto player = playerService.findByIdOptional(id).orElse(null);
      if (player == null) {
        System.out.println("Nie znalezniono zawonika o id " + input);
      }
      System.out.println("Twój wybrany zawonik:");
      System.out.println(player);
    } else if (input.equals("2")) {
      showAllPlayers();
      System.out.println("Wybierz nazwisko:");
        String playerName = cs.nextLine();
        PlayerDto player = playerService.findByNameOptional(playerName).orElse(null);
      if (player == null) {
        System.out.println("Nie ma zawodnika o nazwisku " + playerName);
      } else {
        System.out.println("Twój wybrany zawonik:");
        System.out.println(player);
      }
    } else {
      System.out.println("Wpisano niewłaściwą wartość! Proszę wpisać raz jeszcze.");
      input = cs.nextLine();
      playerInput(input);
    }
  }

  private void showAllPlayers() {
      List<PlayerDto> allPlayers = playerService.getAll();
      int i = 1;
      for (PlayerDto p : allPlayers) {
          System.out.println(i + ". " + p);
          i++;
      }
  }
}
