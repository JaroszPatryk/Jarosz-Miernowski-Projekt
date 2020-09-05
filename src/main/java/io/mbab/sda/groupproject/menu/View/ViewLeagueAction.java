package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.MainAction;
import io.mbab.sda.groupproject.menu.action.MenuAction;
import io.mbab.sda.groupproject.repository.CityRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
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
