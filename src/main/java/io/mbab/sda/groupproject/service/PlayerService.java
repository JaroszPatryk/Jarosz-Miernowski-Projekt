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
public class PlayerService {

  private final PlayerRepository playerRepository;
  private final EntityManager em;

  public Player save(Player player) {
    try {
      em.getTransaction().begin();
      if (player.getId() == null) {
        player = playerRepository.create(player);
      }
      em.persist(player);
      em.getTransaction().commit();
    } catch (Exception ex) {
      em.getTransaction().rollback();
    }

    return player;
  }
}
