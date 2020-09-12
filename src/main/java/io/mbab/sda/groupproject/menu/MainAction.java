package io.mbab.sda.groupproject.menu;

import io.mbab.sda.groupproject.menu.View.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainAction implements MenuAction {

  private final CustomScanner scanner;
  private final MenuActionContext ctx;

  @Override
  public void execute() {
    System.out.println("0) Zamknij aplikację");
    System.out.println("1) Dodaj zawodnika");
    System.out.println("2) Dodaj drużyne");
    System.out.println("3) Dodaj lige");
    System.out.println("4) Wyswietl zawodnika");
    System.out.println("5) Wyswietl druzyne");
    System.out.println("6) Wyswietl lige");

    var input = scanner.nextLine();

    if (input.equals("0")) {
      System.exit(0);
      return;
    }

    if (input.equals("1")) {
      ctx.use(ViewCreatePlayer.class).execute();
      return;
    }

    if (input.equals("2")) {
      ctx.use(ViewCreateTeam.class).execute();
      return;
    }

    if (input.equals("3")) {
      ctx.use(ViewCreateLeague.class).execute();
      return;
    }

    if (input.equals("4")) {
      ctx.use(ViewPlayer.class).execute();
      return;
    }

    //    if (input.equals("5")) {
    //      ctx.use(ViewTeamAction.class).execute();
    //      return;
    //    }
    //
    //    if (input.equals("6")) {
    //      ctx.use(ViewLeagueAction.class).execute();
    //      return;
    //    }

    System.out.println("Wprowadzono nieprawidłowa wartość!");
    execute();
  }
}
