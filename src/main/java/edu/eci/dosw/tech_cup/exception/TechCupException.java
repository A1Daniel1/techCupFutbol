package edu.eci.dosw.tech_cup.exception;

public class TechCupException extends RuntimeException {

    public static final String TOURNAMENT_NULL         = "Tournament cannot be null";
    public static final String TEAM_NULL               = "Team cannot be null";
    public static final String TEAM_NAME_EMPTY         = "Team name cannot be null or empty";
    public static final String PLAYER_NULL             = "Player cannot be null";
    public static final String PLAYER_DUPLICATE        = "Player already belongs to this team";
    public static final String CAPTAIN_NULL            = "Captain cannot be null";
    public static final String MATCH_NULL              = "Match cannot be null";
    public static final String LINEUP_NULL             = "LineUp cannot be null";
    public static final String LINEUP_INCOMPLETE       = "LineUp must have exactly 11 players";
    public static final String REFEREE_NULL            = "Referee cannot be null";
    public static final String TEAM_MIN_PLAYERS        = "Team must have at least 7 players to register";
    public static final String PENALTY_CARDS_NULL      = "PenaltyCards cannot be null";

    public TechCupException(String message) {
        super(message);
    }

    public TechCupException(String message, Throwable cause) {
        super(message, cause);
    }
}
