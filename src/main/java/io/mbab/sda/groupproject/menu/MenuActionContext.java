package io.mbab.sda.groupproject.menu;

import io.mbab.sda.groupproject.menu.View.*;
import io.mbab.sda.groupproject.menu.action.*;
import io.mbab.sda.groupproject.repository.*;
import io.mbab.sda.groupproject.service.CountryService;
import io.mbab.sda.groupproject.service.LeagueService;
import io.mbab.sda.groupproject.service.PlayerService;
import io.mbab.sda.groupproject.service.TeamService;

import java.util.HashMap;
import java.util.Map;

public class MenuActionContext {

  private MenuAction action;
  private Map<Class<? extends MenuAction>, MenuAction> holder = new HashMap<>();


  public MenuActionContext(
          CustomScanner scanner, CrudRepositoryFactory repositoryFactory) {
      initHolder(scanner, repositoryFactory);
  }

    public MenuActionContext use(Class<? extends MenuAction> actionClass) {
        action = holder.get(actionClass);
        return this;
    }

    public void execute() {
        if (action == null) throw new RuntimeException("Menu action not set");
        action.execute();
    }

    private void initHolder(
            CustomScanner scanner, CrudRepositoryFactory repositoryFactory) {
        holder.put(MainAction.class, new MainAction(scanner, this));
        holder.put(
                ViewCreateLeague.class,
                new ViewCreateLeague(
                        scanner,
                        new LeagueAction(
                                this,
                                new LeagueService(
                                        repositoryFactory.get(CountryRepository.class),
                                        repositoryFactory.get(LeagueRepository.class))),
                        this,
                        new CountryService(repositoryFactory.get(CountryRepository.class))));

        holder.put(
                ViewCreateTeam.class,
                new ViewCreateTeam(
                        new TeamAction(
                                this,
                                new TeamService(
                                        repositoryFactory.get(CountryRepository.class),
                                        repositoryFactory.get(LeagueRepository.class),
                                        repositoryFactory.get(TeamRepository.class)),
                                new CountryService(repositoryFactory.get(CountryRepository.class)),
                                new LeagueService(
                                        repositoryFactory.get(CountryRepository.class),
                                        repositoryFactory.get(LeagueRepository.class))),
                        scanner,
                        this,
                        new ViewCreateLeague(
                                scanner,
                                new LeagueAction(
                                        this,
                                        new LeagueService(
                                                repositoryFactory.get(CountryRepository.class),
                                                repositoryFactory.get(LeagueRepository.class))),
                                this,
                                new CountryService(repositoryFactory.get(CountryRepository.class)))));

        holder.put(
                ViewCreatePlayer.class,
                new ViewCreatePlayer(
                        new PlayerAction(
                                this,
                                repositoryFactory.get(CountryRepository.class),
                                new PlayerService(repositoryFactory.get(PlayerRepository.class)),
                                repositoryFactory.get(TeamRepository.class)),
                        scanner,
                        this,
                        new CountryService(repositoryFactory.get(CountryRepository.class))));

        holder.put(
                ViewPlayer.class,
                new ViewPlayer(
                        new PlayerAction(
                                this,
                                repositoryFactory.get(CountryRepository.class),
                                new PlayerService(repositoryFactory.get(PlayerRepository.class)),
                                repositoryFactory.get(TeamRepository.class)),
                        scanner,
                        this,
                        repositoryFactory.get(PlayerRepository.class)));
  }
}
