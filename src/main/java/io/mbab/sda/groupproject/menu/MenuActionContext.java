
package io.mbab.sda.groupproject.menu;

import io.mbab.sda.groupproject.menu.View.ViewLeagueAction;
import io.mbab.sda.groupproject.menu.View.ViewPlayerAction;
import io.mbab.sda.groupproject.menu.View.ViewTeamAction;
import io.mbab.sda.groupproject.menu.action.*;
import io.mbab.sda.groupproject.repository.*;

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
        CreateLeagueAction.class,
        new CreateLeagueAction(scanner,repositoryFactory.get(CountryRepository.class) ,this, repositoryFactory.get(LeagueRepository.class)));
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
            repositoryFactory.get(CountryRepository.class),
            repositoryFactory.get(LeagueRepository.class),
            repositoryFactory.get(TeamRepository.class)));
    holder.put(
        ViewTeamAction.class,
        new ViewTeamAction(this, repositoryFactory.get(TeamRepository.class)));
  }
}
