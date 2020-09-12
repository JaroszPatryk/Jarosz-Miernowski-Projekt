package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
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
    //    System.out.println("7) Wyswietl liste zawodników");
    //    System.out.println("8) Wyswietl lige");
    //    System.out.println("9) Wyswietl lige");

    var input = scanner.nextLine();

    if (input.equals("0")) {
      System.exit(0);
      return;
    }

    if (input.equals("1")) {
      ctx.use(CreatePlayerView.class).execute();
      return;
    }

    if (input.equals("2")) {
      ctx.use(CreateTeamView.class).execute();
      return;
    }

    if (input.equals("3")) {
      ctx.use(CreateLeagueView.class).execute();
      return;
    }

    if (input.equals("4")) {
      ctx.use(FindPlayerView.class).execute();
      return;
    }

    if (input.equals("5")) {
      ctx.use(FindTeamView.class).execute();
      return;
    }

    if (input.equals("6")) {
      ctx.use(FindLeagueView.class).execute();
      return;
    }

    System.out.println("Wprowadzono nieprawidłowa wartość!");
    execute();
  }
}
