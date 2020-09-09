package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Team;

import javax.persistence.EntityManager;

public class TeamRepository extends AbstractCrudRepository<Team, Integer> {

    public TeamRepository(EntityManager em) {
        super(em, Team.class);
    }
}
