package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.MainAction;
import io.mbab.sda.groupproject.menu.action.MenuAction;
import io.mbab.sda.groupproject.repository.CityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ViewCitiesAction implements MenuAction {

  private final MenuActionContext ctx;
  private final CityRepository repository;

  @Override
  public void execute() {
    var cities = repository.getAll();

    if (cities.isEmpty()) {
      System.out.println("Brak danych do wyświetlenia");
    } else {
      System.out.println("\n");
      cities.forEach(System.out::println);
      System.out.println("\n");
    }

    ctx.use(MainAction.class).execute();
  }
}
