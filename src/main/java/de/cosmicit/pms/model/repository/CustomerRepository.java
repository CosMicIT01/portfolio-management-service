package de.cosmicit.pms.model.repository;

import de.cosmicit.pms.model.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
