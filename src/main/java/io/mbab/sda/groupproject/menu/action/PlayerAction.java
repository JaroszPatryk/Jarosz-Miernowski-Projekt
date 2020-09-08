package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.entity.Player.PlayerBuilder;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MainAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.PlayerRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import io.mbab.sda.groupproject.service.PlayerService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerAction {


  private final MenuActionContext ctx;
  private final CountryRepository countryRepository;
  private final PlayerService playerService;
  private final TeamRepository teamRepository;

  public void createPlayer(Player player) {
    playerService.save(player);
  }

  public PlayerBuilder getPlayerBuilder(
      String firstName, String lastName, String dateOfBirth, Country country) {
    return Player.builder()
        .firstName(firstName)
        .lastName(lastName)
        .dateOfBirth(dateOfBirth)
        .country(country);
  }

  public Country getCountry(String countryName) {
    return countryRepository
        .findByName(countryName)
        .orElseGet(() -> Country.builder().name(countryName).build());
  }

  public boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }

  //  public void addTeam(PlayerBuilder builder) {
  //    String teamName = scanner.nextLine();
  //
  //    searchTeam(builder, teamName);
  //  }

  public boolean searchTeam(PlayerBuilder builder, String teamName) {
    if (" ".equals(teamName) || "".equals(teamName)) {
      builder.team(null);
      return true;
    }
    Team team = teamRepository.findByName(teamName);
    if (team == null) {
      return false;
    }
    return true;

    //      teamRepository
    //          .findByName(teamName)
    //          .ifPresentOrElse(
    //              team -> builder.team(team).build(),
    //              () -> {
    //                System.out.println("Nie znaleziono drużyny o podanej nazwie!");
    //                System.out.println("Wciśnij '0' jak chcesz wyjść do głownego menu.");
    //
    //                addTeam(builder);
    //              });
  }
}
