package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateLeagueAction implements MenuAction {

  private final CustomScanner cs;
  private final CountryRepository countryRepository;
  private final MenuActionContext ctx;

  @Override
  public void execute() {

    System.out.println("Jaka nazwa ligi?");
    System.out.println("Wcisnij '0' aby powrócić do menu glównego");

    String name = cs.nextLine();
    if (pressedZero(name)) return;

    System.out.println("Z jakiego Państwa jest ta liga?");

    String countryName = cs.nextLine();
    if (pressedZero(countryName)) return;

    Country country = countryRepository.findByName(countryName);
    if (country == null) {
      League.builder().name(name).country(Country.builder().name(countryName).build());
    } else {
      League.builder().country(country).name(name).build();
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
