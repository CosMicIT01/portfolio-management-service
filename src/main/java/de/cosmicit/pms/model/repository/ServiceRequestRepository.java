package de.cosmicit.pms.model.repository;


import de.cosmicit.pms.model.entities.ServiceRequest;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRequestRepository extends CrudRepository<ServiceRequest, Long> {
}
