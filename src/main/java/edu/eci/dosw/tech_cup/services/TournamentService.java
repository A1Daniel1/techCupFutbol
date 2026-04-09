package edu.eci.dosw.tech_cup.services;

import edu.eci.dosw.tech_cup.exception.TechCupException;
import edu.eci.dosw.tech_cup.entities.TournamentEntity;
import edu.eci.dosw.tech_cup.mappers.TournamentMapper;
import edu.eci.dosw.tech_cup.model.Match;
import edu.eci.dosw.tech_cup.model.Referee;
import edu.eci.dosw.tech_cup.model.Team;
import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.repositories.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    public TournamentService(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    public Tournament createTournament(Tournament tournament) {
        if (tournament == null)
            throw new TechCupException(TechCupException.TOURNAMENT_NULL);
        TournamentEntity saved = tournamentRepository.save(tournamentMapper.toEntity(tournament));
        return tournamentMapper.toModel(saved);
    }

    public Tournament registerTeam(int tournamentIndex, Team team) {
        TournamentEntity entity = getEntityByIndex(tournamentIndex);
        Tournament tournament = tournamentMapper.toModel(entity);
        if (team == null)
            throw new TechCupException(TechCupException.TEAM_NULL);
        if (team.getPlayers().size() < 7)
            throw new TechCupException(TechCupException.TEAM_MIN_PLAYERS);
        tournament.registerTeam(team);
        tournamentMapper.updateEntity(entity, tournament);
        TournamentEntity saved = tournamentRepository.save(entity);
        return tournamentMapper.toModel(saved);
    }

    public Tournament scheduleMatch(int tournamentIndex, Match match) {
        TournamentEntity entity = getEntityByIndex(tournamentIndex);
        Tournament tournament = tournamentMapper.toModel(entity);
        if (match == null)
            throw new TechCupException(TechCupException.MATCH_NULL);
        tournament.scheduleMatch(match);
        tournamentMapper.updateEntity(entity, tournament);
        TournamentEntity saved = tournamentRepository.save(entity);
        return tournamentMapper.toModel(saved);
    }

    public Tournament assignReferee(int tournamentIndex, Referee referee) {
        TournamentEntity entity = getEntityByIndex(tournamentIndex);
        Tournament tournament = tournamentMapper.toModel(entity);
        if (referee == null)
            throw new TechCupException(TechCupException.REFEREE_NULL);
        tournament.setReferee(referee);
        tournamentMapper.updateEntity(entity, tournament);
        TournamentEntity saved = tournamentRepository.save(entity);
        return tournamentMapper.toModel(saved);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(tournamentMapper::toModel)
                .toList();
    }

    private TournamentEntity getEntityByIndex(int index) {
        List<TournamentEntity> entities = tournamentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        if (index < 0 || index >= entities.size()) {
            throw new TechCupException(TechCupException.TOURNAMENT_NULL);
        }
        return entities.get(index);
    }
}
