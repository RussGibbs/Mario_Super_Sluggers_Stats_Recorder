import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.io.*;
import java.lang.ClassNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class AppFrame extends JFrame implements ActionListener {
    Season currentSeason;
    Team currentRoster;
    Game currentGame;
    Team currentTeam;
    int currentRosterIndex;
    int currentTeamIndex;

    Color background;
    JPanel mainPanel;
    JButton home;
    JButton save;
    JButton saveEditedRoster;
    JButton save2;
    JButton saveEditedLineup;
    JButton saveGame;
    JButton newSeason;
    ArrayList<JButton> loadSeason;
    JButton deleteSeason;
    JButton deleteSeasonNo;
    JButton deleteSeasonYes;
    JButton editRoster1;
    JButton editRoster2;
    JTextField nameField;
    JTextField positionField;
    JTextField pitchField;
    JButton addPlayerToRoster;
    JButton resetRoster;
    JButton deleteLastEntry;
    JTable playersTable;
    JButton newGame;
    ArrayList<JButton> loadGame;
    JButton seasonBack;
    JButton deleteGame;
    JButton deleteGameNo;
    JButton deleteGameYes;
    JButton enterGame;
    JRadioButton marioStadium;
    JRadioButton yoshiPark;
    JRadioButton warioCity;
    JRadioButton dkJungle;
    JRadioButton bjPlayroom;
    JRadioButton peachIceGardens;
    JRadioButton bowserCastle;
    JRadioButton luigiMansion;
    JRadioButton daisyCruiser;
    JRadioButton day;
    JRadioButton night;
    JButton editLineup1;
    JButton editLineup2;
    ArrayList<JComboBox<Player>> playerJComboBox;
    JComboBox<Player> pitchJComboBox;
    JCheckBox pitchesFirst;

    JButton strikeOut;
    JButton out;
    JButton singleHit;
    JButton singleHitAdvance;
    JButton doubleHit;
    JButton doubleHitAdvance;
    JButton tripleHit;
    JButton homeRunHit;
    JButton walk;
    JButton changePitcher;
    JButton other;

    JRadioButton firstBase;
    JRadioButton secondBase;
    JRadioButton thirdBase;
    JRadioButton out1;
    JRadioButton out2;

    JButton saveCustomEvent;
    JCheckBox advanceBattingOrder;
    JCheckBox error;
    JComboBox<Player> errorBox;
    JCheckBox hazard;
    JCheckBox steal;
    JComboBox<Player> stealBox;
    JCheckBox sacrifice;
    JSpinner advanceBatter;
    JCheckBox earnedRun1;
    JCheckBox outCheckBox;
    JCheckBox strikeoutCheckBox;
    JSpinner advanceFirst;
    JCheckBox earnedRun2;
    JCheckBox firstBaseOut;
    JCheckBox caughtStealing1;
    JSpinner advanceSecond;
    JCheckBox earnedRun3;
    JCheckBox secondBaseOut;
    JCheckBox caughtStealing2;
    JSpinner advanceThird;
    JCheckBox earnedRun4;
    JCheckBox thirdBaseOut;
    JCheckBox caughtStealing3;
    JCheckBox hitPitcher;
    JCheckBox hitBatter;
    JComboBox<String> hitType;

    ChangeListener batterListener;
    ChangeListener firstListener;
    ChangeListener secondListener;
    ChangeListener thirdListener;


    JButton cancel;
    JButton saveEditedPitcher;
    JComboBox<Player> changePitcherBox;

    // other stuff for manual enter


    // Creates an AllFiles object and loads save data from file if it exists
    AllFiles allFiles = loadFromFile("seasons.xml");

    public AppFrame() throws IOException, ClassNotFoundException {
        setSize(1200, 800);
        background = new Color(0xaacccc);
        loadHome();
    }

    public void loadHome() {
        setTitle("Mario Super Sluggers Stats Recorder");
        mainPanel = new JPanel();
        mainPanel.setBackground(background);
        JPanel[][] cells = new JPanel[2][2];
        mainPanel.setLayout(new GridLayout(2,2));
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                cells[i][j] = new JPanel();
                cells[i][j].setBackground(background);
                mainPanel.add(cells[i][j]);
            }
        }
        cells[1][1].setLayout(new FlowLayout());

        home = new JButton("Home");
        cells[0][0].add(home);
        home.addActionListener(this);

        save = new JButton("Save");
        cells[0][1].add(save);
        save.addActionListener(this);

        newSeason = new JButton("Add New Season");
        cells[1][0].add(newSeason);
        newSeason.addActionListener(this);

        loadSeason = new ArrayList<JButton>();
        for (int i = 0; i < allFiles.getSeasons().size(); i++) {
            JButton jButton = new JButton(("Load Season " + (i + 1)));
            loadSeason.add(jButton);
            cells[1][1].add(jButton);
            jButton.addActionListener(this);
        }

        add(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void loadSeason(Season season, String title) {
        setTitle(title);
        mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(3,1));

        JPanel gamePanel = new JPanel();
        JPanel teamPanel = new JPanel();
        JPanel statsPanel = new JPanel();

        editRoster1 = new JButton("Edit Roster 1");
        editRoster1.addActionListener(this);
        editRoster2 = new JButton("Edit Roster 2");
        editRoster2.addActionListener(this);

        // upper panel
        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(background);
        upperPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(upperPanel);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(background);
        controlPanel.setLayout(new GridLayout(1, 2));
        upperPanel.add(controlPanel);

        home = new JButton("Home");
        JPanel homePanel = new JPanel();
        homePanel.setBackground(background);
        homePanel.setLayout(new FlowLayout());
        controlPanel.add(homePanel);
        deleteSeason = new JButton("Erase");
        deleteSeason.addActionListener(this);
        homePanel.add(deleteSeason);
        homePanel.add(home);
        home.addActionListener(this);


        save = new JButton("Save");
        JPanel savePanel = new JPanel();
        savePanel.setBackground(background);
        controlPanel.add(savePanel);
        savePanel.add(save);
        save.addActionListener(this);

        for (int i = 0; i < 1; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(background);
            upperPanel.add(jPanel);
        }

        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(background);
        labelPanel.setLayout(new FlowLayout());
        upperPanel.add(labelPanel);
        labelPanel.add(new JLabel("Games:"));

        // middle panel
        gamePanel.setBackground(background);
        gamePanel.setLayout(new FlowLayout());
        mainPanel.add(gamePanel);

        loadGame = new ArrayList<JButton>();
        for (int i = 0; i < season.getGames().size(); i++) {
            JButton jButton = new JButton(("Load Game " + (i + 1)));
            loadGame.add(jButton);
            gamePanel.add(jButton);
            jButton.addActionListener(this);
        }
        newGame = new JButton("+");
        newGame.addActionListener(this);
        gamePanel.add(newGame);

        // lower panel
        JPanel lowerPanel = new JPanel();
        lowerPanel.setBackground(background);
        lowerPanel.setLayout(new GridLayout(1,2));
        mainPanel.add(lowerPanel);

        teamPanel.setBackground(background);
        teamPanel.setLayout(new GridLayout(1,2));
        teamPanel.add(editRoster1);
        teamPanel.add(editRoster2);
        lowerPanel.add(teamPanel);

        // add the stuff for stats
        statsPanel.setBackground(background);
        lowerPanel.add(statsPanel);

        add(mainPanel);
        setVisible(true);
    }
    public void loadDeleteSeason(Season season, String seasonTitle) {
        setTitle("Erase Season " + seasonTitle);
        mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(6,1));
        for (int i = 0; i < 2; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(background);
            mainPanel.add(jPanel);
        }
        JPanel question = new JPanel();
        question.setBackground(background);
        question.setLayout(new FlowLayout());
        mainPanel.add(question);
        question.add(new JLabel("Are you sure you would like to erase Season " + seasonTitle + "?"));

        JPanel responses = new JPanel();
        responses.setBackground(background);
        responses.setLayout(new FlowLayout());
        mainPanel.add(responses);
        deleteSeasonNo = new JButton("Cancel");
        deleteSeasonNo.addActionListener(this);
        responses.add(deleteSeasonNo);
        deleteSeasonYes = new JButton("Erase");
        deleteSeasonYes.addActionListener(this);
        responses.add(deleteSeasonYes);

        add(mainPanel);
        setVisible(true);
    }
    public void loadEditRoster(Team team, String title) {
        setTitle(title);
        mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(2,1));

        JPanel topHalf = new JPanel();
        topHalf.setBackground(background);
        topHalf.setLayout(new GridLayout(2,1));
        mainPanel.add(topHalf);

        // upper panel
        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(background);
        upperPanel.setLayout(new GridLayout(1,2));
        topHalf.add(upperPanel);

        home = new JButton("Home");
        JPanel homePanel = new JPanel();
        homePanel.setBackground(background);
        upperPanel.add(homePanel);
        homePanel.add(home);
        home.addActionListener(this);

        save = new JButton("Save");
        saveEditedRoster = new JButton("Save and Exit");
        JPanel savePanel = new JPanel();
        savePanel.setBackground(background);
        savePanel.setLayout(new FlowLayout());
        upperPanel.add(savePanel);
        savePanel.add(save);
        savePanel.add(saveEditedRoster);
        save.addActionListener(this);
        saveEditedRoster.addActionListener(this);

        // middle panel
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(background);
        middlePanel.setLayout(new GridLayout(2,1));
        topHalf.add(middlePanel);

        JPanel queryPanel = new JPanel();
        queryPanel.setBackground(background);
        queryPanel.setLayout(new GridLayout(1,3));
        middlePanel.add(queryPanel);

        JPanel nameQueryPanel = new JPanel();
        nameQueryPanel.setBackground(background);
        nameQueryPanel.setLayout(new GridLayout(2, 1));
        queryPanel.add(nameQueryPanel);
        nameQueryPanel.add(new JLabel("Player Name: "));
        nameField = new JTextField(10);
        nameQueryPanel.add(nameField);

        JPanel positionQueryPanel = new JPanel();
        positionQueryPanel.setBackground(background);
        positionQueryPanel.setLayout(new GridLayout(2, 1));
        queryPanel.add(positionQueryPanel);
        positionQueryPanel.add(new JLabel("Position: "));
        positionField = new JTextField(4);
        positionQueryPanel.add(positionField);

        JPanel pitchQueryPanel = new JPanel();
        pitchQueryPanel.setBackground(background);
        pitchQueryPanel.setLayout(new GridLayout(2, 1));
        queryPanel.add(pitchQueryPanel);
        pitchQueryPanel.add(new JLabel("Pitch: "));
        pitchField = new JTextField(4);
        pitchQueryPanel.add(pitchField);

        JPanel setPanel = new JPanel();
        setPanel.setBackground(background);
        setPanel.setLayout(new GridLayout(1, 3));
        middlePanel.add(setPanel);
        addPlayerToRoster = new JButton("Add Player");
        addPlayerToRoster.addActionListener(this);
        resetRoster = new JButton("Reset Roster");
        resetRoster.addActionListener(this);
        deleteLastEntry = new JButton("Delete Last Entry");
        deleteLastEntry.addActionListener(this);
        setPanel.add(addPlayerToRoster);
        setPanel.add(resetRoster);
        setPanel.add(deleteLastEntry);

        // lower panel
        JPanel tablePanel = new JPanel();
        tablePanel.setBackground(background);
        tablePanel.setLayout(new FlowLayout());
        mainPanel.add(tablePanel);

        PlayerTableModel model = new PlayerTableModel(currentRoster.getPlayers());
        playersTable = new JTable(model);
        tablePanel.add(new JScrollPane(playersTable));

        add(mainPanel);
        setVisible(true);
    }
    public void loadGame(Game game, String title) {
        setTitle(title);
        mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(3,1));

        // upper control bar
        JPanel upper = new JPanel();
        upper.setBackground(background);
        upper.setLayout(new GridLayout(1,2));
        mainPanel.add(upper);

        JPanel back = new JPanel();
        back.setBackground(background);
        back.setLayout(new FlowLayout());
        upper.add(back);
        deleteGame = new JButton("Erase");
        deleteGame.addActionListener(this);
        back.add(deleteGame);
        seasonBack = new JButton("Back to Season");
        seasonBack.addActionListener(this);
        back.add(seasonBack);
        home = new JButton("Home");
        home.addActionListener(this);
        back.add(home);

        JPanel savePanel = new JPanel();
        savePanel.setBackground(background);
        savePanel.setLayout(new FlowLayout());
        upper.add(savePanel);
        save = new JButton("Save");
        save.addActionListener(this);
        savePanel.add(save);

        // middle, enter game button
        JPanel gameEnterPanel = new JPanel();
        gameEnterPanel.setBackground(background);
        gameEnterPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(gameEnterPanel);

        enterGame = new JButton("Enter Game");
        enterGame.addActionListener(this);
        gameEnterPanel.add(enterGame);

        JPanel dayNight = new JPanel();
        dayNight.setBackground(background);
        dayNight.setLayout(new FlowLayout());
        gameEnterPanel.add(dayNight);
        ButtonGroup timeOfDay = new ButtonGroup();
        day = new JRadioButton("Day");
        day.addActionListener(this);
        timeOfDay.add(day);
        dayNight.add(day);
        night = new JRadioButton("Night");
        night.addActionListener(this);
        timeOfDay.add(night);
        dayNight.add(night);

        JPanel stadiums = new JPanel();
        stadiums.setBackground(background);
        stadiums.setLayout(new FlowLayout());
        gameEnterPanel.add(stadiums);
        ButtonGroup stadiumSelect = new ButtonGroup();
        marioStadium = new JRadioButton("Mario Stadium");
        marioStadium.addActionListener(this);
        stadiumSelect.add(marioStadium);
        stadiums.add(marioStadium);
        yoshiPark = new JRadioButton("Yoshi Park");
        yoshiPark.addActionListener(this);
        stadiumSelect.add(yoshiPark);
        stadiums.add(yoshiPark);
        warioCity = new JRadioButton("Wario City");
        warioCity.addActionListener(this);
        stadiumSelect.add(warioCity);
        stadiums.add(warioCity);
        dkJungle = new JRadioButton(" DK Jungle");
        dkJungle.addActionListener(this);
        stadiumSelect.add(dkJungle);
        stadiums.add(dkJungle);
        bjPlayroom = new JRadioButton("Bowser Jr Playroom");
        bjPlayroom.addActionListener(this);
        stadiumSelect.add(bjPlayroom);
        stadiums.add(bjPlayroom);
        peachIceGardens = new JRadioButton("Peach Ice Gardens");
        peachIceGardens.addActionListener(this);
        stadiumSelect.add(peachIceGardens);
        stadiums.add(peachIceGardens);
        bowserCastle = new JRadioButton("Bowser Castle");
        bowserCastle.addActionListener(this);
        stadiumSelect.add(bowserCastle);
        stadiums.add(bowserCastle);
        luigiMansion = new JRadioButton("Luigi Mansion");
        luigiMansion.addActionListener(this);
        stadiumSelect.add(luigiMansion);
        stadiums.add(luigiMansion);
        daisyCruiser = new JRadioButton("Daisy Cruiser");
        daisyCruiser.addActionListener(this);
        stadiumSelect.add(daisyCruiser);
        stadiums.add(daisyCruiser);

        if (currentGame.getTimeOfDay().equals("Day")) {
            day.setSelected(true);
            luigiMansion.setEnabled(false);
            bowserCastle.setEnabled(false);
        }
        else {
            night.setSelected(true);
            bjPlayroom.setEnabled(false);
        }

        if (currentGame.getStadium().equals("Mario Stadium")) {
            marioStadium.setSelected(true);
        }
        else if (currentGame.getStadium().equals("Yoshi Park")) {
            yoshiPark.setSelected(true);
        }
        else if (currentGame.getStadium().equals("Wario City")) {
            warioCity.setSelected(true);
        }
        else if (currentGame.getStadium().equals("DK Jungle")) {
            dkJungle.setSelected(true);
        }
        else if (currentGame.getStadium().equals("Bowser Jr Playroom")) {
            bjPlayroom.setSelected(true);
            night.setEnabled(false);
        }
        else if (currentGame.getStadium().equals("Peach Ice Gardens")) {
            peachIceGardens.setSelected(true);
        }
        else if (currentGame.getStadium().equals("Bowser Castle")) {
            bowserCastle.setSelected(true);
            day.setEnabled(false);
        }
        else if (currentGame.getStadium().equals("Luigi Mansion")) {
            luigiMansion.setSelected(true);
            day.setEnabled(false);
        }
        else if (currentGame.getStadium().equals("Daisy Cruiser")) {
            daisyCruiser.setSelected(true);
        }

        // lower, edit lineups 1 and 2 from rosters
        JPanel lineupsPanel = new JPanel();
        lineupsPanel.setBackground(background);
        lineupsPanel.setLayout(new GridLayout(1,2));
        mainPanel.add(lineupsPanel);
        editLineup1 = new JButton("Edit Lineup 1");
        editLineup1.addActionListener(this);
        lineupsPanel.add(editLineup1);
        editLineup2 = new JButton("Edit Lineup 2");
        editLineup2.addActionListener(this);
        lineupsPanel.add(editLineup2);

        add(mainPanel);
        setVisible(true);
    }
    public void loadDeleteGame(Game game, String gameTitle) {
        setTitle("Erase Game " + gameTitle);
        mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(6,1));
        for (int i = 0; i < 2; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(background);
            mainPanel.add(jPanel);
        }
        JPanel question = new JPanel();
        question.setBackground(background);
        question.setLayout(new FlowLayout());
        mainPanel.add(question);
        question.add(new JLabel("Are you sure you would like to erase Game " + gameTitle + "?"));

        JPanel responses = new JPanel();
        responses.setBackground(background);
        responses.setLayout(new FlowLayout());
        mainPanel.add(responses);
        deleteGameNo = new JButton("Cancel");
        deleteGameNo.addActionListener(this);
        responses.add(deleteGameNo);
        deleteGameYes = new JButton("Erase");
        deleteGameYes.addActionListener(this);
        responses.add(deleteGameYes);

        add(mainPanel);
        setVisible(true);
    }
    public void loadEditLineup(Team team, String title) {
        setTitle(title);
        mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(3,1));

        // upper, controls
        JPanel upper = new JPanel();
        upper.setBackground(background);
        upper.setLayout(new GridLayout(2,1));
        mainPanel.add(upper);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(background);
        controlPanel.setLayout(new GridLayout(1,2));
        upper.add(controlPanel);

        JPanel backPanel = new JPanel();
        backPanel.setBackground(background);
        backPanel.setLayout(new FlowLayout());
        controlPanel.add(backPanel);
        home = new JButton("Home");
        home.addActionListener(this);
        backPanel.add(home);

        JPanel savePanel = new JPanel();
        savePanel.setBackground(background);
        savePanel.setLayout(new FlowLayout());
        controlPanel.add(savePanel);
        save2 = new JButton("Save");
        save2.addActionListener(this);
        savePanel.add(save2);
        saveEditedLineup = new JButton("Save and Exit");
        saveEditedLineup.addActionListener(this);
        savePanel.add(saveEditedLineup);

        JPanel pitchesFirstPanel = new JPanel();
        pitchesFirstPanel.setBackground(background);
        pitchesFirstPanel.setLayout(new FlowLayout());
        upper.add(pitchesFirstPanel);

        boolean thisTeamPitchesFirst = currentGame.isPitchesFirst();
        if (currentTeamIndex == 1) {
            thisTeamPitchesFirst = !thisTeamPitchesFirst;
        }

        pitchesFirst = new JCheckBox("Pitches First");
        pitchesFirst.addActionListener(this);
        pitchesFirstPanel.add(pitchesFirst);
        pitchesFirst.setSelected(thisTeamPitchesFirst);

        // middle, jcomboboxes for lineup
        JPanel middle = new JPanel();
        middle.setBackground(background);
        middle.setLayout(new GridLayout(2, 1));
        mainPanel.add(middle);

        JPanel lineupLabel = new JPanel();
        lineupLabel.setBackground(background);
        lineupLabel.setLayout(new FlowLayout());
        middle.add(lineupLabel);
        lineupLabel.add(new JLabel("Lineup Order:"));
        JPanel lineupPanel = new JPanel();
        lineupPanel.setBackground(background);
        lineupPanel.setLayout(new FlowLayout());
        middle.add(lineupPanel);

        playerJComboBox = new ArrayList<JComboBox<Player>>();
        for (int i = 0; i < 9; i++) {
            JComboBox<Player> jComboBox = new JComboBox<Player>();
            for (int j = 0; j < currentRoster.getPlayers().size(); j++) {
                jComboBox.addItem(currentRoster.getPlayer(j));
            }
            jComboBox.setSelectedItem(currentRoster.getPlayer(i));
            playerJComboBox.add(jComboBox);
        }

        for (int i = 0; i < 9; i++) {
            lineupPanel.add(playerJComboBox.get(i));
        }


        for (int i = 0; i < 9; i++) {
            playerJComboBox.get(i).setSelectedItem(currentSeason.getTeam(currentSeason.getGames().indexOf(currentGame), currentRosterIndex));
        }


        // bottom, jcombobox for starting pitcher
        JPanel bottom = new JPanel();
        bottom.setBackground(background);
        bottom.setLayout(new GridLayout(2, 1));
        mainPanel.add(bottom);

        JPanel pitchLabel = new JPanel();
        pitchLabel.setBackground(background);
        pitchLabel.setLayout(new FlowLayout());
        bottom.add(pitchLabel);
        pitchLabel.add(new JLabel("Starting Pitcher:"));
        JPanel pitchPanel = new JPanel();
        pitchPanel.setBackground(background);
        pitchPanel.setLayout(new FlowLayout());
        bottom.add(pitchPanel);

        pitchJComboBox = new JComboBox<Player>();
        for (int i = 0; i < currentRoster.getPlayers().size(); i++) {
            pitchJComboBox.addItem(currentRoster.getPlayer(i));
        }

        pitchPanel.add(pitchJComboBox);

        if (currentGame.getStartingPitcher(currentTeamIndex) != null) {
            pitchJComboBox.setSelectedItem(currentGame.getStartingPitcher(currentTeamIndex));
        }

        add(mainPanel);
        setVisible(true);
    }

    // write enter game
    public void enterGame(Game game, String title) {
        if (!currentGame.isHasStarted()) {
            currentGame.startGame();
        }

        setTitle(title);
        mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(4,1));

        // highest
        JPanel highest = new JPanel();
        highest.setBackground(background);
        highest.setLayout(new GridLayout(1,3));
        mainPanel.add(highest);

        JPanel homePanel = new JPanel();
        homePanel.setBackground(background);
        homePanel.setLayout(new FlowLayout());
        highest.add(homePanel);

        home = new JButton("Home");
        home.addActionListener(this);
        homePanel.add(home);

        for (int i = 0; i < 1; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(background);
            highest.add(jPanel);
        }

        JPanel savePanel = new JPanel();
        savePanel.setBackground(background);
        savePanel.setLayout(new FlowLayout());
        highest.add(savePanel);

        save = new JButton("Save");
        save.addActionListener(this);
        savePanel.add(save);

        saveGame = new JButton("Save and Exit");
        saveGame.addActionListener(this);
        savePanel.add(saveGame);

        // high
        JPanel high = new JPanel();
        high.setBackground(background);
        high.setLayout(new GridLayout(1,5));
        mainPanel.add(high);

        for (int i = 0; i < 1; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(background);
            high.add(jPanel);
        }

        JPanel strikeOutPanel = new JPanel();
        strikeOutPanel.setBackground(background);
        strikeOutPanel.setLayout(new GridLayout(1,1));
        high.add(strikeOutPanel);

        strikeOut = new JButton("Strikeout");
        strikeOut.addActionListener(this);
        strikeOutPanel.add(strikeOut);

        JPanel outPanel = new JPanel();
        outPanel.setBackground(background);
        outPanel.setLayout(new GridLayout(1,1));
        high.add(outPanel);

        out = new JButton("Out");
        out.addActionListener(this);
        outPanel.add(out);

        JPanel otherPanel = new JPanel();
        otherPanel.setBackground(background);
        otherPanel.setLayout(new GridLayout(2,1));
        high.add(otherPanel);

        other = new JButton("Other");
        other.addActionListener(this);
        otherPanel.add(other);

        walk = new JButton("Walk");
        walk.addActionListener(this);
        otherPanel.add(walk);

        JPanel inningsPanel = new JPanel();
        inningsPanel.setBackground(background);
        inningsPanel.setLayout(new GridLayout(2,1));
        high.add(inningsPanel);

        JPanel upperInnings = new JPanel();
        upperInnings.setBackground(background);
        upperInnings.setLayout(new FlowLayout());
        inningsPanel.add(upperInnings);

        JPanel lowerInnings = new JPanel();
        lowerInnings.setBackground(background);
        lowerInnings.setLayout(new FlowLayout());
        inningsPanel.add(lowerInnings);


        firstBase = new JRadioButton();
        firstBase.setEnabled(false);

        secondBase = new JRadioButton();
        secondBase.setEnabled(false);

        thirdBase = new JRadioButton();
        thirdBase.setEnabled(false);

        if (currentGame.getBases()[0] != null) {
            firstBase.setSelected(true);
        }

        if (currentGame.getBases()[1] != null) {
            secondBase.setSelected(true);
        }

        if (currentGame.getBases()[2] != null) {
            thirdBase.setSelected(true);
        }

        upperInnings.add(secondBase);
        lowerInnings.add(thirdBase);
        lowerInnings.add(firstBase);

        // low
        JPanel low = new JPanel();
        low.setBackground(background);
        low.setLayout(new GridLayout(1,5));
        mainPanel.add(low);

        for (int i = 0; i < 1; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(background);
            low.add(jPanel);
        }

        JPanel singlesPanel = new JPanel();
        singlesPanel.setBackground(background);
        singlesPanel.setLayout(new GridLayout(2,1));
        low.add(singlesPanel);

        singleHit = new JButton("Single");
        singleHit.addActionListener(this);
        singlesPanel.add(singleHit);

        singleHitAdvance = new JButton("Single Advance");
        singleHitAdvance.addActionListener(this);
        singlesPanel.add(singleHitAdvance);

        JPanel doublesPanel = new JPanel();
        doublesPanel.setBackground(background);
        doublesPanel.setLayout(new GridLayout(2,1));
        low.add(doublesPanel);

        doubleHit = new JButton("Double");
        doubleHit.addActionListener(this);
        doublesPanel.add(doubleHit);

        doubleHitAdvance = new JButton("Double Advance");
        doubleHitAdvance.addActionListener(this);
        doublesPanel.add(doubleHitAdvance);

        JPanel bigHitPanel = new JPanel();
        bigHitPanel.setBackground(background);
        bigHitPanel.setLayout(new GridLayout(2,1));
        low.add(bigHitPanel);

        tripleHit = new JButton("Triple");
        tripleHit.addActionListener(this);
        bigHitPanel.add(tripleHit);

        homeRunHit = new JButton("Home Run");
        homeRunHit.addActionListener(this);
        bigHitPanel.add(homeRunHit);

        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(background);
        scorePanel.setLayout(new GridLayout(2, 1));
        low.add(scorePanel);

        JPanel inningAndOutsPanel = new JPanel();
        inningAndOutsPanel.setBackground(background);
        inningAndOutsPanel.setLayout(new FlowLayout());
        scorePanel.add(inningAndOutsPanel);

        String inningString = (((currentGame.getInning() + 1) / 2) +
                (currentGame.getInning() % 2 == 0 ? "-" : "+"));
        inningAndOutsPanel.add(new JLabel(inningString));

        out1 = new JRadioButton();
        out1.setEnabled(false);
        inningAndOutsPanel.add(out1);
        out2 = new JRadioButton();
        out2.setEnabled(false);
        inningAndOutsPanel.add(out2);

        if (currentGame.getOuts() >= 1) {
            out1.setSelected(true);
        }
        if (currentGame.getOuts() == 2) {
            out2.setSelected(true);
        }

        inningAndOutsPanel.add(out1);
        inningAndOutsPanel.add(out2);

        JPanel currentScorePanel = new JPanel();
        currentScorePanel.setBackground(background);
        currentScorePanel.setLayout(new FlowLayout());
        scorePanel.add(currentScorePanel);

        currentScorePanel.add(new JLabel(currentGame.getScore()[0] + " - " + currentGame.getScore()[1]));

        // lowest
        JPanel lowest = new JPanel();
        lowest.setBackground(background);
        lowest.setLayout(new GridLayout(1,2));
        mainPanel.add(lowest);

        JPanel pitcherPanel = new JPanel();
        pitcherPanel.setBackground(background);
        pitcherPanel.setLayout(new FlowLayout());
        lowest.add(pitcherPanel);

        pitcherPanel.add(new JLabel("Current Pitcher: " + currentGame.getCurrentPitcher()[currentGame.getCurrentTeamPitchingIndex()] + " "));

        changePitcher = new JButton("Change Pitcher");
        changePitcher.addActionListener(this);
        pitcherPanel.add(changePitcher);

        JPanel hitterPanel = new JPanel();
        hitterPanel.setBackground(background);
        hitterPanel.setLayout(new FlowLayout());
        lowest.add(hitterPanel);

        hitterPanel.add(new JLabel("Current Batter: " +
                currentGame.getCurrentBatter()[currentGame.getCurrentTeamPitchingIndex() == 0 ? 1 : 0]));

        add(mainPanel);
        setVisible(true);
    }
    public void loadGameError() {
        setTitle("Error");
        mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(6,1));
        for (int i = 0; i < 2; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(background);
            mainPanel.add(jPanel);
        }
        JPanel question = new JPanel();
        question.setBackground(background);
        question.setLayout(new FlowLayout());
        mainPanel.add(question);
        question.add(new JLabel("Failed to create Game! Each Roster must have at least 9 players"));

        JPanel responses = new JPanel();
        responses.setBackground(background);
        responses.setLayout(new FlowLayout());
        mainPanel.add(responses);

        seasonBack = new JButton("Okay");
        seasonBack.addActionListener(this);
        responses.add(seasonBack);

        add(mainPanel);
        setVisible(true);
    }

    public void loadChangePitcher(String title) {
        setTitle(title);
        mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(2,1));

        // upper
        JPanel upper = new JPanel();
        upper.setBackground(background);
        upper.setLayout(new GridLayout(1,3));
        mainPanel.add(upper);

        JPanel homePanel = new JPanel();
        homePanel.setBackground(background);
        homePanel.setLayout(new FlowLayout());
        upper.add(homePanel);

        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        homePanel.add(cancel);

        for (int i = 0; i < 1; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(background);
            upper.add(jPanel);
        }

        JPanel savePanel = new JPanel();
        savePanel.setBackground(background);
        savePanel.setLayout(new FlowLayout());
        upper.add(savePanel);

        saveEditedPitcher = new JButton("Save and Exit");
        saveEditedPitcher.addActionListener(this);
        savePanel.add(saveEditedPitcher);

        // pitcher select
        JPanel pitcherSelectPanel = new JPanel();
        pitcherSelectPanel.setBackground(background);
        pitcherSelectPanel.setLayout(new FlowLayout());
        mainPanel.add(pitcherSelectPanel);

        pitcherSelectPanel.add(new JLabel("Pitcher: "));
        changePitcherBox = new JComboBox<Player>();
        for (int i = 0; i < currentGame.getLineups().get(currentGame.getCurrentTeamPitchingIndex()).size(); i++) {
            changePitcherBox.addItem(currentGame.getLineups().get(currentGame.getCurrentTeamPitchingIndex()).get(i));
        }
        changePitcherBox.setSelectedItem(currentGame.getCurrentPitcher(currentGame.getCurrentTeamPitchingIndex()));
        pitcherSelectPanel.add(changePitcherBox);

        add(mainPanel);
        setVisible(true);
    }

    public void loadOther(String title) {
        setTitle(title);
        mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(3,1));

        JPanel combinedHigh = new JPanel();
        combinedHigh.setBackground(background);
        combinedHigh.setLayout(new GridLayout(2,1));
        mainPanel.add(combinedHigh);

        // highest
        JPanel highestPanel = new JPanel();
        highestPanel.setBackground(background);
        highestPanel.setLayout(new GridLayout(1,3));
        combinedHigh.add(highestPanel);

        JPanel cancelPanel = new JPanel();
        cancelPanel.setBackground(background);
        cancelPanel.setLayout(new FlowLayout());
        highestPanel.add(cancelPanel);

        if (cancel == null) {
            cancel = new JButton("Cancel");
            cancel.addActionListener(this);
        }
        cancelPanel.add(cancel);

        for (int i = 0; i < 1; i++) {
            JPanel jPanel = new JPanel();
            jPanel.setBackground(background);
            highestPanel.add(jPanel);
        }

        JPanel savePanel = new JPanel();
        savePanel.setBackground(background);
        savePanel.setLayout(new FlowLayout());
        highestPanel.add(savePanel);

        if (saveCustomEvent == null) {
            saveCustomEvent = new JButton("Save and Exit");
            saveCustomEvent.addActionListener(this);
        }
        savePanel.add(saveCustomEvent);

        // high
        JLabel spacer1 = new JLabel("             ");
        JLabel spacer2 = new JLabel("             ");
        JLabel spacer3 = new JLabel("             ");
        JLabel spacer4 = new JLabel("             ");
        JLabel spacer13 = new JLabel("             ");
        JLabel spacer14 = new JLabel("             ");

        JLabel spacer5 = new JLabel("              ");
        JLabel spacer7 = new JLabel("              ");
        JLabel spacer9 = new JLabel("              ");
        JLabel spacer11 = new JLabel("              ");

        JLabel spacer6 = new JLabel("                ");
        JLabel spacer8 = new JLabel("                ");
        JLabel spacer10 = new JLabel("                ");
        JLabel spacer12 = new JLabel("                ");




        JPanel highPanel = new JPanel();
        highPanel.setBackground(background);
        highPanel.setLayout(new GridLayout(4, 1));
        combinedHigh.add(highPanel);

        JPanel advanceBattingOrderPanel1 = new JPanel();
        advanceBattingOrderPanel1.setBackground(background);
        advanceBattingOrderPanel1.setLayout(new BorderLayout());
        highPanel.add(advanceBattingOrderPanel1);

        JPanel advanceBattingOrderPanel = new JPanel();
        advanceBattingOrderPanel.setBackground(background);
        advanceBattingOrderPanel.setLayout(new FlowLayout());
        advanceBattingOrderPanel1.add(advanceBattingOrderPanel, BorderLayout.WEST);

        advanceBattingOrderPanel.add(spacer1);
        if (advanceBattingOrder == null) {
            advanceBattingOrder = new JCheckBox("Advance Batting Order");
            advanceBattingOrder.addActionListener(this);
            advanceBattingOrder.setSelected(true);
        }
        advanceBattingOrderPanel.add(advanceBattingOrder);

        JPanel errorPanel1 = new JPanel();
        errorPanel1.setBackground(background);
        errorPanel1.setLayout(new BorderLayout());
        highPanel.add(errorPanel1);

        JPanel errorPanel = new JPanel();
        errorPanel.setBackground(background);
        errorPanel.setLayout(new FlowLayout());
        errorPanel1.add(errorPanel, BorderLayout.WEST);

        errorPanel.add(spacer2);
        if (error == null) {
            error = new JCheckBox("Error");
            error.addActionListener(this);
        }
        errorPanel.add(error);

        if (error.isSelected()) {
            if (errorBox == null) {
                errorBox = new JComboBox<>();
                for (int i = 0; i < currentGame.getLineups().get(currentGame.getCurrentTeamPitchingIndex()).size(); i++) {
                    errorBox.addItem(currentGame.getLineups().get(currentGame.getCurrentTeamPitchingIndex()).get(i));
                }
                errorBox.setSelectedItem(null);
            }
            errorPanel.add(errorBox);

            if (hazard == null) {
                hazard = new JCheckBox("Hazard");
                hazard.addActionListener(this);
            }
            errorPanel.add(hazard);

            if (hazard.isSelected()) {
                errorBox.setSelectedItem(null);
                errorBox.setEnabled(false);
            }
            else {
                errorBox.setEnabled(true);
            }
        }
        else {
            errorBox = null;
            hazard = null;
        }

        JPanel stealPanel1 = new JPanel();
        stealPanel1.setBackground(background);
        stealPanel1.setLayout(new BorderLayout());
        highPanel.add(stealPanel1);

        JPanel stealPanel = new JPanel();
        stealPanel.setBackground(background);
        stealPanel.setLayout(new FlowLayout());
        stealPanel1.add(stealPanel, BorderLayout.WEST);

        stealPanel.add(spacer3);

        if (steal == null) {
            steal = new JCheckBox("Steal");
            steal.addActionListener(this);
        }
        stealPanel.add(steal);

        if (steal.isSelected()) {
            if (stealBox == null) {
                stealBox = new JComboBox<>();
                for (int i = 0; i < currentGame.getLineups().get(currentGame.getCurrentTeamPitchingIndex() == 0 ? 1 : 0).size(); i++) {
                    stealBox.addItem(currentGame.getLineups().get(currentGame.getCurrentTeamPitchingIndex() == 0 ? 1 : 0).get(i));
                }
                stealBox.setSelectedItem(null);
            }
            stealPanel.add(stealBox);
        }
        else {
            stealBox = null;
        }

        JPanel sacrificePanel1 = new JPanel();
        sacrificePanel1.setBackground(background);
        sacrificePanel1.setLayout(new BorderLayout());
        highPanel.add(sacrificePanel1);

        JPanel sacrificePanel = new JPanel();
        sacrificePanel.setBackground(background);
        sacrificePanel.setLayout(new FlowLayout());
        sacrificePanel1.add(sacrificePanel, BorderLayout.WEST);

        sacrificePanel.add(spacer4);

        if (sacrifice == null) {
            sacrifice = new JCheckBox("Sacrifice");
            sacrifice.addActionListener(this);
        }
        sacrificePanel.add(sacrifice);

        // low
        JPanel lowPanel = new JPanel();
        lowPanel.setBackground(background);
        lowPanel.setLayout(new GridLayout(8,1));
        mainPanel.add(lowPanel);

        JPanel advanceBatterPanel1 = new JPanel();
        advanceBatterPanel1.setBackground(background);
        advanceBatterPanel1.setLayout(new BorderLayout());
        lowPanel.add(advanceBatterPanel1);

        JPanel advanceBatterPanel = new JPanel();
        advanceBatterPanel.setBackground(background);
        advanceBatterPanel.setLayout(new FlowLayout());
        advanceBatterPanel1.add(advanceBatterPanel, BorderLayout.WEST);

        advanceBatterPanel.add(spacer5);

        if (advanceBatter == null) {
            String[] values = {"0", "1", "2", "3", "4"};
            SpinnerListModel model = new SpinnerListModel(values);
            advanceBatter = new JSpinner(model);

            if (batterListener == null) {
                batterListener = new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        outCheckBox = null;
                        strikeoutCheckBox = null;
                        mainPanel.setVisible(false);
                        loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
                    }
                };
            }

            advanceBatter.addChangeListener(batterListener);
        }
        advanceBatterPanel.add(advanceBatter);

        advanceBatterPanel.add(new JLabel("Advance Batter"));

        if (outCheckBox == null) {
            outCheckBox = new JCheckBox("Out");
            outCheckBox.addActionListener(this);
        }
        advanceBatterPanel.add(outCheckBox);

        if (strikeoutCheckBox == null) {
            strikeoutCheckBox = new JCheckBox("Strikeout");
            strikeoutCheckBox.addActionListener(this);
        }
        advanceBatterPanel.add(strikeoutCheckBox);

        JPanel batterPanelLower1 = new JPanel();
        batterPanelLower1.setBackground(background);
        batterPanelLower1.setLayout(new BorderLayout());
        lowPanel.add(batterPanelLower1);

        JPanel batterPanelLower = new JPanel();
        batterPanelLower.setBackground(background);
        batterPanelLower.setLayout(new FlowLayout());
        batterPanelLower1.add(batterPanelLower, BorderLayout.WEST);

        if (advanceBatter.getValue().equals("4")) {
            if (earnedRun1 == null) {
                earnedRun1 = new JCheckBox("Earned Run");
                earnedRun1.addActionListener(this);
            }
            batterPanelLower.add(spacer6);
            batterPanelLower.add(earnedRun1);
        }
        else {
            earnedRun1 = null;
        }

        // first base
        JPanel firstPanel1 = new JPanel();
        firstPanel1.setBackground(background);
        firstPanel1.setLayout(new BorderLayout());
        lowPanel.add(firstPanel1);

        JPanel firstPanel = new JPanel();
        firstPanel.setBackground(background);
        firstPanel.setLayout(new FlowLayout());
        firstPanel1.add(firstPanel, BorderLayout.WEST);

        firstPanel.add(spacer7);

        if (advanceFirst == null) {
            String[] values = {"0", "1", "2", "3"};
            SpinnerListModel model = new SpinnerListModel(values);
            advanceFirst = new JSpinner(model);

            if (firstListener == null) {
                firstListener = new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        firstBaseOut = null;
                        mainPanel.setVisible(false);
                        loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
                    }
                };
            }

            advanceFirst.addChangeListener(firstListener);
        }
        firstPanel.add(advanceFirst);

        firstPanel.add(new JLabel("Advance 1st Runner"));

        if (firstBaseOut== null) {
            firstBaseOut = new JCheckBox("1st Runner Out");
            firstBaseOut.addActionListener(this);
        }
        firstPanel.add(firstBaseOut);


        JPanel firstPanelLower1 = new JPanel();
        firstPanelLower1.setBackground(background);
        firstPanelLower1.setLayout(new BorderLayout());
        lowPanel.add(firstPanelLower1);

        JPanel firstPanelLower = new JPanel();
        firstPanelLower.setBackground(background);
        firstPanelLower.setLayout(new FlowLayout());
        firstPanelLower1.add(firstPanelLower, BorderLayout.WEST);

        if (advanceFirst.getValue().toString().compareTo("2") > 0) {
            if (earnedRun2 == null) {
                earnedRun2 = new JCheckBox("Earned Run");
                earnedRun2.addActionListener(this);
            }
            firstPanelLower.add(spacer8);
            firstPanelLower.add(earnedRun2);
        }
        else {
            earnedRun2 = null;
        }

        if (firstBaseOut.isSelected()) {
            if (caughtStealing1 == null) {
                caughtStealing1 = new JCheckBox("Caught Stealing");
                caughtStealing1.addActionListener(this);
            }
            firstPanelLower.add(new JLabel("                                                           "));
            firstPanelLower.add(caughtStealing1);
        }
        else {
            caughtStealing1 = null;
        }



        // second base
        JPanel secondPanel1 = new JPanel();
        secondPanel1.setBackground(background);
        secondPanel1.setLayout(new BorderLayout());
        lowPanel.add(secondPanel1);

        JPanel secondPanel = new JPanel();
        secondPanel.setBackground(background);
        secondPanel.setLayout(new FlowLayout());
        secondPanel1.add(secondPanel, BorderLayout.WEST);

        secondPanel.add(spacer9);

        if (advanceSecond == null) {
            String[] values = {"0", "1", "2"};
            SpinnerListModel model = new SpinnerListModel(values);
            advanceSecond = new JSpinner(model);

            if (secondListener == null) {
                secondListener = new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        secondBaseOut = null;
                        mainPanel.setVisible(false);
                        loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
                    }
                };
            }

            advanceSecond.addChangeListener(secondListener);
        }
        secondPanel.add(advanceSecond);

        secondPanel.add(new JLabel("Advance 2nd Runner"));

        if (secondBaseOut== null) {
            secondBaseOut = new JCheckBox("2nd Runner Out");
            secondBaseOut.addActionListener(this);
        }
        secondPanel.add(secondBaseOut);


        JPanel secondPanelLower1 = new JPanel();
        secondPanelLower1.setBackground(background);
        secondPanelLower1.setLayout(new BorderLayout());
        lowPanel.add(secondPanelLower1);

        JPanel secondPanelLower = new JPanel();
        secondPanelLower.setBackground(background);
        secondPanelLower.setLayout(new FlowLayout());
        secondPanelLower1.add(secondPanelLower, BorderLayout.WEST);

        if (advanceSecond.getValue().toString().compareTo("1") > 0) {
            if (earnedRun3 == null) {
                earnedRun3 = new JCheckBox("Earned Run");
                earnedRun3.addActionListener(this);
            }
            secondPanelLower.add(spacer10);
            secondPanelLower.add(earnedRun3);
        }
        else {
            earnedRun3 = null;
        }

        if (secondBaseOut.isSelected()) {
            if (caughtStealing2 == null) {
                caughtStealing2 = new JCheckBox("Caught Stealing");
                caughtStealing2.addActionListener(this);
            }
            secondPanelLower.add(new JLabel("                                                           "));
            secondPanelLower.add(caughtStealing2);
        }
        else {
            caughtStealing2 = null;
        }

        // third base
        JPanel thirdPanel1 = new JPanel();
        thirdPanel1.setBackground(background);
        thirdPanel1.setLayout(new BorderLayout());
        lowPanel.add(thirdPanel1);

        JPanel thirdPanel = new JPanel();
        thirdPanel.setBackground(background);
        thirdPanel.setLayout(new FlowLayout());
        thirdPanel1.add(thirdPanel, BorderLayout.WEST);

        thirdPanel.add(spacer11);

        if (advanceThird == null) {
            String[] values = {"0", "1"};
            SpinnerListModel model = new SpinnerListModel(values);
            advanceThird = new JSpinner(model);

            if (thirdListener == null) {
                thirdListener = new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        thirdBaseOut = null;
                        mainPanel.setVisible(false);
                        loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
                    }
                };
            }

            advanceThird.addChangeListener(thirdListener);
        }
        thirdPanel.add(advanceThird);

        thirdPanel.add(new JLabel("Advance 3rd Runner"));

        if (thirdBaseOut == null) {
            thirdBaseOut = new JCheckBox("3rd Runner Out");
            thirdBaseOut.addActionListener(this);
        }
        thirdPanel.add(thirdBaseOut);


        JPanel thirdPanelLower1 = new JPanel();
        thirdPanelLower1.setBackground(background);
        thirdPanelLower1.setLayout(new BorderLayout());
        lowPanel.add(thirdPanelLower1);

        JPanel thirdPanelLower = new JPanel();
        thirdPanelLower.setBackground(background);
        thirdPanelLower.setLayout(new FlowLayout());
        thirdPanelLower1.add(thirdPanelLower, BorderLayout.WEST);

        if (advanceThird.getValue().toString().compareTo("0") > 0) {
            if (earnedRun4 == null) {
                earnedRun4 = new JCheckBox("Earned Run");
                earnedRun4.addActionListener(this);
            }
            thirdPanelLower.add(spacer12);
            thirdPanelLower.add(earnedRun4);
        }
        else {
            earnedRun4 = null;
        }

        if (thirdBaseOut.isSelected()) {
            if (caughtStealing3 == null) {
                caughtStealing3 = new JCheckBox("Caught Stealing");
                caughtStealing3.addActionListener(this);
            }
            thirdPanelLower.add(new JLabel("                                                           "));
            thirdPanelLower.add(caughtStealing3);
        }
        else {
            caughtStealing3 = null;
        }

        // lowest
        JPanel lowestPanel = new JPanel();
        lowestPanel.setBackground(background);
        lowestPanel.setLayout(new GridLayout(8,1));
        mainPanel.add(lowestPanel);

        JPanel hitPitcherPanel1 = new JPanel();
        hitPitcherPanel1.setBackground(background);
        hitPitcherPanel1.setLayout(new BorderLayout());
        lowestPanel.add(hitPitcherPanel1);

        JPanel hitPitcherPanel = new JPanel();
        hitPitcherPanel.setBackground(background);
        hitPitcherPanel.setLayout(new FlowLayout());
        hitPitcherPanel1.add(hitPitcherPanel, BorderLayout.WEST);

        hitPitcherPanel.add(spacer13);

        if (hitPitcher == null) {
            hitPitcher = new JCheckBox("Hit (Pitcher)");
            hitPitcher.addActionListener(this);
        }
        hitPitcherPanel.add(hitPitcher);

        JPanel hitBatterPanel1 = new JPanel();
        hitBatterPanel1.setBackground(background);
        hitBatterPanel1.setLayout(new BorderLayout());
        lowestPanel.add(hitBatterPanel1);

        JPanel hitBatterPanel = new JPanel();
        hitBatterPanel.setBackground(background);
        hitBatterPanel.setLayout(new FlowLayout());
        hitBatterPanel1.add(hitBatterPanel, BorderLayout.WEST);

        hitBatterPanel.add(spacer14);

        if (hitBatter == null) {
            hitBatter = new JCheckBox("Hit (Batter)");
            hitBatter.addActionListener(this);
        }
        hitBatterPanel.add(hitBatter);

        if (hitBatter.isSelected()) {
            if (hitType == null) {
                hitType = new JComboBox<>();
                hitType.addItem("Single");
                hitType.addItem("Double");
                hitType.addItem("Triple");
                hitType.addItem("Home Run");
                hitType.addItem("Walk");

                hitType.setSelectedItem(null);
            }
            hitBatterPanel.add(hitType);
        }
        else {
            hitType = null;
        }

        add(mainPanel);
        setVisible(true);
    }
    public void save() {
        try {
            writeToFile(allFiles, "seasons.xml");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void saveOther() {}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == home) {
            mainPanel.setVisible(false);
            loadHome();
        }

        if (e.getSource() == save) {
            save();
        }

        if (e.getSource() == newSeason) {
            allFiles.addSeason();
            mainPanel.setVisible(false);
            currentSeason = allFiles.getSeason(allFiles.getSeasons().size() - 1);
            loadSeason.add(new JButton());
            loadSeason(allFiles.getSeason(allFiles.getSeasons().size() - 1), "Season " + allFiles.getSeasons().size());
        }

        for (int i = 0; i < allFiles.getSeasons().size(); i++) {
            if (e.getSource() == loadSeason.get(i)) {
                mainPanel.setVisible(false);
                currentSeason = allFiles.getSeason(i);
                loadSeason(allFiles.getSeason(i), "Season " + (i + 1));
            }
        }

        if (e.getSource() == deleteSeason) {
            mainPanel.setVisible(false);
            loadDeleteSeason(currentSeason, "" + (allFiles.getSeasons().indexOf(currentSeason) + 1));
        }

        if (e.getSource() == deleteSeasonNo) {
            mainPanel.setVisible(false);
            loadSeason(currentSeason, "Season " + (allFiles.getSeasons().indexOf(currentSeason) + 1));
        }

        if (e.getSource() == deleteSeasonYes) {
            mainPanel.setVisible(false);
            allFiles.getSeasons().remove(currentSeason);
            loadHome();
        }

        if (e.getSource() == newGame) {
            if (currentSeason.getRoster(0).getPlayers().size() >= 9 && currentSeason.getRoster(1).getPlayers().size() >= 9) {
                currentSeason.addGame();
                mainPanel.setVisible(false);
                currentGame = currentSeason.getGame(currentSeason.getGames().size() - 1);
                for (int i = 0; i < 9; i++) {
                    currentGame.setPlayerToLineup(currentSeason.getRoster(0).getPlayer(i), 0, i);
                    currentGame.setPlayerToLineup(currentSeason.getRoster(1).getPlayer(i), 1, i);
                }
                currentGame.setStartingPitcher(0, currentSeason.getRoster(0).getPlayer(0));
                currentGame.setStartingPitcher(1, currentSeason.getRoster(1).getPlayer(0));
                loadGame.add(new JButton());
                loadGame(currentSeason.getGame(currentSeason.getGames().size() - 1), "Game " + currentSeason.getGames().size());
            }
            else {
                mainPanel.setVisible(false);
                loadGameError();
            }
        }

        for (int i = 0; i < currentSeason.getGames().size(); i++) {
            if (e.getSource() == loadGame.get(i)) {
                mainPanel.setVisible(false);
                currentGame = currentSeason.getGame(i);
                loadGame(currentSeason.getGame(i), "Game " + (i + 1));
            }
        }

        if (e.getSource() == deleteGame) {
            mainPanel.setVisible(false);
            loadDeleteGame(currentGame, "" + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == deleteGameNo) {
            mainPanel.setVisible(false);
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == deleteGameYes) {
            mainPanel.setVisible(false);
            currentSeason.getGames().remove(currentGame);
            loadSeason(currentSeason, "Season " + (allFiles.getSeasons().indexOf(currentSeason) + 1));
        }

        if (e.getSource() == seasonBack) {
            mainPanel.setVisible(false);
            loadSeason(currentSeason, "Season " + (allFiles.getSeasons().indexOf(currentSeason) + 1));
        }

        if (e.getSource() == enterGame) {
            mainPanel.setVisible(false);
            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == day) {
            mainPanel.setVisible(false);
            currentGame.setTimeOfDay("Day");
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == night) {
            mainPanel.setVisible(false);
            currentGame.setTimeOfDay("Night");
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == marioStadium) {
            mainPanel.setVisible(false);
            currentGame.setStadium("Mario Stadium");
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == yoshiPark) {
            mainPanel.setVisible(false);
            currentGame.setStadium("Yoshi Park");
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == warioCity) {
            mainPanel.setVisible(false);
            currentGame.setStadium("Wario City");
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == dkJungle) {
            mainPanel.setVisible(false);
            currentGame.setStadium("DK Jungle");
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == bjPlayroom) {
            mainPanel.setVisible(false);
            currentGame.setStadium("Bowser Jr Playroom");
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == peachIceGardens) {
            mainPanel.setVisible(false);
            currentGame.setStadium("Peach Ice Gardens");
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == bowserCastle) {
            mainPanel.setVisible(false);
            currentGame.setStadium("Bowser Castle");
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == luigiMansion) {
            mainPanel.setVisible(false);
            currentGame.setStadium("Luigi Mansion");
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == daisyCruiser) {
            mainPanel.setVisible(false);
            currentGame.setStadium("Daisy Cruiser");
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == editLineup1) {
            mainPanel.setVisible(false);
            currentRoster = currentSeason.getRoster(0);
            currentRosterIndex = 0;
            currentTeam = currentGame.getTeam(0);
            currentTeamIndex = 0;
            loadEditLineup(currentGame.getTeam(0), "Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Lineup 1");
        }

        if (e.getSource() == editLineup2) {
            mainPanel.setVisible(false);
            currentRoster = currentSeason.getRoster(1);
            currentRosterIndex = 1;
            currentTeam = currentGame.getTeam(1);
            currentTeamIndex = 1;
            loadEditLineup(currentGame.getTeam(1), "Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Lineup 2");
        }

        if (e.getSource() == save2) {
            for (int i = 0; i < 9; i++) {
                currentGame.setPlayerToLineup((Player) playerJComboBox.get(i).getSelectedItem(), currentTeamIndex, i);
            }
            currentGame.setStartingPitcher(currentTeamIndex, (Player) pitchJComboBox.getSelectedItem());
            save();
        }

        if (e.getSource() == saveEditedLineup) {
            mainPanel.setVisible(false);
            for (int i = 0; i < 9; i++) {
                currentGame.setPlayerToLineup((Player) playerJComboBox.get(i).getSelectedItem(), currentTeamIndex, i);
            }
            currentGame.setStartingPitcher(currentTeamIndex, (Player) pitchJComboBox.getSelectedItem());
            save();
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == pitchesFirst) {
            currentGame.setPitchesFirst(!currentGame.isPitchesFirst());
        }

        if (e.getSource() == editRoster1) {
            mainPanel.setVisible(false);
            currentRoster = currentSeason.getRoster(0);
            currentRosterIndex = 0;
            loadEditRoster(currentSeason.getRoster(0), "Season " + (allFiles.getSeasons().indexOf(currentSeason) + 1) + " - Roster 1");
        }

        if (e.getSource() == editRoster2) {
            mainPanel.setVisible(false);
            currentRoster = currentSeason.getRoster(1);
            currentRosterIndex = 1;
            loadEditRoster(currentSeason.getRoster(1), "Season " + (allFiles.getSeasons().indexOf(currentSeason) + 1) + " - Roster 2");
        }

        if (e.getSource() == saveEditedRoster) {
            save();
            mainPanel.setVisible(false);
            loadSeason(currentSeason, "Season " + (allFiles.getSeasons().indexOf(currentSeason) + 1));
        }

        if (e.getSource() == addPlayerToRoster) {
            Player player = new Player();
            player.setName(nameField.getText());
            player.setDefensePosition(positionField.getText());
            player.setPitch(pitchField.getText());

            currentSeason.addPlayer(currentRosterIndex, player);
            mainPanel.setVisible(false);
            loadEditRoster(currentRoster, "Season " + (allFiles.getSeasons().indexOf(currentSeason) + 1) + " - Roster " + (currentRosterIndex + 1));
        }

        if (e.getSource() == resetRoster) {
            currentSeason.setRoster(new Team(), currentRosterIndex);
            currentRoster = currentSeason.getRoster(currentRosterIndex);
            mainPanel.setVisible(false);
            loadEditRoster(currentRoster, "Season " + (allFiles.getSeasons().indexOf(currentSeason) + 1) + " - Roster " + (currentRosterIndex + 1));
        }

        if (e.getSource() == deleteLastEntry) {
            currentSeason.getRoster(currentRosterIndex).getPlayers().remove(currentSeason.getRoster(currentRosterIndex).getPlayers().size() - 1);
            mainPanel.setVisible(false);
            loadEditRoster(currentRoster, "Season " + (allFiles.getSeasons().indexOf(currentSeason) + 1) + " - Roster " + (currentRosterIndex + 1));
        }

        if (e.getSource() == saveGame) {
            mainPanel.setVisible(false);
            save();
            loadGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == changePitcher) {
            mainPanel.setVisible(false);
            loadChangePitcher("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Change Pitcher " +
                    (currentGame.getCurrentTeamPitchingIndex() + 1));
        }

        if (e.getSource() == strikeOut) {
            mainPanel.setVisible(false);
            currentGame.strikeOut();
            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == out) {
            mainPanel.setVisible(false);
            currentGame.hitAdvance(0);
            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == singleHit) {
            mainPanel.setVisible(false);
            currentGame.hit(1);
            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == singleHitAdvance) {
            mainPanel.setVisible(false);
            currentGame.hitAdvance(1);
            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == doubleHit) {
            mainPanel.setVisible(false);
            currentGame.hit(2);
            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == doubleHitAdvance) {
            mainPanel.setVisible(false);
            currentGame.hitAdvance(2);
            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == tripleHit) {
            mainPanel.setVisible(false);
            currentGame.hitAdvance(3);
            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == homeRunHit) {
            mainPanel.setVisible(false);
            currentGame.hitAdvance(4);
            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == walk) {
            mainPanel.setVisible(false);
            currentGame.walk();
            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == cancel) {
            mainPanel.setVisible(false);
            saveCustomEvent = null;
            advanceBattingOrder = null;
            error = null;
            errorBox = null;
            hazard = null;
            steal = null;
            stealBox = null;
            sacrifice = null;
            advanceBatter = null;
            earnedRun1 = null;
            outCheckBox = null;
            strikeoutCheckBox = null;
            advanceFirst = null;
            earnedRun2 = null;
            firstBaseOut = null;
            caughtStealing1 = null;
            advanceSecond = null;
            earnedRun3 = null;
            secondBaseOut = null;
            caughtStealing2 = null;
            advanceThird = null;
            earnedRun4 = null;
            thirdBaseOut = null;
            caughtStealing3 = null;
            hitPitcher = null;
            hitBatter = null;
            hitType = null;

            batterListener = null;
            firstListener = null;
            secondListener = null;
            thirdListener = null;

            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == saveEditedPitcher) {
            mainPanel.setVisible(false);
            currentGame.placePitcher(currentGame.getCurrentTeamPitchingIndex(), (Player) changePitcherBox.getSelectedItem());
            save();
            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }

        if (e.getSource() == other) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == advanceBattingOrder) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == error) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == hazard) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == steal) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == sacrifice) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == outCheckBox) {
            mainPanel.setVisible(false);
            advanceBatter = null;
            if (outCheckBox.isSelected()) {
                strikeoutCheckBox = null;
            }
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == strikeoutCheckBox) {
            mainPanel.setVisible(false);
            advanceBatter = null;
            if (strikeoutCheckBox.isSelected()) {
                outCheckBox = null;
            }
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == earnedRun1) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == firstBaseOut) {
            mainPanel.setVisible(false);
            advanceFirst = null;
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == earnedRun2) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == caughtStealing1) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == secondBaseOut) {
            mainPanel.setVisible(false);
            advanceSecond = null;
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == earnedRun3) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == caughtStealing2) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == thirdBaseOut) {
            mainPanel.setVisible(false);
            advanceThird = null;
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == earnedRun4) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == caughtStealing3) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == hitPitcher) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == hitBatter) {
            mainPanel.setVisible(false);
            loadOther("Game " + (currentSeason.getGames().indexOf(currentGame) + 1) + " - Other");
        }

        if (e.getSource() == saveCustomEvent) {
            mainPanel.setVisible(false);
            saveOther();

            saveCustomEvent = null;
            advanceBattingOrder = null;
            error = null;
            errorBox = null;
            hazard = null;
            steal = null;
            stealBox = null;
            sacrifice = null;
            advanceBatter = null;
            earnedRun1 = null;
            outCheckBox = null;
            strikeoutCheckBox = null;
            advanceFirst = null;
            earnedRun2 = null;
            firstBaseOut = null;
            caughtStealing1 = null;
            advanceSecond = null;
            earnedRun3 = null;
            secondBaseOut = null;
            caughtStealing2 = null;
            advanceThird = null;
            earnedRun4 = null;
            thirdBaseOut = null;
            caughtStealing3 = null;
            hitPitcher = null;
            hitBatter = null;
            hitType = null;

            batterListener = null;
            firstListener = null;
            secondListener = null;
            thirdListener = null;

            enterGame(currentGame, "Game " + (currentSeason.getGames().indexOf(currentGame) + 1));
        }
    }

    // Output AllFiles to file
    public static void writeToFile(AllFiles allfiles, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(allfiles);
        oos.close();
    }

    // Input AllFiles from file
    public static AllFiles loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        File file = new File("seasons.xml");
        if (file.exists() && !file.isDirectory()) {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            AllFiles allFiles = (AllFiles) ois.readObject();
            ois.close();
            return allFiles;
        }

        return new AllFiles();
    }
}
