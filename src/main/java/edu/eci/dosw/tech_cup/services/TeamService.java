package edu.eci.dosw.tech_cup.services;

import edu.eci.dosw.tech_cup.exception.TechCupException;
import edu.eci.dosw.tech_cup.entities.TeamEntity;
import edu.eci.dosw.tech_cup.mappers.TeamMapper;
import edu.eci.dosw.tech_cup.model.Player;
import edu.eci.dosw.tech_cup.model.Team;
import edu.eci.dosw.tech_cup.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final AtomicLong apiIdSequence = new AtomicLong(1);
    private final Map<Long, Long> apiToDbIds = new ConcurrentHashMap<>();

    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public List<PersistedTeam> getTeams() {
        List<PersistedTeam> teams = new ArrayList<>();
        for (TeamEntity entity : teamRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))) {
            Long apiId = resolveApiId(entity.getId());
            teams.add(new PersistedTeam(apiId, teamMapper.toModel(entity)));
        }
        return teams;
    }

    public PersistedTeam createTeam(String name) {
        if (name == null || name.isBlank())
            throw new TechCupException(TechCupException.TEAM_NAME_EMPTY);
        TeamEntity saved = teamRepository.save(teamMapper.toEntity(new Team(name)));
        Long apiId = apiIdSequence.getAndIncrement();
        apiToDbIds.put(apiId, saved.getId());
        return new PersistedTeam(apiId, teamMapper.toModel(saved));
    }

    public PersistedTeam addPlayerToTeam(long teamId, Player player) {
        TeamEntity entity = getEntityOrThrow(teamId);
        Team team = teamMapper.toModel(entity);
        if (team == null)
            throw new TechCupException(TechCupException.TEAM_NULL);
        if (player == null)
            throw new TechCupException(TechCupException.PLAYER_NULL);
        if (team.getPlayers().contains(player))
            throw new TechCupException(TechCupException.PLAYER_DUPLICATE);
        team.addPlayer(player);
        teamMapper.updateEntity(entity, team);
        TeamEntity saved = teamRepository.save(entity);
        return new PersistedTeam(saved.getId(), teamMapper.toModel(saved));
    }

    public PersistedTeam setCaptain(long teamId, int playerIndex) {
        TeamEntity entity = getEntityOrThrow(teamId);
        Team team = teamMapper.toModel(entity);
        if (playerIndex < 0 || playerIndex >= team.getPlayers().size()) {
            throw new IllegalArgumentException("Captain index out of bounds");
        }
        Player captain = team.getPlayers().get(playerIndex);
        if (captain == null)
            throw new TechCupException(TechCupException.CAPTAIN_NULL);
        team.setCaptain(captain);
        teamMapper.updateEntity(entity, team);
        TeamEntity saved = teamRepository.save(entity);
        return new PersistedTeam(saved.getId(), teamMapper.toModel(saved));
    }

    private TeamEntity getEntityOrThrow(long teamId) {
        Long dbId = apiToDbIds.get(teamId);
        if (dbId == null) {
            dbId = teamId;
        }
        return teamRepository.findById(dbId)
                .orElseThrow(() -> new TechCupException(TechCupException.TEAM_NULL));
    }

    private Long resolveApiId(Long dbId) {
        for (Map.Entry<Long, Long> entry : apiToDbIds.entrySet()) {
            if (entry.getValue().equals(dbId)) {
                return entry.getKey();
            }
        }
        Long apiId = apiIdSequence.getAndIncrement();
        apiToDbIds.put(apiId, dbId);
        return apiId;
    }

    public record PersistedTeam(Long id, Team team) {
    }
}

