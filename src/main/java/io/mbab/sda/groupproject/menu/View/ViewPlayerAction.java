package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.MainAction;
import io.mbab.sda.groupproject.menu.action.MenuAction;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import io.mbab.sda.groupproject.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ViewPlayerAction implements MenuAction {

    private final MenuActionContext ctx;
    private final PlayerRepository repository;

    @Override
    public void execute() {
        List<Player> players = repository.getAll();

        if (players.isEmpty()) {
            System.out.println("Brak danych do wyświetlenia");
        } else {
            System.out.println("\n");
            players.forEach(System.out::println);
            System.out.println("\n");
        }

        ctx.use(MainAction.class).execute();
    }
}
