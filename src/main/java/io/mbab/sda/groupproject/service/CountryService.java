package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.repository.CountryRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final EntityManager em;

    public Country save(Country country){
        try{
            em.getTransaction().begin();
            if(country.getId() == null){
                country = countryRepository.create(country);
            }
            em.persist(country);
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
        }
        return country;
    }

}
