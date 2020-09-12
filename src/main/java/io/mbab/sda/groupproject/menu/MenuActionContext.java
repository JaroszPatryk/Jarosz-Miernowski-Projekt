package io.mbab.sda.groupproject.menu;

import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.mapper.CountryMapper;
import io.mbab.sda.groupproject.mapper.LeagueMapper;
import io.mbab.sda.groupproject.mapper.PlayerMapper;
import io.mbab.sda.groupproject.mapper.TeamMapper;
import io.mbab.sda.groupproject.menu.View.*;
import io.mbab.sda.groupproject.menu.action.LeagueAction;
import io.mbab.sda.groupproject.menu.action.PlayerAction;
import io.mbab.sda.groupproject.menu.action.TeamAction;
import io.mbab.sda.groupproject.repository.*;
import io.mbab.sda.groupproject.service.CountryService;
import io.mbab.sda.groupproject.service.LeagueService;
import io.mbab.sda.groupproject.service.PlayerService;
import io.mbab.sda.groupproject.service.TeamService;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

public class MenuActionContext {

  private MenuAction action;
  private Map<Class<? extends MenuAction>, MenuAction> holder = new HashMap<>();

  public MenuActionContext(CustomScanner scanner, CrudRepositoryFactory repositoryFactory) {
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

  private void initHolder(CustomScanner scanner, CrudRepositoryFactory repositoryFactory) {
    holder.put(MainAction.class, new MainAction(scanner, this));
    holder.put(
        ViewCreateLeague.class,
        new ViewCreateLeague(
            scanner,
            new LeagueAction(
                this,
                new LeagueService(
                    repositoryFactory.get(CountryRepository.class),
                    repositoryFactory.get(LeagueRepository.class),
                    new LeagueMapper(),
                    new CountryMapper())),
            this,
            new CountryService(
                repositoryFactory.get(CountryRepository.class), new CountryMapper())));

    holder.put(
        ViewLeague.class,
        new ViewLeague(
            new LeagueAction(
                this,
                new LeagueService(
                    repositoryFactory.get(CountryRepository.class),
                    repositoryFactory.get(LeagueRepository.class),
                    new LeagueMapper(),
                    new CountryMapper())),
            scanner,
            this,
            repositoryFactory.get(LeagueRepository.class)));

    holder.put(
        ViewCreateTeam.class,
        new ViewCreateTeam(
            new TeamAction(
                this,
                new TeamService(
                    repositoryFactory.get(CountryRepository.class),
                    repositoryFactory.get(LeagueRepository.class),
                    repositoryFactory.get(TeamRepository.class),
                    new TeamMapper(),
                    new CountryMapper(),
                    new LeagueMapper()),
                new CountryService(
                    repositoryFactory.get(CountryRepository.class), new CountryMapper()),
                new LeagueService(
                    repositoryFactory.get(CountryRepository.class),
                    repositoryFactory.get(LeagueRepository.class),
                    new LeagueMapper(),
                    new CountryMapper())),
            scanner,
            this,
            new ViewCreateLeague(
                scanner,
                new LeagueAction(
                    this,
                    new LeagueService(
                        repositoryFactory.get(CountryRepository.class),
                        repositoryFactory.get(LeagueRepository.class),
                        new LeagueMapper(),
                        new CountryMapper())),
                this,
                new CountryService(
                    repositoryFactory.get(CountryRepository.class), new CountryMapper()))));

    holder.put(
            ViewTeam.class,
            new ViewTeam(
                    new TeamAction(
                            this,
                            new TeamService(
                                    repositoryFactory.get(CountryRepository.class),
                                    repositoryFactory.get(LeagueRepository.class),
                                    repositoryFactory.get(TeamRepository.class),
                                    new TeamMapper(),
                                    new CountryMapper(),
                                    new LeagueMapper()),
                            new CountryService(
                                    repositoryFactory.get(CountryRepository.class), new CountryMapper()),
                            new LeagueService(
                                    repositoryFactory.get(CountryRepository.class),
                                    repositoryFactory.get(LeagueRepository.class),
                                    new LeagueMapper(),
                                    new CountryMapper())),
                    scanner,
                    this,
                    repositoryFactory.get(TeamRepository.class)));

    holder.put(
        ViewCreatePlayer.class,
        new ViewCreatePlayer(
            new PlayerAction(
                this,
                new CountryService(
                    repositoryFactory.get(CountryRepository.class), new CountryMapper()),
                new PlayerService(
                    repositoryFactory.get(PlayerRepository.class), new PlayerMapper()),
                new TeamService(
                    repositoryFactory.get(CountryRepository.class),
                    repositoryFactory.get(LeagueRepository.class),
                    repositoryFactory.get(TeamRepository.class),
                    new TeamMapper(),
                    new CountryMapper(),
                    new LeagueMapper())),
            scanner,
            this,
            new CountryService(
                repositoryFactory.get(CountryRepository.class), new CountryMapper())));

    holder.put(
        ViewPlayer.class,
        new ViewPlayer(
            new PlayerAction(
                this,
                new CountryService(
                    repositoryFactory.get(CountryRepository.class), new CountryMapper()),
                new PlayerService(
                    repositoryFactory.get(PlayerRepository.class), new PlayerMapper()),
                new TeamService(
                    repositoryFactory.get(CountryRepository.class),
                    repositoryFactory.get(LeagueRepository.class),
                    repositoryFactory.get(TeamRepository.class),
                    new TeamMapper(),
                    new CountryMapper(),
                    new LeagueMapper())),
            scanner,
            this,
            new PlayerService(repositoryFactory.get(PlayerRepository.class), new PlayerMapper())));
  }
}
