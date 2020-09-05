package io.mbab.sda.groupproject.menu.View;

import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.action.MainAction;
import io.mbab.sda.groupproject.menu.action.MenuAction;
import io.mbab.sda.groupproject.repository.PlayerRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ViewTeamAction implements MenuAction {

    private final MenuActionContext ctx;
    private final TeamRepository repository;
    @Override
    public void execute() {
        List<Team> teams = repository.getAll();

        if (teams.isEmpty()) {
            System.out.println("Brak danych do wyświetlenia");
        } else {
            System.out.println("\n");
            teams.forEach(System.out::println);
            System.out.println("\n");
        }

        ctx.use(MainAction.class).execute();
    }
}
