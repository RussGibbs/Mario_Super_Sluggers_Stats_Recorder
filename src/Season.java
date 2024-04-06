import java.io.Serializable;
import java.util.ArrayList;


// class Season includes games and rosters
public class Season implements Serializable {
    // attributes
    private final ArrayList<Game> games;
    private final Team[] rosters;

    // constructor
    public Season() {
        games = new ArrayList<>();
        rosters = new Team[2];
        rosters[0] = new Team();
        rosters[1] = new Team();
    }

    // setter methods
    public void addGame() {
        games.add(new Game());
    }

    public void setRoster(Team team, int index) {
        rosters[index] = team;
    }

    public void addPlayer(int rosterIndex, Player player) {
        rosters[rosterIndex].addPlayer(player);
    }

    // getter methods
    public ArrayList<Game> getGames() {
        return games;
    }

    public Game getGame(int gameIndex) {
        return games.get(gameIndex);
    }

    public Team getRoster(int index) {
        return rosters[index];
    }

    // Game-specific getter methods
    public Team getTeam(int gameIndex, int teamIndex) {
        return games.get(gameIndex).getTeam(teamIndex);
    }
}
