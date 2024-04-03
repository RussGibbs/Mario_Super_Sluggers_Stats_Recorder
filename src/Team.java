import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    // attributes
    private ArrayList<Player> players;

    // constructors
    public Team() {
        players = new ArrayList<Player>();
    }

    // setter methods
    public void addPlayer() {
        players.add(new Player());
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public void setPlayer(int playerIndex, Player player) {
        players.set(playerIndex, player);
    }

    // Player-specific setter methods
    public void setPlayerName(int playerIndex, String playerName) {
        Player player = players.get(playerIndex);
        player.setName(playerName);
        players.set(playerIndex, player);
    }
    public void setDefensePosition(int playerIndex, String defensePosition) {
        Player player = players.get(playerIndex);
        player.setDefensePosition(defensePosition);
        players.set(playerIndex, player);
    }

    // getter methods
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public Player getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    // Player-specific getter methods
    public String getPlayerName(int playerIndex) {
        return players.get(playerIndex).getName();
    }
    public String getDefensePosition(int playerIndex) {
        return players.get(playerIndex).getDefensePosition();
    }
}

