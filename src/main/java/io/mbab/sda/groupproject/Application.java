package io.mbab.sda.groupproject;

import io.mbab.sda.groupproject.config.Configuration;
import io.mbab.sda.groupproject.config.JpaUtil;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.InitDatabase;
import io.mbab.sda.groupproject.menu.action.MainAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.*;

public class Application {

  public static void main(String... args) {
    var emFactory =
            JpaUtil.getEntityManagerFactory(
                    Configuration.getDataSource(), Configuration.getEntityClass());
    var repositoryFactory = new CrudRepositoryFactory(emFactory);
    CountryRepository countryRepository = repositoryFactory.get(CountryRepository.class);
    TeamRepository teamRepository = repositoryFactory.get(TeamRepository.class);
    LeagueRepository leagueRepository = repositoryFactory.get(LeagueRepository.class);
    PlayerRepository playerRepository = repositoryFactory.get(PlayerRepository.class);


    InitDatabase initDatabase =
            new InitDatabase(countryRepository, teamRepository, leagueRepository, playerRepository);

    initDatabase.init();

    var scanner = new CustomScanner();

    new MenuActionContext(scanner, repositoryFactory).use(MainAction.class).execute();
  }
}
