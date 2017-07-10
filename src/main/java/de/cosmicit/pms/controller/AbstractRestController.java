package de.cosmicit.pms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cosmicit.pms.controller.exception.InvalidParameterException;
import de.cosmicit.pms.controller.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

@Transactional
abstract public class AbstractRestController<T> {

    @Autowired
    EntityManager entityManager;
    @Autowired
    ObjectMapper objectMapper;


    abstract public Class<T> getEntityClass();

    public T get(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return findEntityOrThrowException(id);
    }

    @SuppressWarnings("unchecked")
    public List<T> getList() {
        return entityManager.createQuery(
                "SELECT t FROM " + getEntityClass().getSimpleName() + " t"
        ).getResultList();
    }

    public T create(@RequestBody T entity) throws InvalidParameterException {
        // Don't accept ID field
        if (null != entityManager.getEntityManagerFactory().getPersistenceUnitUtil()
                .getIdentifier(entity)) {
            throw new InvalidParameterException("Invalid ID in request body");
        }
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    public T replace(@PathVariable("id") Long id, @RequestBody String jsonData)
            throws ResourceNotFoundException, InvalidParameterException, IOException {
        T entity = findEntityOrThrowException(id);
        T mergedEntity;
        mergedEntity = objectMapper.readerForUpdating(entity).readValue(jsonData);
        // Don't allow to change ID
        if (!id.equals(
                entityManager.getEntityManagerFactory().getPersistenceUnitUtil()
                        .getIdentifier(mergedEntity))) {
            throw new InvalidParameterException("Invalid Customer ID in request body");
        }
        entityManager.merge(mergedEntity);
        entityManager.flush();
        return mergedEntity;
    }

    public T update(@PathVariable("id") Long id, @RequestBody String jsonData)
            throws ResourceNotFoundException, InvalidParameterException, IOException {
        T entity = findEntityOrThrowException(id);
        T updatedEntity;
        updatedEntity = objectMapper.readerForUpdating(entity).readValue(jsonData);
        // Don't allow to change ID
        if (!id.equals(
                entityManager.getEntityManagerFactory().getPersistenceUnitUtil()
                        .getIdentifier(updatedEntity))) {
            throw new InvalidParameterException("Invalid user ID in request body");
        }
        entityManager.persist(updatedEntity);
        entityManager.flush();
        return updatedEntity;
    }

    public void delete(@PathVariable("id") Long id)
            throws ResourceNotFoundException {
        T entity = findEntityOrThrowException(id);
        entityManager.remove(entity);
        entityManager.flush();
    }

    T findEntityOrThrowException(Long id)
            throws ResourceNotFoundException {
        T entity = entityManager.find(getEntityClass(), id);
        if (null == entity) {
            throw new ResourceNotFoundException("Resource not found");
        }
        return entity;
    }
}
