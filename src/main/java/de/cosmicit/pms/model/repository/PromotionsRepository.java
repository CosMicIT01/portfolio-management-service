package de.cosmicit.pms.model.repository;


import de.cosmicit.pms.model.entities.Promotion;
import org.springframework.data.repository.CrudRepository;

public interface PromotionsRepository extends CrudRepository<Promotion, Long> {
}
