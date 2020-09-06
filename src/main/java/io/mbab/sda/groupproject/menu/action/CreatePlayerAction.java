package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.entity.Player.PlayerBuilder;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.PlayerRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import io.mbab.sda.groupproject.service.CountryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePlayerAction implements MenuAction {

  private final CustomScanner scanner;
  private final MenuActionContext ctx;
  private final CountryRepository countryRepository;
  private final PlayerRepository playerRepository;
  private final TeamRepository teamRepository;
  private final CountryService countryService;

  @Override
  public void execute() {

    System.out.println("!!! DODAJESZ PIŁKARZA !!!");
    System.out.println("--> Wciśnięcie '0' powoduję powtór do menu głównego <--");
    System.out.println("Podaj imie gracza:");
    String firstName = scanner.nextLine();
    if (pressedZero(firstName)) {
      return;
    }

    var builder = Player.builder().firstName(firstName);

    System.out.println("!!! DODAJESZ PIŁKARZA !!!");
    System.out.println("Podaj nazwisko gracza:");
    String lastName = scanner.nextLine();
    if (pressedZero(lastName)) {
      return;
    }

    builder.lastName(lastName);

    System.out.println("!!! DODAJESZ PIŁKARZA !!!");
    System.out.println("Podaj date urodzenia gracza:");
    String dateOfBirth = scanner.nextLine();
    if (pressedZero(dateOfBirth)) {
      return;
    }

    builder.dateOfBirth(dateOfBirth);

    System.out.println("!!! DODAJESZ PIŁKARZA !!!");
    System.out.println("Podaj kraj pochodzenia gracza:");

    String countryName = scanner.nextLine();

    if (pressedZero(countryName)) {
      return;
    }
    var country =
        countryRepository
            .findByName(countryName)
            .orElseGet(() -> Country.builder().name(countryName).build());



    addTeam(builder);

    countryService.save(country);

    builder.country(country);
    Player player = builder.build();


    playerRepository.create(player);

    System.out.println("Dodałeś piłkarza o danych: " + firstName + " " + lastName);
    if (player.getTeam() == null) {
      System.out.println("Bez drużyny");
    } else {
      System.out.println(player.getTeam().getName());
    }
    System.out.println(dateOfBirth);
    System.out.println(countryName);

    ctx.execute();
  }

  private boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }

  private void addTeam(PlayerBuilder builder) {
    System.out.println("!!! DODAJESZ PIŁKARZA !!!");
    System.out.println("Podaj drużynę w której gracz występuję:");
    System.out.println("Pusty znak - piłkarz jest obecnie bez drużyny");
    String teamName = scanner.nextLine();
    pressedZero(teamName);

    teamRepository
        .findByName(teamName)
        .ifPresentOrElse(
            team -> builder.team(team),
            () -> {
              System.out.println("Nie znaleziono drużyny o podanej nazwie!");
              System.out.println("Wciśnij '0' jak chcesz wyjść do głownego menu.");

              addTeam(builder);
            });
  }
}
