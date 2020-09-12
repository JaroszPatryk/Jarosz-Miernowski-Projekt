package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TeamRepository extends AbstractCrudRepository<Team, Integer> {

    public TeamRepository(EntityManager em) {
        super(em, Team.class);
    }
}
