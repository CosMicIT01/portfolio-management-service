package de.cosmicit.pms.model.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class CollectionDeserializer extends JsonDeserializer<Set<?>>{

    @Autowired
    EntityManager entityManager;

    @Override
    public Set<Object> deserialize(JsonParser p, DeserializationContext ctxt)
            throws RuntimeException, IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);

        Class<?> entityClass = getEntityClass(p);
        Set<Object> collection = new HashSet<Object>(0);

        for (JsonNode n : node) {
            Long id = n.asLong();
            if (id > 0) {
                Object entity = entityManager.find(entityClass, id);
                if (null == entity) {
                    throw new RuntimeException("Entity not found. Name: " + entityClass.getSimpleName() + " ID: " + id);
                }
                collection.add(entity);
            } else {
                throw new RuntimeException("Can not resolve id of entity. Name: " + entityClass.getSimpleName());
            }
        }
        return collection;
    }

    private Class<?> getEntityClass(JsonParser p) throws IOException {
        Type entityClassType = ReflectionUtils.findField(p.getCurrentValue().getClass(), p.getCurrentName()).getGenericType();
        if (entityClassType instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) entityClassType).getActualTypeArguments()[0];
        } else {
            throw new RuntimeException("Can not find definition of entity Collection");
        }
    }
}
