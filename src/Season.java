import java.io.Serializable;
import java.util.ArrayList;

public class Season implements Serializable {
    // attributes
    private ArrayList<Game> games;
    private Team[] rosters;

    // constructors
    public Season() {
        games = new ArrayList<Game>();
        rosters = new Team[2];
        rosters[0] = new Team();
        rosters[1] = new Team();
    }

    // setter methods
    public void addGame() {
        games.add(new Game());
    }
    public void addGame(Game game) {
        games.add(game);
    }
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }
    public void setGame(int gameIndex, Game game) {
        games.set(gameIndex, game);
    }

    public void setRosters(Team[] rosters) {
        this.rosters = rosters;
    }

    public void setRoster(Team team, int index) {
        rosters[index] = team;
    }
    public void addPlayer(int rosterIndex, Player player) {
        rosters[rosterIndex].addPlayer(player);
    }

    // Game-specific setter methods
    public void setTeams(int gameIndex, ArrayList<Team> teams) {
        Game game = games.get(gameIndex);
        game.setTeams(teams);
        games.set(gameIndex, game);
    }
    public void setTeam(int gameIndex, int teamIndex, Team team) {
        Game game = games.get(gameIndex);
        game.setTeam(teamIndex, team);
        games.set(gameIndex, game);
    }
    public void setGameResult(int gameIndex, boolean gameResult) {
        Game game = games.get(gameIndex);
        game.setGameResult(gameResult);
        games.set(gameIndex, game);
    }
    public void setStadium(int gameIndex, String stadium) {
        Game game = games.get(gameIndex);
        game.setStadium(stadium);
        games.set(gameIndex, game);
    }

    // Team-specific setter methods
    public void setPlayers(int gameIndex, int teamIndex, ArrayList<Player> players) {
        Game game = games.get(gameIndex);
        game.setPlayers(teamIndex, players);
        games.set(gameIndex, game);
    }
    public void setPlayer(int gameIndex, int teamIndex, int playerIndex, Player player) {
        Game game = games.get(gameIndex);
        game.setPlayer(teamIndex, playerIndex, player);
        games.set(gameIndex, game);
    }

    // Player-specific setter methods
    public void setPlayerName(int gameIndex, int teamIndex, int playerIndex, String playerName) {
        Game game = games.get(gameIndex);
        game.setPlayerName(teamIndex, playerIndex, playerName);
        games.set(gameIndex, game);
    }
    public void setDefensePosition(int gameIndex, int teamIndex, int playerIndex, String defensePosition) {
        Game game = games.get(gameIndex);
        game.setDefensePosition(teamIndex, playerIndex, defensePosition);
        games.set(gameIndex, game);
    }

    // getter methods
    public ArrayList<Game> getGames() {
        return games;
    }
    public Game getGame(int gameIndex) {
        return games.get(gameIndex);
    }

    public Team[] getRosters() {
        return rosters;
    }

    public Team getRoster(int index) {
        return rosters[index];
    }

    // Game-specific getter methods
    public ArrayList<Team> getTeams(int gameIndex) {
        return games.get(gameIndex).getTeams();
    }
    public Team getTeam(int gameIndex, int teamIndex) {
        return games.get(gameIndex).getTeam(teamIndex);
    }
    public boolean getGameResult(int gameIndex) {
        return games.get(gameIndex).isGameResult();
    }
    public String getStadium(int gameIndex) {
        return games.get(gameIndex).getStadium();
    }

    // Team-specific getter methods
    public ArrayList<Player> getPlayers(int gameIndex, int teamIndex) {
        return games.get(gameIndex).getPlayers(teamIndex);
    }
    public Player getPlayer(int gameIndex, int teamIndex, int playerIndex) {
        return games.get(gameIndex).getPlayer(teamIndex, playerIndex);
    }

    // Player-specific getter methods
    public String getPlayerName(int gameIndex, int teamIndex, int playerIndex) {
        return games.get(gameIndex).getPlayerName(teamIndex, playerIndex);
    }
    public String getDefensePosition(int gameIndex, int teamIndex, int playerIndex) {
        return games.get(gameIndex).getDefensePosition(teamIndex, playerIndex);
    }
}

