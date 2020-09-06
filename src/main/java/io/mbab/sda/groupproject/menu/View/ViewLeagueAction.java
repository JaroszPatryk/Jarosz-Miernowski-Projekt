package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.MainAction;
import io.mbab.sda.groupproject.menu.action.MenuAction;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor

public class ViewLeagueAction implements MenuAction {

    private final MenuActionContext ctx;
    private final LeagueRepository repository;

    @Override
    public void execute() {
        List<League> leagues = repository.getAll();

        if (leagues.isEmpty()) {
            System.out.println("Brak danych do wyświetlenia");
        } else {
            System.out.println("\n");
            leagues.forEach(System.out::println);
            System.out.println("\n");
        }

        ctx.use(MainAction.class).execute();
    }
}
