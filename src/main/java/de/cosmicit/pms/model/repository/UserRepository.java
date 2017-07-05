package de.cosmicit.pms.model.repository;

import de.cosmicit.pms.model.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
