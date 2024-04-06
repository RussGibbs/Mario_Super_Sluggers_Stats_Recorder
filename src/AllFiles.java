import java.io.Serializable;
import java.util.ArrayList;


// class AllFiles is the highest level aggregate of data classes
public class AllFiles implements Serializable {
    // contains all seasons
    private final ArrayList<Season> seasons;

    // constructors
    public AllFiles() {
        seasons = new ArrayList<>();
    }

    // setter methods
    public void addSeason() {
        seasons.add(new Season());
    }

    // getter methods
    public ArrayList<Season> getSeasons() {
        return seasons;
    }
    public Season getSeason(int seasonIndex) {
        return seasons.get(seasonIndex);
    }
}
