package io.mbab.sda.groupproject.menu.View;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.dto.PlayerDto;
import io.mbab.sda.groupproject.mapper.JsonUtil;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.action.MenuAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.PlayerAction;
import io.mbab.sda.groupproject.service.CountryService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class CreatePlayerView implements MenuAction {

  private final PlayerAction playerAction;
  private final CustomScanner cs;
  private final MenuActionContext ctx;
  private final CountryService countryService;

  @Override
  public void execute() {

    System.out.println("!!! DODAJESZ PIŁKARZA !!!");
    System.out.println("--> Wciśnięcie '0' powoduję powtór do menu głównego <--");
    System.out.println("Podaj imie gracza:");

    String firstName = cs.nextLine();
    playerAction.pressedZero(firstName);

    System.out.println("!!! DODAJESZ PIŁKARZA !!!");
    System.out.println("Podaj nazwisko gracza:");
    String lastName = cs.nextLine();
    playerAction.pressedZero(lastName);

    System.out.println("!!! DODAJESZ PIŁKARZA !!!");
    System.out.println("Podaj date urodzenia gracza:");
    String dateOfBirth = cs.nextLine();
    playerAction.pressedZero(dateOfBirth);

    System.out.println("!!! DODAJESZ PIŁKARZA !!!");
    System.out.println("Podaj kraj pochodzenia gracza:");
    String countryName = cs.nextLine();
    playerAction.pressedZero(countryName);
    CountryDto country = playerAction.getCountry(countryName);
    country = countryService.save(country);

    PlayerDto player = null;
    boolean isTeamFound = false;
    PlayerDto.PlayerDtoBuilder playerBuilder =
            playerAction.getPlayerBuilder(firstName, lastName, dateOfBirth, country);
    do {
      System.out.println("!!! DODAJESZ PIŁKARZA !!!");
      System.out.println("Podaj nazwę drużyny w której gracz występuję:");
      System.out.println("Pusty znak - piłkarz jest obecnie bez drużyny");
      playerAction.getAll().forEach(teamDto -> System.out.println(teamDto.getName()));
      String teamName = cs.nextLine();
      playerAction.pressedZero(teamName);
      isTeamFound = playerAction.searchTeam(playerBuilder, teamName);
      if (!isTeamFound) {
        System.out.println("Taka drużyna nie istnieje! Proszę podać raz jeszcze.");
      }
    } while (!isTeamFound);
    System.out.println(player);
    player = playerBuilder.build();
    System.out.println(player);
    player = playerAction.createPlayer(player);
    System.out.println("player" + player);
    System.out.println(player);


    System.out.println("Dodałeś piłkarza o danych: \n" + firstName + " " + lastName);
    if (player.getTeam() == null) {
      System.out.println("Bez drużyny");
    } else {
      System.out.println(player.getTeam().getName());
    }
    System.out.println(dateOfBirth);
    System.out.println(countryName);
    System.out.println("Czy chcesz zapisac piłkarzy do pliku Json? \n T - zapis \n inny klawisz powrót");
    String user = cs.nextLine();
    if(user.equals("T")){
      System.out.println("Podaj scieżkę dostępu do pliku:");
      String path = cs.nextLine();
      try {
        JsonUtil.writeToJsonFile(playerAction.getAll(), path);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    ctx.execute();
  }
}
