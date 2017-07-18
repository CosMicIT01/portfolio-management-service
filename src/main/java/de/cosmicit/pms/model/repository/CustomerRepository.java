package de.cosmicit.pms.model.repository;

import de.cosmicit.pms.model.entities.Customer;
import de.cosmicit.pms.model.entities.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT s FROM Subscription s WHERE s.customer = :customer")
    Set<Subscription> getSubscriptionsForCustomer(@Param("customer") Customer customer);
}
