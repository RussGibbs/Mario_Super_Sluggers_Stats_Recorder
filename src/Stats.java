import java.io.Serializable;

public class Stats implements Serializable {
    // batting stats
    private int gamesPlayed;
    private int atBats;
    private int hits;
    private int runs;
    private int strikeouts;
    private int rbis;
    private int singles;
    private int doubles;
    private int triples;
    private int homeRuns;

    private int hitterWalks;
    private int sacrifices;
    private int plateAppearances;

    // pitching stats
    private int completeGames;
    private int wins;
    private int losses;
    private int gamesStarted;
    private int gamesFinished;
    private int singlesAllowed;
    private int doublesAllowed;
    private int triplesAllowed;
    private int gamesPitched;
    private double inningsPitched;
    private int strikeOuts;
    private int homeRunsAllowed;
    private int saves;
    private int saveOpportunities;
    private int holds;
    private int shutOuts;
    private int noHitters;
    private int perfectGames;

    private int pitcherWalks;
    private int pitcherHits;
    private int earnedRuns;

    // Misc
    private int steals;
    private int caughtStealing;
    private int errors;

    public Stats() {
        gamesPlayed = 0;
        atBats = 0;
        hits = 0;
        runs = 0;
        strikeouts = 0;
        rbis = 0;
        singles = 0;
        doubles = 0;
        triples = 0;
        homeRuns = 0;

        hitterWalks = 0;
        sacrifices = 0;
        plateAppearances = 0;

        completeGames = 0;
        wins = 0;
        losses = 0;
        gamesStarted = 0;
        gamesFinished = 0;
        singlesAllowed = 0;
        doublesAllowed = 0;
        triplesAllowed = 0;
        gamesPitched = 0;
        inningsPitched = 0;
        strikeOuts = 0;
        homeRunsAllowed = 0;
        saves = 0;
        saveOpportunities = 0;
        holds = 0;
        shutOuts = 0;
        noHitters = 0;
        perfectGames = 0;

        pitcherWalks = 0;
        pitcherHits = 0;
        earnedRuns = 0;

        steals = 0;
        caughtStealing = 0;
        errors = 0;
    }

    // getters


    public int getDoublesAllowed() {
        return doublesAllowed;
    }

    public int getGamesFinished() {
        return gamesFinished;
    }


    public int getStrikeouts() {
        return strikeouts;
    }

    public int getSinglesAllowed() {
        return singlesAllowed;
    }

    public int getTriplesAllowed() {
        return triplesAllowed;
    }

    public int getCaughtStealing() {
        return caughtStealing;
    }

    public int getHomeRunsAllowed() {
        return homeRunsAllowed;
    }

    public int getHolds() {
        return holds;
    }

    public int getSaveOpportunities() {
        return saveOpportunities;
    }

    public int getCompleteGames() {
        return completeGames;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }

    public int getGamesPitched() {
        return gamesPitched;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public double getInningsPitched() {
        return inningsPitched;
    }

    public int getAtBats() {
        return atBats;
    }

    public int getDoubles() {
        return doubles;
    }

    public int getGamesStarted() {
        return gamesStarted;
    }

    public int getHits() {
        return hits;
    }

    public int getHomeRuns() {
        return homeRuns;
    }

    public int getNoHitters() {
        return noHitters;
    }

    public int getPerfectGames() {
        return perfectGames;
    }

    public int getRbis() {
        return rbis;
    }

    public int getRuns() {
        return runs;
    }

    public int getSaves() {
        return saves;
    }

    public int getShutOuts() {
        return shutOuts;
    }

    public int getSingles() {
        return singles;
    }

    public int getSteals() {
        return steals;
    }

    public int getStrikeOuts() {
        return strikeOuts;
    }

    public int getTriples() {
        return triples;
    }

    public int getEarnedRuns() {
        return earnedRuns;
    }

    public int getErrors() {
        return errors;
    }

    public int getHitterWalks() {
        return hitterWalks;
    }

    public int getPitcherHits() {
        return pitcherHits;
    }

    public int getPitcherWalks() {
        return pitcherWalks;
    }

    public int getPlateAppearances() {
        return plateAppearances;
    }

    public int getSacrifices() {
        return sacrifices;
    }

    // Setters
    public void setDoublesAllowed(int doublesAllowed) {
        this.doublesAllowed = doublesAllowed;
    }

    public void setGamesFinished(int gamesFinished) {
        this.gamesFinished = gamesFinished;
    }

    public void setSinglesAllowed(int singlesAllowed) {
        this.singlesAllowed = singlesAllowed;
    }

    public void setStrikeouts(int strikeouts) {
        this.strikeouts = strikeouts;
    }

    public void setTriplesAllowed(int triplesAllowed) {
        this.triplesAllowed = triplesAllowed;
    }

    public void setCaughtStealing(int caughtStealing) {
        this.caughtStealing = caughtStealing;
    }

    public void setHomeRunsAllowed(int homeRunsAllowed) {
        this.homeRunsAllowed = homeRunsAllowed;
    }

    public void setHolds(int holds) {
        this.holds = holds;
    }

    public void setSaveOpportunities(int saveOpportunities) {
        this.saveOpportunities = saveOpportunities;
    }

    public void setCompleteGames(int completeGames) {
        this.completeGames = completeGames;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setGamesPitched(int gamesPitched) {
        this.gamesPitched = gamesPitched;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setAtBats(int atBats) {
        this.atBats = atBats;
    }

    public void setDoubles(int doubles) {
        this.doubles = doubles;
    }

    public void setGamesStarted(int gamesStarted) {
        this.gamesStarted = gamesStarted;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setHomeRuns(int homeRuns) {
        this.homeRuns = homeRuns;
    }

    public void setInningsPitched(double inningsPitched) {
        this.inningsPitched = inningsPitched;
    }

    public void setNoHitters(int noHitters) {
        this.noHitters = noHitters;
    }

    public void setPerfectGames(int perfectGames) {
        this.perfectGames = perfectGames;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public void setRbis(int rbis) {
        this.rbis = rbis;
    }

    public void setSaves(int saves) {
        this.saves = saves;
    }

    public void setShutOuts(int shutOuts) {
        this.shutOuts = shutOuts;
    }

    public void setSingles(int singles) {
        this.singles = singles;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public void setStrikeOuts(int strikeOuts) {
        this.strikeOuts = strikeOuts;
    }

    public void setTriples(int triples) {
        this.triples = triples;
    }



    public void setEarnedRuns(int earnedRuns) {
        this.earnedRuns = earnedRuns;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public void setHitterWalks(int hitterWalks) {
        this.hitterWalks = hitterWalks;
    }

    public void setPitcherHits(int pitcherHits) {
        this.pitcherHits = pitcherHits;
    }

    public void setPitcherWalks(int pitcherWalks) {
        this.pitcherWalks = pitcherWalks;
    }

    public void setPlateAppearances(int plateAppearances) {
        this.plateAppearances = plateAppearances;
    }

    public void setSacrifices(int sacrifices) {
        this.sacrifices = sacrifices;
    }



    // adders
    public void addDoubleAllowed() {
        this.doublesAllowed ++;
    }

    public void addGameFinished() {
        this.gamesFinished ++;
    }

    public void addSingleAllowed() {
        this.singlesAllowed ++;
    }

    public void addStrikeout() {
        this.strikeouts ++;
    }

    public void addTripleAllowed() {
        this.triplesAllowed ++;
    }
    public void addHomeRunAllowed() {
        homeRunsAllowed++;
    }

    public void addCaughtStealing() {
        caughtStealing++;
    }
    public void addSaveOpportunity() {
        saveOpportunities++;
    }

    public void addHold() {
        holds++;
    }
    public void addCompleteGame() {
        completeGames++;
    }
    public void addWin() {
        wins++;
    }

    public void addLoss() {
        losses++;
    }
    public void addGamePitched() {
        gamesPitched++;
    }

    public void addGamePlayed() {
        gamesPlayed++;
    }
    public void addAtBat() {
        atBats++;
    }

    public void addDouble() {
        doubles++;
    }

    public void addGameStarted() {
        gamesStarted++;
    }

    public void addHit() {
        hits++;
    }

    public void addHomeRun() {
        homeRuns++;
    }

    public void addInningPitched() {
        if (inningsPitched % 1 <= 0.11 || inningsPitched % 1 >= 0.99) {
            inningsPitched += 0.1;
        }
        else {
            inningsPitched += 0.8;
        }
    }

    public void addNoHitter() {
        noHitters++;
    }

    public void addPerfectGame() {
        perfectGames++;
    }

    public void addRun() {
        runs++;
    }

    public void addRbi() {
        rbis++;
    }

    public void addSave() {
        saves++;
    }

    public void addShutOut() {
        shutOuts++;
    }

    public void addSingle() {
        singles++;
    }

    public void addSteal() {
        steals++;
    }

    public void addStrikeOut() {
        strikeOuts++;
    }

    public void addTriple() {
        triples++;
    }

    public void addEarnedRun() {
        earnedRuns++;
    }

    public void addError() {
        errors++;
    }

    public void addHitterWalk() {
        hitterWalks++;
    }

    public void addPitcherHit() {
        pitcherHits++;
    }

    public void addPitcherWalk() {
        pitcherWalks++;
    }

    public void addPlateAppearance() {
        plateAppearances++;
    }

    public void addSacrifice() {
        sacrifices++;
    }

    // combiners
    public void addDoublesAllowed(int doublesAllowed) {
        this.doublesAllowed += doublesAllowed;
    }

    public void addGamesFinished(int gamesFinished) {
        this.gamesFinished += gamesFinished;
    }

    public void addSinglesAllowed(int singlesAllowed) {
        this.singlesAllowed += singlesAllowed;
    }

    public void addStrikeouts(int strikeouts) {
        this.strikeouts += strikeouts;
    }

    public void addTriplesAllowed(int triplesAllowed) {
        this.triplesAllowed += triplesAllowed;
    }
    public void addHomeRunsAllowed(int homeRunsAllowed) {
        this.homeRunsAllowed += homeRunsAllowed;
    }

    public void addCaughtStealing(int caughtStealing) {
        this.caughtStealing += caughtStealing;
    }
    public void addSaveOpportunities(int saveOpportunities) {
        this.saveOpportunities = saveOpportunities;
    }

    public void addHolds(int holds) {
        this.holds = holds;
    }
    public void addCompleteGames(int completeGames) {
        this.completeGames += completeGames;
    }
    public void addWins(int wins) {
        this.wins += wins;
    }

    public void addLosses(int losses) {
        this.losses += losses;
    }
    public void addGamesPitched(int gamesPitched) {
        this.gamesPitched += gamesPitched;
    }

    public void addGamesPlayed(int gamesPlayed) {
        this.gamesPlayed += gamesPlayed;
    }
    public void addAtBats(int atBats) {
        this.atBats += atBats;
    }

    public void addDoubles(int doubles) {
        this.doubles += doubles;
    }

    public void addGamesStarted(int gamesStarted) {
        this.gamesStarted += gamesStarted;
    }

    public void addHits(int hits) {
        this.hits += hits;
    }

    public void addHomeRuns(int homeRuns) {
        this.homeRuns += homeRuns;
    }

    public void addInningsPitched(double inningsPitched) {
        this.inningsPitched += inningsPitched;
        if (this.inningsPitched % 1 >= 0.3) {
            this.inningsPitched += 0.7;
        }
    }

    public void addNoHitters(int noHitters) {
        this.noHitters += noHitters;
    }

    public void addPerfectGames(int perfectGames) {
        this.perfectGames += perfectGames;
    }

    public void addRuns(int runs) {
        this.runs += runs;
    }

    public void addRbis(int rbis) {
        this.rbis += rbis;
    }

    public void addSaves(int saves) {
        this.saves += saves;
    }

    public void addShutOuts(int shutOuts) {
        this.shutOuts += shutOuts;
    }

    public void addSingles(int singles) {
        this.singles += singles;
    }

    public void addSteals(int steals) {
        this.steals += steals;
    }

    public void addStrikeOuts(int strikeOuts) {
        this.strikeOuts += strikeOuts;
    }

    public void addTriples(int triples) {
        this.triples += triples;
    }

    public void addEarnedRuns(int earnedRuns) {
        this.earnedRuns += earnedRuns;
    }

    public void addErrors(int errors) {
        this.errors += errors;
    }

    public void addHitterWalks(int hitterWalks) {
        this.hitterWalks += hitterWalks;
    }

    public void addPitcherHits(int pitcherHits) {
        this.pitcherHits += pitcherHits;
    }

    public void addPitcherWalks(int pitcherWalks) {
        this.pitcherWalks += pitcherWalks;
    }

    public void addPlateAppearances(int plateAppearances) {
        this.plateAppearances += plateAppearances;
    }

    public void addSacrifices(int sacrifices) {
        this.sacrifices += sacrifices;
    }
}
