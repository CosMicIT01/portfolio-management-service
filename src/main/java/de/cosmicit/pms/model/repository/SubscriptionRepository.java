package de.cosmicit.pms.model.repository;


import de.cosmicit.pms.model.entities.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
}
