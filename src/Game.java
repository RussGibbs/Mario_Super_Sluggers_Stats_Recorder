import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    // attributes
    private ArrayList<Team> teams;
    private boolean gameResult;
    private String stadium;
    private String timeOfDay;
    private Player[] startingPitcher;
    private ArrayList<ArrayList<Player>> lineups;
    private boolean pitchesFirst;

    private boolean hasStarted;
    private boolean hasFinished;
    private int inning;
    private int outs;
    private int[] score;
    private Player[] bases;
    private Player[] earnedBases;

    private Player[] currentBatter;
    private Player[] currentPitcher;
    private int[] currentBatterIndex;
    private int[] currentPitcherIndex;
    private int currentTeamPitchingIndex;

    private boolean[] hasErrors;

    private Stats[][] gameStats;

    // constructors
    public Game() {
        teams = new ArrayList<Team>();
        teams.add(new Team());
        teams.add(new Team());
        gameResult = true;
        stadium = "Mario Stadium";
        timeOfDay = "Day";
        startingPitcher = new Player[2];
        lineups = new ArrayList<ArrayList<Player>>();
        lineups.add(new ArrayList<Player>());
        lineups.add(new ArrayList<Player>());
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                lineups.get(i).add(null);
            }
        }

        hasStarted = false;
        hasFinished = false;
        pitchesFirst = true;
        inning = 1;
        outs = 0;
        score = new int[2];
        bases = new Player[3];
        earnedBases = new Player[3];
        currentBatter = new Player[2];
        currentPitcher = new Player[2];
        currentBatterIndex = new int[2];
        currentPitcherIndex = new int[2];
        currentTeamPitchingIndex = 0;

        hasErrors = new boolean[2];

        gameStats = new Stats[2][9];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                gameStats[i][j] = new Stats();
            }
        }
    }

    // remember to check if the game is over
    public void startGame() {
        hasStarted = true;
        // set to the current pitching team
        if (!pitchesFirst) {
            currentTeamPitchingIndex = 1;
        }

        // give every player a gamePlayed
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                gameStats[i][j].addGamePlayed();
            }
        }

        // initialize starting pitchers
        placePitcher(0, startingPitcher[0]);
        placePitcher(1, startingPitcher[1]);

        for (int i = 0; i < 2; i++) {
            int[] playerIndex = new int[2];

            loop:
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 9; k++) {
                    if (lineups.get(j).get(k) == (startingPitcher[i])) {
                        playerIndex[0] = j;
                        playerIndex[1] = k;
                        break loop;
                    }
                }
            }

            gameStats[playerIndex[0]][playerIndex[1]].addGameStarted();
        }

        // initialize starting batters
        currentBatter[0] = lineups.get(0).get(0);
        currentBatterIndex[0] = 0;
        gameStats[0][0].addPlateAppearance();
        currentBatter[1] = lineups.get(1).get(0);
        currentBatterIndex[1] = 0;
        gameStats[1][0].addPlateAppearance();
    }

    public void placePitcher(int teamIndex, Player player) {
        currentPitcher[teamIndex] = player;
        currentPitcherIndex[teamIndex] = lineups.get(teamIndex).indexOf(player);
        gameStats[teamIndex][8].addGamePitched();
    }

    public void nextBatter(int teamIndex) {
        gameStats[teamIndex][currentBatterIndex[teamIndex]].addAtBat();
        if (currentBatterIndex[teamIndex] == 8) {
            currentBatterIndex[teamIndex] = 0;
        } else {
            currentBatterIndex[teamIndex]++;
        }
        currentBatter[teamIndex] = lineups.get(teamIndex).get(currentBatterIndex[teamIndex]);
        gameStats[teamIndex][currentBatterIndex[teamIndex]].addPlateAppearance();
    }

    // at Bat events
    /*
    0 = out
    1 = single
    2 = double
    3 = triple
    4 = home run
     */
    public void hitAdvance(int amount) {
        // give the current batter the corresponding hit
        switch (amount) {
            case 0 -> {
                outs++;
                gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addInningPitched();
                nextBatter(currentTeamPitchingIndex == 1 ? 0 : 1);
                if (outs == 3) {
                    inning++;
                    outs = 0;
                    if (inning >= 19 && (score[0] != score[1])) {
                        finishGame();
                    }
                    else {
                        if (currentTeamPitchingIndex == 1) {
                            currentTeamPitchingIndex = 0;
                        } else currentTeamPitchingIndex = 1;

                        clearBases();
                    }
                }
            }

            case 1 -> {
                // give the hitter a single
                gameStats[currentTeamPitchingIndex == 1 ? 0 : 1][currentBatterIndex[currentTeamPitchingIndex == 1 ? 0 : 1]].addSingle();

                // give the pitcher a hit
                gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addPitcherHit();

                // update the bases
                Player[] tempBases = new Player[3];
                tempBases[0] = bases[0];
                tempBases[1] = bases[1];
                tempBases[2] = bases[2];

                Player[] tempEarnedBases = new Player[3];
                tempEarnedBases[0] = earnedBases[0];
                tempEarnedBases[1] = earnedBases[1];
                tempEarnedBases[2] = earnedBases[2];

                bases[0] = currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0];
                earnedBases[0] = currentPitcher[currentTeamPitchingIndex];
                bases[1] = tempBases[0];
                earnedBases[1] = tempEarnedBases[0];
                bases[2] = tempBases[1];
                earnedBases[2] = tempEarnedBases[1];

                if (tempBases[2] != null) {
                    score(tempBases[2], tempEarnedBases[2], currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);
                }

                nextBatter(currentTeamPitchingIndex == 0 ? 1 : 0);
            }

            case 2 -> {
                // give the hitter a double
                gameStats[currentTeamPitchingIndex == 1 ? 0 : 1][currentBatterIndex[currentTeamPitchingIndex == 1 ? 0 : 1]].addDouble();

                // give the pitcher a hit
                gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addPitcherHit();

                // update the bases
                Player[] tempBases = new Player[3];
                tempBases[0] = bases[0];
                tempBases[1] = bases[1];
                tempBases[2] = bases[2];

                Player[] tempEarnedBases = new Player[3];
                tempEarnedBases[0] = earnedBases[0];
                tempEarnedBases[1] = earnedBases[1];
                tempEarnedBases[2] = earnedBases[2];

                bases[0] = null;
                earnedBases[0] = null;
                bases[1] = currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0];
                earnedBases[1] = currentPitcher[currentTeamPitchingIndex];
                bases[2] = tempBases[0];
                earnedBases[2] = tempEarnedBases[0];

                if (tempBases[1] != null) {
                    score(tempBases[1], tempEarnedBases[1], currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);
                }
                if (tempBases[2] != null) {
                    score(tempBases[2], tempEarnedBases[2], currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);
                }

                nextBatter(currentTeamPitchingIndex == 0 ? 1 : 0);
            }

            case 3 -> {
                // give the hitter a triple
                gameStats[currentTeamPitchingIndex == 1 ? 0 : 1][currentBatterIndex[currentTeamPitchingIndex == 1 ? 0 : 1]].addTriple();

                // give the pitcher a hit
                gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addPitcherHit();

                // update the bases
                advance(-1, 3, currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);
                /*Player[] tempBases = new Player[3];
                tempBases[0] = bases[0];
                tempBases[1] = bases[1];
                tempBases[2] = bases[2];

                Player[] tempEarnedBases = new Player[3];
                tempEarnedBases[0] = earnedBases[0];
                tempEarnedBases[1] = earnedBases[1];
                tempEarnedBases[2] = earnedBases[2];

                bases[0] = null;
                earnedBases[0] = null;
                bases[1] = null;
                earnedBases[1] = null;
                bases[2] = currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0];
                earnedBases[2] = currentPitcher[currentTeamPitchingIndex];

                if (tempBases[0] != null) {
                    score(tempBases[0], tempEarnedBases[0], currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);
                }
                if (tempBases[1] != null) {
                    score(tempBases[1], tempEarnedBases[1], currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);
                }
                if (tempBases[2] != null) {
                    score(tempBases[2], tempEarnedBases[2], currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);
                }*/

                nextBatter(currentTeamPitchingIndex == 0 ? 1 : 0);
            }

            case 4 -> {
                // give the hitter a home run
                gameStats[currentTeamPitchingIndex == 1 ? 0 : 1][currentBatterIndex[currentTeamPitchingIndex == 1 ? 0 : 1]].addHomeRun();

                // give the pitcher a hit and a home run allowed
                gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addPitcherHit();
                gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addHomeRunAllowed();

                // advance since as is the same for either runner advance style
                advance(-1, 4, currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);

                nextBatter(currentTeamPitchingIndex == 0 ? 1 : 0);
            }
        }
    }

    public void hit(int amount) {
        switch (amount) {
            case 1 -> {
                // give the hitter a single
                gameStats[currentTeamPitchingIndex == 1 ? 0 : 1][currentBatterIndex[currentTeamPitchingIndex == 1 ? 0 : 1]].addSingle();

                // give the pitcher a hit
                gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addPitcherHit();

                // advance
                advance(-1, 1, currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);

                nextBatter(currentTeamPitchingIndex == 0 ? 1 : 0);
            }

            case 2 -> {
                // give the hitter a double
                gameStats[currentTeamPitchingIndex == 1 ? 0 : 1][currentBatterIndex[currentTeamPitchingIndex == 1 ? 0 : 1]].addDouble();

                // give the pitcher a hit
                gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addPitcherHit();

                // advance
                advance(-1, 2, currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);

                nextBatter(currentTeamPitchingIndex == 0 ? 1 : 0);
            }
        }
    }

    public void strikeOut() {
        // update info
        // update stats
        outs++;
        gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addInningPitched();
        gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addStrikeOut();
        nextBatter(currentTeamPitchingIndex == 1 ? 0 : 1);
        if (outs == 3) {
            inning++;
            outs = 0;
            if (inning >= 19 && (score[0] != score[1])) {
                finishGame();
            }
            else {
                if (currentTeamPitchingIndex == 1) {
                    currentTeamPitchingIndex = 0;
                } else currentTeamPitchingIndex = 1;

                clearBases();
            }
        }
    }

    public void clearBases() {
        bases[0] = null;
        bases[1] = null;
        bases[2] = null;
        earnedBases[0] = null;
        earnedBases[1] = null;
        earnedBases[2] = null;
    }

    public void walk() {
        // give the pitcher and hitter the walks, and remove an at bat
        gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addPitcherWalk();
        gameStats[currentTeamPitchingIndex == 0 ? 1 : 0][currentBatterIndex[currentTeamPitchingIndex == 0 ? 1 : 0]].addHitterWalk();
        gameStats[currentTeamPitchingIndex == 0 ? 1 : 0][currentBatterIndex[currentTeamPitchingIndex == 0 ? 1 : 0]].setAtBats(
                gameStats[currentTeamPitchingIndex == 0 ? 1 : 0][currentBatterIndex[currentTeamPitchingIndex == 0 ? 1 : 0]].getAtBats() - 1);

        // update the bases
        Player[] tempBases = new Player[3];
        tempBases[0] = bases[0];
        tempBases[1] = bases[1];
        tempBases[2] = bases[2];

        Player[] tempEarnedBases = new Player[3];
        tempEarnedBases[0] = earnedBases[0];
        tempEarnedBases[1] = earnedBases[1];
        tempEarnedBases[2] = earnedBases[2];

        bases[0] = currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0];
        earnedBases[0] = currentPitcher[currentTeamPitchingIndex];
        if (tempBases[0] != null) {
            bases[1] = tempBases[0];
            earnedBases[1] = tempEarnedBases[0];
            if (tempBases[1] != null) {
                bases[2] = tempBases[1];
                earnedBases[2] = tempEarnedBases[1];
                if (tempBases[2] != null) {
                    score(tempBases[2], tempEarnedBases[2], currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);
                }
            }
        }

        // advance the batter
        nextBatter(currentTeamPitchingIndex == 0 ? 1 : 0);
    }

    public void score(Player runner, Player pitcher, Player hitter) {
        // add score to the scoreboard
        score[currentTeamPitchingIndex == 0 ? 1 : 0]++;

        // give run to runner
        addRun(runner);

        // give earned run to pitcher
        if (pitcher != null) {
            gameStats[currentTeamPitchingIndex][lineups.get(currentTeamPitchingIndex).indexOf(pitcher)].addEarnedRun();
        }

        // give rbi to hitter
        if (hitter != null) {
            gameStats[currentTeamPitchingIndex == 0 ? 1 : 0][lineups.get(currentTeamPitchingIndex == 0 ? 1 : 0).indexOf(hitter)].addRbi();
        }
    }

    public void addSteal(Player player) {
        int[] playerIndex = new int[2];

        loop:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                if (lineups.get(i).get(j) == player) {
                    playerIndex[0] = i;
                    playerIndex[1] = j;
                    break loop;
                }
            }
        }

        gameStats[playerIndex[0]][playerIndex[1]].addSteal();
    }

    public void addCaughtStealing(Player player) {
        int[] playerIndex = new int[2];

        loop:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                if (lineups.get(i).get(j) == player) {
                    playerIndex[0] = i;
                    playerIndex[1] = j;
                    break loop;
                }
            }
        }

        gameStats[playerIndex[0]][playerIndex[1]].addCaughtStealing();
    }

    public void addRun(Player player) {
        int[] playerIndex = new int[2];

        loop:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                if (lineups.get(i).get(j) == player) {
                    playerIndex[0] = i;
                    playerIndex[1] = j;
                    break loop;
                }
            }
        }

        gameStats[playerIndex[0]][playerIndex[1]].addRun();
    }



    public void addSacrifice(Player player) {
        // remove an at bat
        gameStats[currentTeamPitchingIndex == 0 ? 1 : 0][currentBatterIndex[currentTeamPitchingIndex == 0 ? 1 : 0]].setAtBats(
                gameStats[currentTeamPitchingIndex == 0 ? 1 : 0][currentBatterIndex[currentTeamPitchingIndex == 0 ? 1 : 0]].getAtBats() - 1);
        int[] playerIndex = new int[2];

        loop:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                if (lineups.get(i).get(j) == player) {
                    playerIndex[0] = i;
                    playerIndex[1] = j;
                    break loop;
                }
            }
        }

        gameStats[playerIndex[0]][playerIndex[1]].addSacrifice();
    }

    public void addError(Player player) {
        int[] playerIndex = new int[2];

        loop:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                if (lineups.get(i).get(j) == player) {
                    playerIndex[0] = i;
                    playerIndex[1] = j;
                    break loop;
                }
            }
        }

        gameStats[playerIndex[0]][playerIndex[1]].addError();
    }

    // manually advance a runner
    public void advance(int base, int amount, Player hitter) {
        Player[] tempBases = new Player[3];
        tempBases[0] = bases[0];
        tempBases[1] = bases[1];
        tempBases[2] = bases[2];

        Player[] tempEarnedBases = new Player[3];
        tempEarnedBases[0] = earnedBases[0];
        tempEarnedBases[1] = earnedBases[1];
        tempEarnedBases[2] = earnedBases[2];

        if (base == -1) {
            // update the bases
            if (amount == 1) {
                bases[0] = currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0];
                earnedBases[0] = currentPitcher[currentTeamPitchingIndex];
                if (tempBases[0] != null) {
                    bases[1] = tempBases[0];
                    earnedBases[1] = tempEarnedBases[0];
                    if (tempBases[1] != null) {
                        bases[2] = tempBases[1];
                        earnedBases[2] = tempEarnedBases[1];
                        if (tempBases[2] != null) {
                            score(tempBases[2], tempEarnedBases[2], currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0]);
                        }
                    }
                }
            }
            else if (amount == 2) {
                bases[0] = null;
                earnedBases[0] = null;
                bases[1] = currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0];
                earnedBases[1] = currentPitcher[currentTeamPitchingIndex];
                if (tempBases[0] != null) {
                    bases[2] = tempBases[0];
                    earnedBases[2] = tempEarnedBases[0];
                    if (tempBases[1] != null) {
                        score(tempBases[1], tempEarnedBases[1], hitter);
                    }
                    if (tempBases[2] != null) {
                        score(tempBases[2], tempEarnedBases[2], hitter);
                    }
                }
                else {
                    if (tempBases[1] != null) {
                        bases[2] = tempBases[1];
                        earnedBases[2] = tempEarnedBases[1];
                        if (tempBases[2] != null) {
                            score(tempBases[2], tempEarnedBases[2], hitter);
                        }
                    }
                }
            }
            else if (amount == 3) {
                bases[0] = null;
                earnedBases[0] = null;
                bases[1] = null;
                earnedBases[1] = null;
                bases[2] = currentBatter[currentTeamPitchingIndex == 0 ? 1 : 0];
                earnedBases[2] = currentPitcher[currentTeamPitchingIndex];
                if (tempBases[0] != null) {
                    score(tempBases[0], tempEarnedBases[0], hitter);
                }
                if (tempBases[1] != null) {
                    score(tempBases[1], tempEarnedBases[1], hitter);
                }
                if (tempBases[2] != null) {
                    score(tempBases[2], tempEarnedBases[2], hitter);
                }
            }
            else {
                clearBases();
                // score runners on base
                if (tempBases[0] != null) {
                    score(tempBases[0], tempEarnedBases[0], hitter);
                }
                if (tempBases[1] != null) {
                    score(tempBases[1], tempEarnedBases[1], hitter);
                }
                if (tempBases[2] != null) {
                    score(tempBases[2], tempEarnedBases[2], hitter);
                }

                // score the hitter
                score(hitter, tempEarnedBases[2], hitter);
            }
        }
        else if (base == 0) {
            if (amount == 1) {
                bases[0] = null;
                earnedBases[0] = null;
                bases[1] = tempBases[0];
                earnedBases[1] = tempEarnedBases[0];
                if (tempBases[1] != null) {
                    bases[2] = tempBases[1];
                    earnedBases[2] = tempEarnedBases[1];
                    if (tempBases[2] != null) {
                        score(tempBases[2], tempEarnedBases[2], hitter);
                    }
                }
            }
            else if (amount == 2) {
                bases[0] = null;
                earnedBases[0] = null;
                bases[1] = null;
                earnedBases[1] = null;

                bases[2] = tempBases[0];
                earnedBases[2] = tempEarnedBases[0];
                if (tempBases[1] != null) {
                    score(tempBases[1], tempEarnedBases[1], hitter);
                }
                if (tempBases[2] != null) {
                    score(tempBases[2], tempEarnedBases[2], hitter);
                }
            }
            else {
                bases[0] = null;
                earnedBases[0] = null;
                bases[1] = null;
                earnedBases[1] = null;
                bases[2] = null;
                earnedBases[2] = null;

                if (tempBases[0] != null) {
                    score(tempBases[0], tempEarnedBases[0], hitter);
                }
                if (tempBases[1] != null) {
                    score(tempBases[1], tempEarnedBases[1], hitter);
                }
                if (tempBases[2] != null) {
                    score(tempBases[2], tempEarnedBases[2], hitter);
                }
            }
        }
        else if (base == 1) {
            if (amount == 1) {
                bases[1] = null;
                earnedBases[1] = null;
                bases[2] = tempBases[1];
                earnedBases[2] = tempEarnedBases[1];
                if (tempBases[2] != null) {
                    score(tempBases[2], tempEarnedBases[2], hitter);
                }
            }
            else if (amount >= 2) {
                bases[1] = null;
                earnedBases[1] = null;
                bases[2] = null;
                earnedBases[2] = null;
                if (tempBases[1] != null) {
                    score(tempBases[1], tempEarnedBases[1], hitter);
                }
                if (tempBases[2] != null) {
                    score(tempBases[2], tempEarnedBases[2], hitter);
                }
            }
        }
        else {
            if (amount >= 1) {
                bases[2] = null;
                earnedBases[2] = null;

                score(tempBases[2], tempEarnedBases[2], hitter);
            }
        }
    }

    // removes a runner
    public void remove(int base) {
        bases[base] = null;
        earnedBases[base] = null;

        outs++;
        gameStats[currentTeamPitchingIndex][currentPitcherIndex[currentTeamPitchingIndex]].addInningPitched();
        if (outs == 3) {
            inning++;
            outs = 0;
            if (inning >= 19 && (score[0] != score[1])) {
                finishGame();
            }
            else {
                if (currentTeamPitchingIndex == 1) {
                    currentTeamPitchingIndex = 0;
                } else currentTeamPitchingIndex = 1;

                clearBases();
            }
        }
    }

    public void awardSave(Player player) {
        loop:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                if (lineups.get(i).get(j) == player) {
                    gameStats[i][j].addSave();
                    break loop;
                }
            }
        }
    }

    public void awardWin(Player player) {
        loop:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                if (lineups.get(i).get(j) == player) {
                    gameStats[i][j].addWin();
                    break loop;
                }
            }
        }
    }

    public void awardLoss(Player player) {
        loop:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                if (lineups.get(i).get(j) == player) {
                    gameStats[i][j].addLoss();
                    break loop;
                }
            }
        }
    }


    // do saves and wins / losses
    public void finishGame() {
        hasFinished = true;
        inning--; // in order to make the innings not overflow
        gameStats[currentTeamPitchingIndex == 0 ? 1 : 0][currentBatterIndex[currentTeamPitchingIndex == 0 ? 1 : 0]].setPlateAppearances(
                gameStats[currentTeamPitchingIndex == 0 ? 1 : 0][currentBatterIndex[currentTeamPitchingIndex == 0 ? 1 : 0]].getPlateAppearances() - 1);

        // set who won
        if (score[1] > score[0]) {
            gameResult = false;
        }

        // set all pitching awards except saves and win/losses
        for (int i = 0; i < 2; i++) {
            // complete game
            if (gameStats[i][currentPitcherIndex[i]].getInningsPitched() == (double) inning / 2) {
                gameStats[i][currentPitcherIndex[i]].addCompleteGame();
            }

            // shutouts
            if (gameStats[i][currentPitcherIndex[i]].getCompleteGames() == 1 && score[i == 0 ? 1 : 0] == 0) {
                gameStats[i][currentPitcherIndex[i]].addShutOut();
            }

            // no hitters
            if (gameStats[i][currentPitcherIndex[i]].getCompleteGames() == 1 && gameStats[i][currentPitcherIndex[i]].getPitcherHits() == 0) {
                gameStats[i][currentPitcherIndex[i]].addNoHitter();
            }

            // perfect games
            if (score[i == 0 ? 1 : 0] == 0) {
                gameStats[i][currentPitcherIndex[i]].addShutOut();
            }
        }

        // update individual Player stats after the game is finished from stats[][]
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                lineups.get(i).get(j).combineStats(gameStats[i][j]);
            }
        }
    }

    // setter methods

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public void addTeam() {
        teams.add(new Team());
    }
    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
    public void setTeam(int teamIndex, Team team) {
        teams.set(teamIndex, team);
    }
    public void setGameResult(boolean gameResult) {
        this.gameResult = gameResult;
    }
    public void setStadium(String stadium) {
        this.stadium = stadium;
    }
    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
    public void setStartingPitcher(int teamIndex, Player player) {
        startingPitcher[teamIndex] = player;
    }

    public void setLineups(ArrayList<ArrayList<Player>> lineups) {
        this.lineups = lineups;
    }
    public void setPlayerToLineup(Player player, int teamIndex, int playerIndex) {
        lineups.get(teamIndex).set(playerIndex, player);
    }

    public void setPitchesFirst(boolean bool) {
        pitchesFirst = bool;
    }

    public void setBases(Player[] bases) {
        this.bases = bases;
    }

    public void setBase(int baseIndex, Player player) {
        this.bases[baseIndex] = player;
    }

    public void setCurrentBatter(Player currentBatter, int teamIndex) {
        this.currentBatter[teamIndex] = currentBatter;
    }

    public void setCurrentBatterIndex(int[] currentBatterIndex) {
        this.currentBatterIndex = currentBatterIndex;
    }

    public void setCurrentPitcher(Player currentPitcher, int teamIndex) {
        this.currentPitcher[teamIndex] = currentPitcher;
    }

    public void setGameStats(Stats[][] gameStats) {
        this.gameStats = gameStats;
    }

    public void setCurrentPitcherIndex(int[] currentPitcherIndex) {
        this.currentPitcherIndex = currentPitcherIndex;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public void setInning(int inning) {
        this.inning = inning;
    }

    public void setOuts(int outs) {
        this.outs = outs;
    }

    public void setScore(int[] score) {
        this.score = score;
    }

    public void setStartingPitcher(Player[] startingPitcher) {
        this.startingPitcher = startingPitcher;
    }

    public void setCurrentPitcher(Player[] currentPitcher) {
        this.currentPitcher = currentPitcher;
    }

    public void setCurrentBatter(Player[] currentBatter) {
        this.currentBatter = currentBatter;
    }

    public void setCurrentTeamPitchingIndex(int currentTeamPitchingIndex) {
        this.currentTeamPitchingIndex = currentTeamPitchingIndex;
    }

    public void setHasErrors(boolean[] hasErrors) {
        this.hasErrors = hasErrors;
    }

    public void setHasError(int teamIndex, boolean bool) {
        this.hasErrors[teamIndex] = bool;
    }

    public void setEarnedBases(Player[] earnedBases) {
        this.earnedBases = earnedBases;
    }

    public void setEarnedBase(int baseIndex, Player player) {
        this.earnedBases[baseIndex] = player;
    }

    // getter methods

    public boolean isHasStarted() {
        return hasStarted;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }
    public Team getTeam(int teamIndex) {
        return teams.get(teamIndex);
    }
    public String getStadium() {
        return stadium;
    }
    public String getTimeOfDay() {
        return timeOfDay;
    }
    public Player getStartingPitcher(int teamIndex) {
        return startingPitcher[teamIndex];
    }
    public ArrayList<ArrayList<Player>> getLineups() {
        return lineups;
    }

    public Player getPlayerFromLineup(int teamIndex, int lineupIndex) {
        return lineups.get(teamIndex).get(lineupIndex);
    }

    public boolean isPitchesFirst() {
        return pitchesFirst;
    }

    public Stats[][] getGameStats() {
        return gameStats;
    }

    public boolean isGameResult() {
        return gameResult;
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public int getInning() {
        return inning;
    }

    public int getOuts() {
        return outs;
    }

    public int[] getCurrentBatterIndex() {
        return currentBatterIndex;
    }

    public int[] getCurrentPitcherIndex() {
        return currentPitcherIndex;
    }

    public int[] getScore() {
        return score;
    }

    public Player[] getCurrentBatter() {
        return currentBatter;
    }

    public Player[] getCurrentPitcher() {
        return currentPitcher;
    }

    public Player getCurrentBatter(int teamIndex) {
        return currentBatter[teamIndex];
    }

    public Player getCurrentPitcher(int teamIndex) {
        return currentPitcher[teamIndex];
    }

    public Player[] getBases() {
        return bases;
    }

    public Player[] getStartingPitcher() {
        return startingPitcher;
    }

    public int getCurrentTeamPitchingIndex() {
        return currentTeamPitchingIndex;
    }

    public boolean[] getHasErrors() {
        return hasErrors;
    }

    public Player[] getEarnedBases() {
        return earnedBases;
    }

    // Team-specific setter methods
    public void setPlayers(int teamIndex, ArrayList<Player> players) {
        Team team = teams.get(teamIndex);
        team.setPlayers(players);
        teams.set(teamIndex, team);
    }
    public void setPlayer(int teamIndex, int playerIndex, Player player) {
        Team team = teams.get(teamIndex);
        team.setPlayer(playerIndex, player);
        teams.set(teamIndex, team);
    }

    // Player-specific setter methods
    public void setPlayerName(int teamIndex, int playerIndex, String playerName) {
        Team team = teams.get(teamIndex);
        team.setPlayerName(playerIndex, playerName);
        teams.set(teamIndex, team);
    }
    public void setDefensePosition(int teamIndex, int playerIndex, String defensePosition) {
        Team team = teams.get(teamIndex);
        team.setDefensePosition(playerIndex, defensePosition);
        teams.set(teamIndex, team);
    }

    // Team-specific getter methods
    public ArrayList<Player> getPlayers(int teamIndex) {
        return teams.get(teamIndex).getPlayers();
    }
    public Player getPlayer(int teamIndex, int playerIndex) {
        return teams.get(teamIndex).getPlayer(playerIndex);
    }

    // Player-specific getter methods
    public String getPlayerName(int teamIndex, int playerIndex) {
        return teams.get(teamIndex).getPlayerName(playerIndex);
    }
    public String getDefensePosition(int teamIndex, int playerIndex) {
        return teams.get(teamIndex).getDefensePosition(playerIndex);
    }
}

