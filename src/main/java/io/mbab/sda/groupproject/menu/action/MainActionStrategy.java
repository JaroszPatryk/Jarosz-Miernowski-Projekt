package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.menu.View.*;

import java.util.Map;
import java.util.Optional;

public class MainActionStrategy {
  private final Map<String, Class<? extends MenuAction>> map;

  public MainActionStrategy() {
    this.map = initStrategyMap();
  }

  public Optional<MenuActionContext> prepareCtx(String input, MenuActionContext ctx) {
    if (map.containsKey(input)) {
      return Optional.of(ctx.use(map.get(input)));
    } else {
      return Optional.empty();
    }
  }

  private Map<String, Class<? extends MenuAction>> initStrategyMap() {
    return Map.ofEntries(
        Map.entry("0", ExitAction.class),
        Map.entry("1", CreatePlayerView.class),
        Map.entry("2", CreateTeamView.class),
        Map.entry("3", CreateLeagueView.class),
        Map.entry("4", FindPlayerView.class),
        Map.entry("5", FindTeamView.class),
        Map.entry("6", FindLeagueView.class),
        Map.entry("7", CreateJsonFile.class)
        );
  }
}
