package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.dto.PlayerDto;
import io.mbab.sda.groupproject.mapper.JsonUtil;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.action.MenuAction;
import io.mbab.sda.groupproject.repository.PlayerRepository;
import io.mbab.sda.groupproject.service.PlayerService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class CreateJsonFile implements MenuAction {

  private final CustomScanner cs;
  private final PlayerRepository playerRepository;
  private final PlayerService playerService;

  public void execute() {

    System.out.println("!!! Zapisujesz JSONA !!!");
    System.out.println("--> Wciśnięcie '0' powoduję powtór do menu głównego <--");
    System.out.println("Jaka nazwa pliku");

    List<PlayerDto> players = playerService.getAll();
    String jsonName = cs.nextLine();
      try {
          JsonUtil.writeToJsonFile(players, jsonName);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}
