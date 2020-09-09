package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.League;

import javax.persistence.EntityManager;

public class LeagueRepository extends AbstractCrudRepository<League, Integer> {

    public LeagueRepository(EntityManager em) {
        super(em, League.class);
    }
}
