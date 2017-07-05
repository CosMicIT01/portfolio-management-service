package de.cosmicit.pms.model.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;

public class ReferenceDeserializer extends JsonDeserializer<Object> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);
        Class<?> entityClass = ReflectionUtils.findField(p.getCurrentValue().getClass(), p.getCurrentName()).getType();
        if (node.asLong() > 0) {
            Long id = node.asLong();
            Object entity = entityManager.find(entityClass, id);
            if (null == entity) {
                throw new RuntimeException("Entity not found. Name: " + entityClass.getSimpleName() + " ID: " + id);
            }
            return entity;
        } else {
            throw new RuntimeException("Can not resolve id of entity. Name: " + entityClass.getSimpleName());
        }
    }
}
