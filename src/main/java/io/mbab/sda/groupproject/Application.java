package io.mbab.sda.groupproject;

import io.mbab.sda.groupproject.config.Configuration;
import io.mbab.sda.groupproject.config.JpaUtil;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.InitDatabase;
import io.mbab.sda.groupproject.menu.MainAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.*;
import io.mbab.sda.groupproject.service.CountryService;
import io.mbab.sda.groupproject.service.LeagueService;
import io.mbab.sda.groupproject.service.PlayerService;
import io.mbab.sda.groupproject.service.TeamService;

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

    final CountryService countryService = new CountryService(countryRepository);
    final TeamService teamService = new TeamService(countryRepository, leagueRepository, teamRepository);
    final LeagueService leagueService = new LeagueService(countryRepository, leagueRepository);
    final PlayerService playerService = new PlayerService(playerRepository);

    InitDatabase initDatabase =
            new InitDatabase(countryService, teamService, leagueService, playerService);

    initDatabase.init();

    var scanner = new CustomScanner();

    new MenuActionContext(scanner, repositoryFactory).use(MainAction.class).execute();
  }
}
