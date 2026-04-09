package edu.eci.dosw.tech_cup.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import edu.eci.dosw.tech_cup.entities.MatchEntity;
import edu.eci.dosw.tech_cup.model.Match;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {

    private final ObjectMapper objectMapper;

    public MatchMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper.copy();
        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL);
    }

    public MatchEntity toEntity(Match match) {
        MatchEntity entity = new MatchEntity();
        entity.setPayload(write(match));
        return entity;
    }

    public Match toModel(MatchEntity entity) {
        return read(entity.getPayload(), Match.class);
    }

    public void updateEntity(MatchEntity entity, Match match) {
        entity.setPayload(write(match));
    }

    private String write(Match match) {
        try {
            return objectMapper.writeValueAsString(match);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Unable to map Match to entity", e);
        }
    }

    private <T> T read(String payload, Class<T> clazz) {
        try {
            return objectMapper.readValue(payload, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Unable to map entity to Match", e);
        }
    }
}
