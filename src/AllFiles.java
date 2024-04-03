import java.io.Serializable;
import java.util.ArrayList;

public class AllFiles implements Serializable {
    // attributes
    private ArrayList<Season> seasons;

    // constructors
    public AllFiles() {
        seasons = new ArrayList<Season>();
        //seasons.add(new Season());
    }

    // setter methods
    public void addSeason() {
        seasons.add(new Season());
    }
    public void addSeason(Season season) {
        seasons.add(season);
    }
    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
    public void setSeason(int seasonIndex, Season season) {
        seasons.set(seasonIndex, season);
    }

    // Season-specific setter methods
    public void addGame(int seasonIndex) {
        Season season = seasons.get(seasonIndex);
        season.addGame();
        seasons.set(seasonIndex, season);
    }
    public void addGame(int seasonIndex, Game game) {
        Season season = seasons.get(seasonIndex);
        season.addGame(game);
        seasons.set(seasonIndex, season);
    }
    public void setGames(int seasonIndex, ArrayList<Game> games) {
        Season season = seasons.get(seasonIndex);
        season.setGames(games);
        seasons.set(seasonIndex, season);
    }
    public void setGame(int seasonIndex, int gameIndex, Game game) {
        Season season = seasons.get(seasonIndex);
        season.setGame(gameIndex, game);
        seasons.set(seasonIndex, season);
    }

    // Game-specific setter methods
    public void setTeams(int seasonIndex, int gameIndex, ArrayList<Team> teams) {
        Season season = seasons.get(seasonIndex);
        season.setTeams(gameIndex, teams);
        seasons.set(seasonIndex, season);
    }
    public void setTeam(int seasonIndex, int gameIndex, int teamIndex, Team team){
        Season season = seasons.get(seasonIndex);
        season.setTeam(gameIndex, teamIndex, team);
        seasons.set(seasonIndex, season);
    }
    public void setGameResult(int seasonIndex, int gameIndex, boolean gameResult){
        Season season = seasons.get(seasonIndex);
        season.setGameResult(gameIndex, gameResult);
        seasons.set(seasonIndex, season);
    }
    public void setStadium(int seasonIndex, int gameIndex, String stadium){
        Season season = seasons.get(seasonIndex);
        season.setStadium(gameIndex, stadium);
        seasons.set(seasonIndex, season);
    }

    // Team specific setter methods
    public void setPlayers(int seasonIndex, int gameIndex, int teamIndex, ArrayList<Player> players) {
        Season season = seasons.get(seasonIndex);
        season.setPlayers(gameIndex, teamIndex, players);
        seasons.set(seasonIndex, season);
    }
    public void setPlayer(int seasonIndex, int gameIndex, int teamIndex, int playerIndex, Player player){
        Season season = seasons.get(seasonIndex);
        season.setPlayer(gameIndex, teamIndex, playerIndex, player);
        seasons.set(seasonIndex, season);
    }

    // Player specific setter methods
    public void setPlayerName(int seasonIndex, int gameIndex, int teamIndex, int playerIndex, String playerName){
        Season season = seasons.get(seasonIndex);
        season.setPlayerName(gameIndex, teamIndex, playerIndex, playerName);
        seasons.set(seasonIndex, season);
    }
    public void setDefensePosition(int seasonIndex, int gameIndex, int teamIndex, int playerIndex, String defensePosition){
        Season season = seasons.get(seasonIndex);
        season.setDefensePosition(gameIndex, teamIndex, playerIndex, defensePosition);
        seasons.set(seasonIndex, season);
    }

    // getter methods
    public ArrayList<Season> getSeasons() {
        return seasons;
    }
    public Season getSeason(int seasonIndex) {
        return seasons.get(seasonIndex);
    }

    // Season-specific getter methods
    public ArrayList<Game> getGames(int seasonIndex) {
        return seasons.get(seasonIndex).getGames();
    }
    public Game getGame(int seasonIndex, int gameIndex) {
        return seasons.get(seasonIndex).getGame(gameIndex);
    }

    // Game-specific getter methods
    public ArrayList<Team> getTeams(int seasonIndex, int gameIndex) {
        return seasons.get(seasonIndex).getTeams(gameIndex);
    }
    public Team getTeam(int seasonIndex, int gameIndex, int teamIndex) {
        return seasons.get(seasonIndex).getTeam(gameIndex, teamIndex);
    }
    public boolean getGameResult(int seasonIndex, int gameIndex) {
        return seasons.get(seasonIndex).getGameResult(gameIndex);
    }
    public String getStadium(int seasonIndex, int gameIndex) {
        return seasons.get(seasonIndex).getStadium(gameIndex);
    }

    // Team specific getter methods
    public ArrayList<Player> getPlayers(int seasonIndex, int gameIndex, int teamIndex) {
        return seasons.get(seasonIndex).getPlayers(gameIndex, teamIndex);
    }
    public Player getPlayer(int seasonIndex, int gameIndex, int teamIndex, int playerIndex) {
        return seasons.get(seasonIndex).getPlayer(gameIndex, teamIndex, playerIndex);
    }

    // Player specific getter methods
    public String getPlayerName(int seasonIndex, int gameIndex, int teamIndex, int playerIndex) {
        return seasons.get(seasonIndex).getPlayerName(gameIndex, teamIndex, playerIndex);
    }
    public String getDefensePosition(int seasonIndex, int gameIndex, int teamIndex, int playerIndex) {
        return seasons.get(seasonIndex).getDefensePosition(gameIndex, teamIndex, playerIndex);
    }
}

