
package io.mbab.sda.groupproject.menu;

import io.mbab.sda.groupproject.menu.View.ViewLeagueAction;
import io.mbab.sda.groupproject.menu.View.ViewPlayerAction;
import io.mbab.sda.groupproject.menu.View.ViewTeamAction;
import io.mbab.sda.groupproject.menu.action.*;
import io.mbab.sda.groupproject.repository.*;
import io.mbab.sda.groupproject.service.LeagueService;
import io.mbab.sda.groupproject.service.TeamService;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

public class MenuActionContext {

  private MenuAction action;
  private Map<Class<? extends MenuAction>, MenuAction> holder = new HashMap<>();


  public MenuActionContext(CustomScanner scanner, CrudRepositoryFactory repositoryFactory,
                           EntityManager em) {
    initHolder(scanner, repositoryFactory, em);
  }

  public MenuActionContext use(Class<? extends MenuAction> actionClass) {
    action = holder.get(actionClass);
    return this;
  }

  public void execute() {
    if (action == null) throw new RuntimeException("Menu action not set");
    action.execute();
  }

  private void initHolder(CustomScanner scanner, CrudRepositoryFactory repositoryFactory,
                          EntityManager em) {
    holder.put(MainAction.class, new MainAction(scanner, this));
    holder.put(
        CreateLeagueAction.class,
        new CreateLeagueAction(scanner,this,
                new LeagueService(repositoryFactory.get(CountryRepository.class),
                        repositoryFactory.get(LeagueRepository.class),
                        em)));
    holder.put(
        ViewLeagueAction.class,
        new ViewLeagueAction(this, repositoryFactory.get(LeagueRepository.class)));

    holder.put(
        CreatePlayerAction.class,
        new CreatePlayerAction(
            scanner,
            this,
            repositoryFactory.get(CountryRepository.class),
            repositoryFactory.get(PlayerRepository.class),
            repositoryFactory.get(TeamRepository.class)));
    holder.put(
        ViewPlayerAction.class,
        new ViewPlayerAction(this, repositoryFactory.get(PlayerRepository.class)));

    holder.put(
        CreateTeamAction.class,
        new CreateTeamAction(
            scanner,
            this,
            new TeamService(repositoryFactory.get(CountryRepository.class),
                    repositoryFactory.get(LeagueRepository.class),
                    repositoryFactory.get(TeamRepository.class),
                    em)));
    holder.put(
        ViewTeamAction.class,
        new ViewTeamAction(this, repositoryFactory.get(TeamRepository.class)));
  }
}
