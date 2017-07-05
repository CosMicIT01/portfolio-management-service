package de.cosmicit.pms.model.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Set;

@JsonComponent
public class CollectionSerializer extends JsonSerializer<Set<Object>> {

    @Autowired
    EntityManager entityManager;

    @Override
    public void serialize(Set<Object> collection,
            com.fasterxml.jackson.core.JsonGenerator gen,
            com.fasterxml.jackson.databind.SerializerProvider serializers)
                    throws IOException ,JsonProcessingException {
        gen.writeStartArray();
        for (Object entity : collection) {
            gen.writeNumber((Long) entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity));
        }
        gen.writeEndArray();
    };
}
