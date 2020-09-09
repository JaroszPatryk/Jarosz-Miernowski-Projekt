package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.PlayerRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@RequiredArgsConstructor
public class PlayerService implements CrudService<Player, Integer> {

    private final PlayerRepository playerRepository;


    public Player save(Player player) {

        if (player.getId() == null) {
            player = playerRepository.create(player);
        }
        return player;
    }

    @Override
    public List<Player> getAll() {
        return null;
    }

    @Override
    public Player findById(Integer integer) {
        return null;
    }

    @Override
    public Player update(Player entity) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
