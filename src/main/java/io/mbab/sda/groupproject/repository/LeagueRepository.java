package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.League;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class LeagueRepository extends AbstractCrudRepository<League, Integer> {

    public LeagueRepository(EntityManager em) {
        super(em, League.class);
    }
}
