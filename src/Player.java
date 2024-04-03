import java.io.Serializable;

public class Player implements Serializable {
    // attributes
    private String name;
    private String defensePosition;
    private String pitch;
    private Stats stats;

    // constructors
    public Player() {
        name = "";
        defensePosition = "";
        pitch = "";
        stats = new Stats();
    }

    // setter methods
    public void setName(String name) {
        this.name = name;
    }
    public void setDefensePosition(String defensePosition) {
        this.defensePosition = defensePosition;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    // getter methods
    public String getName() {
        return name;
    }
    public String getDefensePosition() {
        return defensePosition;
    }

    public String getPitch() {
        return pitch;
    }

    public Stats getStats() {
        return stats;
    }

    @Override
    public String toString() {
        return name;
    }

    // combine stats
    public void combineStats(Stats stats) {
        this.stats.addGamesPlayed(stats.getGamesPlayed());
        this.stats.addAtBats(stats.getAtBats());
        this.stats.addHits(stats.getHits());
        this.stats.addRuns(stats.getRuns());
        this.stats.addRbis(stats.getRbis());
        this.stats.addSingles(stats.getSingles());
        this.stats.addDoubles(stats.getDoubles());
        this.stats.addTriples(stats.getTriples());
        this.stats.addHomeRuns(stats.getHomeRuns());
        this.stats.addGamesStarted(stats.getGamesStarted());
        this.stats.addGamesPitched(stats.getGamesPitched());
        this.stats.addInningsPitched(stats.getInningsPitched());
        this.stats.addStrikeOuts(stats.getStrikeOuts());
        this.stats.addSaves(stats.getSaves());
        this.stats.addShutOuts(stats.getShutOuts());
        this.stats.addNoHitters(stats.getNoHitters());
        this.stats.addPerfectGames(stats.getPerfectGames());
        this.stats.addSteals(stats.getSteals());
        this.stats.addHitterWalks(stats.getHitterWalks());
        this.stats.addSacrifices(stats.getSacrifices());
        this.stats.addPlateAppearances(stats.getPlateAppearances());
        this.stats.addPitcherWalks(stats.getPitcherWalks());
        this.stats.addPitcherHits(stats.getPitcherHits());
        this.stats.addEarnedRuns(stats.getEarnedRuns());
        this.stats.addErrors(stats.getErrors());
        this.stats.addWins(stats.getWins());
        this.stats.addLosses(stats.getLosses());
        this.stats.addCompleteGames(stats.getCompleteGames());
        this.stats.addSaveOpportunities(stats.getSaveOpportunities());
        this.stats.addHolds(stats.getHolds());
        this.stats.addHomeRunsAllowed(stats.getHomeRunsAllowed());
        this.stats.addCaughtStealing(stats.getCaughtStealing());
    }
}

