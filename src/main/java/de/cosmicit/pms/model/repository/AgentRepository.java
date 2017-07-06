package de.cosmicit.pms.model.repository;


import de.cosmicit.pms.model.entities.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long> {
}
