package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AbstractCrudRepository<ENTITY, ID> implements CrudRepository<ENTITY, ID> {

  protected EntityManager em;
  private Class<ENTITY> entityClass;

  @Override
  public List<ENTITY> getAll() {

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(entityClass);
    var root = criteriaQuery.from(entityClass);
    return em.createQuery(criteriaQuery.select(root)).getResultList();
  }

  public Optional<ENTITY> findByNameOptional(String name) {

    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(entityClass);
      var root = criteriaQuery.from(entityClass);

      return Optional.of(
              em.createQuery(
                      criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"),
                              name)))
                      .getSingleResult());
    } catch (NoResultException ex) {
      return Optional.empty();
    }
  }

  public ENTITY findByName(String name) {

    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(entityClass);
      var root = criteriaQuery.from(entityClass);

      return em.createQuery(
              criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name)))
              .getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }

    @Override
    public ENTITY findById(ID integer) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(entityClass);
        var root = criteriaQuery.from(entityClass);
        return em.createQuery(
                criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), integer)))
                .getSingleResult();
    }

    @Override
    public Optional<ENTITY> findByIdOptional(ID integer) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            var criteriaQuery = criteriaBuilder.createQuery(entityClass);
            var root = criteriaQuery.from(entityClass);
            return Optional.of(em.createQuery(
                    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), integer)))
                    .getSingleResult());
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public ENTITY create(ENTITY entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    return entity;
  }

  @Override
  public ENTITY update(ENTITY entity) {
    try {
      em.getTransaction().begin();
      em.persist(entity);
      em.getTransaction().commit();
    } catch (Exception ex) {
      em.getTransaction().rollback();
    }
    return entity;
  }

  @Override
  public void delete(ID integer) {
    try {
      em.getTransaction().begin();
      em.remove(findById(integer));
      em.getTransaction().commit();
    } catch (Exception ex) {
      em.getTransaction().rollback();
    }
  }
}
