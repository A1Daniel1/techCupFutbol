package edu.eci.dosw.tech_cup.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import edu.eci.dosw.tech_cup.entities.TournamentEntity;
import edu.eci.dosw.tech_cup.model.Tournament;
import org.springframework.stereotype.Component;

@Component
public class TournamentMapper {

    private final ObjectMapper objectMapper;

    public TournamentMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper.copy();
        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL);
    }

    public TournamentEntity toEntity(Tournament tournament) {
        TournamentEntity entity = new TournamentEntity();
        entity.setPayload(write(tournament));
        return entity;
    }

    public Tournament toModel(TournamentEntity entity) {
        return read(entity.getPayload(), Tournament.class);
    }

    public void updateEntity(TournamentEntity entity, Tournament tournament) {
        entity.setPayload(write(tournament));
    }

    private String write(Tournament tournament) {
        try {
            return objectMapper.writeValueAsString(tournament);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Unable to map Tournament to entity", e);
        }
    }

    private <T> T read(String payload, Class<T> clazz) {
        try {
            return objectMapper.readValue(payload, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Unable to map entity to Tournament", e);
        }
    }
}
