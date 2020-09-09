package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.mapper.CrudMapper;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public class CountryRepository extends AbstractCrudRepository<Country, Integer> {


    public CountryRepository(EntityManager em) {
        super(em, Country.class);
    }
}
