package de.cosmicit.pms.model.repository;


import de.cosmicit.pms.model.entities.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServicesRepository extends CrudRepository<Service, Long> {
}
