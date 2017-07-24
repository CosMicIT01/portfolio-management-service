package de.cosmicit.pms.model.repository;


import de.cosmicit.pms.model.entities.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long> {
}
