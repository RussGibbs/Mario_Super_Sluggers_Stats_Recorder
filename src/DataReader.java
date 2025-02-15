import java.io.*;


// class DataReader is the required script to view the saved data from a given season
public class DataReader {
    // method to run which is the launch point to create "seasonData.csv"
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AllFiles allFiles = AppFrame.loadFromFile("seasons.xml");
        seasonData(allFiles.getSeason(0));
    }

    // contains required code to create "seasonData.csv"
    public static void seasonData(Season season) {
        File file = new File("seasonData.csv");
        try {
            if (!file.delete()) {
                System.out.println("File not deleted. File does not yet exist");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String filename = "seasonData.csv";

        PrintWriter printWriter;
        try {
            FileWriter fileWriter = new FileWriter(filename, true); // Set true for append mode
            printWriter = new PrintWriter(fileWriter);
            printWriter.write("Player Name");
            printWriter.write(",");
            printWriter.write("Position");
            printWriter.write(",");
            printWriter.write("Pitching");
            printWriter.write(",");
            printWriter.write("");
            printWriter.write(",");
            printWriter.write("G");
            printWriter.write(",");
            printWriter.write("PA");
            printWriter.write(",");
            printWriter.write("AB");
            printWriter.write(",");
            printWriter.write("R");
            printWriter.write(",");
            printWriter.write("H");
            printWriter.write(",");
            printWriter.write("2B");
            printWriter.write(",");
            printWriter.write("3B");
            printWriter.write(",");
            printWriter.write("HR");
            printWriter.write(",");
            printWriter.write("RBI");
            printWriter.write(",");
            printWriter.write("SB");
            printWriter.write(",");
            printWriter.write("CS");
            printWriter.write(",");
            printWriter.write("BB");
            printWriter.write(",");
            printWriter.write("SO");
            printWriter.write(",");
            printWriter.write("E");
            printWriter.write(",");
            printWriter.write("");
            printWriter.write(",");
            printWriter.write("W");
            printWriter.write(",");
            printWriter.write("L");
            printWriter.write(",");
            printWriter.write("G");
            printWriter.write(",");
            printWriter.write("GS");
            printWriter.write(",");
            printWriter.write("GF");
            printWriter.write(",");
            printWriter.write("CG");
            printWriter.write(",");
            printWriter.write("SHO");
            printWriter.write(",");
            printWriter.write("SV");
            printWriter.write(",");
            printWriter.write("SVO");
            printWriter.write(",");
            printWriter.write("IP");
            printWriter.write(",");
            printWriter.write("H");
            printWriter.write(",");
            printWriter.write("ER");
            printWriter.write(",");
            printWriter.write("HR");
            printWriter.write(",");
            printWriter.write("BB");
            printWriter.write(",");
            printWriter.write("SO");
            printWriter.write(",");
            printWriter.write("NH");
            printWriter.write(",");
            printWriter.write("\n");
            for (int i = 0; i < season.getRoster(0).getPlayers().size(); i++) {
                printWriter.write(season.getRoster(0).getPlayer(i).getName());
                printWriter.write(",");
                printWriter.write(season.getRoster(0).getPlayer(i).getDefensePosition());
                printWriter.write(",");
                printWriter.write(season.getRoster(0).getPlayer(i).getPitch());
                printWriter.write(",");
                printWriter.write("");
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getGamesPlayed()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getPlateAppearances()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getAtBats()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getRuns()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getHits()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getDoubles()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getTriples()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getHomeRuns()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getRbis()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getSteals()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getCaughtStealing()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getHitterWalks()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getStrikeouts()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getErrors()));
                printWriter.write(",");
                printWriter.write("");
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getWins()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getLosses()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getGamesPitched()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getGamesStarted()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getGamesFinished()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getCompleteGames()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getShutOuts()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getSaves()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getSaveOpportunities()));
                printWriter.write(",");
                printWriter.write(Double.toString(season.getRoster(0).getPlayer(i).getStats().getInningsPitched()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getPitcherHits()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getEarnedRuns()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getHomeRunsAllowed()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getPitcherWalks()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getStrikeOuts()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(0).getPlayer(i).getStats().getNoHitters()));
                printWriter.write(",");
                printWriter.write("\n");
            }
            printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("");
            printWriter.write(",");printWriter.write("\n");
            for (int i = 0; i < season.getRoster(1).getPlayers().size(); i++) {
                printWriter.write(season.getRoster(1).getPlayer(i).getName());
                printWriter.write(",");
                printWriter.write(season.getRoster(1).getPlayer(i).getDefensePosition());
                printWriter.write(",");
                printWriter.write(season.getRoster(1).getPlayer(i).getPitch());
                printWriter.write(",");
                printWriter.write("");
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getGamesPlayed()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getPlateAppearances()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getAtBats()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getRuns()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getHits()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getDoubles()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getTriples()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getHomeRuns()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getRbis()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getSteals()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getCaughtStealing()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getHitterWalks()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getStrikeouts()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getErrors()));
                printWriter.write(",");
                printWriter.write("");
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getWins()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getLosses()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getGamesPitched()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getGamesStarted()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getGamesFinished()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getCompleteGames()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getShutOuts()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getSaves()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getSaveOpportunities()));
                printWriter.write(",");
                printWriter.write(Double.toString(season.getRoster(1).getPlayer(i).getStats().getInningsPitched()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getPitcherHits()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getEarnedRuns()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getHomeRunsAllowed()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getPitcherWalks()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getStrikeOuts()));
                printWriter.write(",");
                printWriter.write(Integer.toString(season.getRoster(1).getPlayer(i).getStats().getNoHitters()));
                printWriter.write(",");
                printWriter.write("\n");
            }
            printWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
