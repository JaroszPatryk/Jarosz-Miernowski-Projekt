package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.City;
import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CityRepository;
import io.mbab.sda.groupproject.repository.CountryRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class CreatePlayerAction implements MenuAction {

  private final CustomScanner scanner;
  private final MenuActionContext ctx;
  private final CountryRepository countryRepository;

  @Override
  public void execute() {

    System.out.println("0) Przejdź do poprzedniego menu");
    System.out.println("Podaj imie gracza:");
    var input = scanner.nextLine();
    if (pressedZero(input)) return;
    var builder = Player.builder().firstName(input);

    System.out.println("Podaj nazwisko gracza:");
    input = scanner.nextLine();
    if (pressedZero(input)) return;
    var lastName = builder.lastName(input).build();

    System.out.println("Podaj date urodzenia gracza:");
    input = scanner.nextLine();
    if (pressedZero(input)) return;
    var date = builder.dateOfBirth(input).build();

    System.out.println("Podaj kraj pochodzenia gracza:");
    input = scanner.nextLine();
    if (pressedZero(input)) return;
    Country country = countryRepository.findByName(input);
    if(country == null){
      System.out.println("Tworzysz nowy kraj:" + input);
    }else{
      builder.country(country);
    }
    
  }

  private boolean pressedZero(String input) {
    if (input.equals("0")) {
      ctx.use(MainAction.class).execute();
      return true;
    }
    return false;
  }
}
