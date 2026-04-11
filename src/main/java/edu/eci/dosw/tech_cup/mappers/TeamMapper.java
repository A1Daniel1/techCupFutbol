package edu.eci.dosw.tech_cup.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.dosw.tech_cup.entities.TeamEntity;
import edu.eci.dosw.tech_cup.enums.TypePlayer;
import edu.eci.dosw.tech_cup.enums.TypeUser;
import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamMapper {

    private final ObjectMapper objectMapper;

    public TeamMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper.copy();
    }

    public TeamEntity toEntity(Team team) {
        TeamEntity entity = new TeamEntity();
        entity.setPayload(write(toPayload(team)));
        return entity;
    }

    public Team toModel(TeamEntity entity) {
        TeamPayload payload = read(entity.getPayload(), TeamPayload.class);
        Team team = new Team(payload.getName());
        team.setMatches(payload.getMatches());
        team.setFairPlay(payload.getFairPlay());

        List<StoredPlayer> storedPlayers = payload.getPlayers();
        if (storedPlayers != null) {
            for (StoredPlayer player : storedPlayers) {
                team.addPlayer(player);
            }
        }

        if (payload.getCaptainIndex() >= 0 && payload.getCaptainIndex() < team.getPlayers().size()) {
            team.setCaptain(team.getPlayers().get(payload.getCaptainIndex()));
        }

        return team;
    }

    public void updateEntity(TeamEntity entity, Team team) {
        entity.setPayload(write(toPayload(team)));
    }

    private TeamPayload toPayload(Team team) {
        TeamPayload payload = new TeamPayload();
        payload.setName(team.getName());
        payload.setMatches(team.getMatches());
        payload.setFairPlay(team.getFairPlay());

        List<StoredPlayer> players = new ArrayList<>();
        int captainIndex = -1;
        for (int i = 0; i < team.getPlayers().size(); i++) {
            Player player = team.getPlayers().get(i);
            players.add(StoredPlayer.from(player));
            if (team.getCaptain() != null && team.getCaptain() == player) {
                captainIndex = i;
            }
        }

        payload.setPlayers(players);
        payload.setCaptainIndex(captainIndex);
        return payload;
    }

    private String write(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Unable to map Team to entity", e);
        }
    }

    private <T> T read(String payload, Class<T> clazz) {
        try {
            return objectMapper.readValue(payload, clazz);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Unable to map entity to Team", e);
        }
    }

    public static class TeamPayload {
        private String name;
        private int matches;
        private int fairPlay;
        private List<StoredPlayer> players = new ArrayList<>();
        private int captainIndex = -1;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getMatches() {
            return matches;
        }

        public void setMatches(int matches) {
            this.matches = matches;
        }

        public int getFairPlay() {
            return fairPlay;
        }

        public void setFairPlay(int fairPlay) {
            this.fairPlay = fairPlay;
        }

        public List<StoredPlayer> getPlayers() {
            return players;
        }

        public void setPlayers(List<StoredPlayer> players) {
            this.players = players;
        }

        public int getCaptainIndex() {
            return captainIndex;
        }

        public void setCaptainIndex(int captainIndex) {
            this.captainIndex = captainIndex;
        }
    }

    public static class StoredPlayer extends Player {
        public StoredPlayer() {
            super();
        }

        public static StoredPlayer from(Player source) {
            StoredPlayer stored = new StoredPlayer();
            stored.setId(source.getId());
            stored.setName(source.getName());
            stored.setEmail(source.getEmail());
            stored.setAge(source.getAge());
            stored.setRole(source.getRole() == null ? TypeUser.PLAYER : source.getRole());
            stored.setNumber(source.getNumber());
            stored.setNickName(source.getNickName());
            stored.setPosition(source.getPosition());
            stored.setAvailable(source.isAvailable());
            TypePlayer type = source.getTypePlayer();
            if (type != null) {
                stored.setTypePlayer(type);
            }
            return stored;
        }
    }
}
