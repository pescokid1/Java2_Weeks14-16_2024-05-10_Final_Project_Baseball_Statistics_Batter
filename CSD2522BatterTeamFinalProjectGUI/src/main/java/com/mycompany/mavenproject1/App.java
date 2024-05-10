/*
Authors: Luke Dawson, Terry Pescosolido, Cedrick Charles, Gavin Mefford-Gibbins, Wesley Morah
Date created: 4/24/24
Purpose: create a GUI to accept input from a user and write it into a Sqlite database
Updated by:
Luke Dawson - 4/24/24 - created the skeleton for the GUI
Luke Dawson - 4/25/24 - added button functionality to add the data to the database
    and added starter check box. Changed default value of each text box to 0 and
    made reset button return them all to 0 rather than empty them
Luke Dawson - 4/26/24 - added integer parsing for the text fields before adding
    data to the database
Luke Dawson - 4/28/24 - added formatting to clean up the GUI and make it more manageable
Terry Pescosolido - 4/28/24 - changed calls to database for integration
Luke Dawson - 4/29/24 - added "Add new Batter" menu
Terry Pescosolido - 4/29/24 - fixed interaction for new player name combo box with db
Luke Dawson - 4/30/24 - added a menu option to enter a new game
Terry Pescosolido - 4/30/24 - fixed interaction for new game combo box with db
Gavin Mefford-Gibbins - 4/30/2024 - Added functionality to the View Game Report button
Cedrick Charles - 4/30/2024 - Made modification to the display of the View Game Report button added by Gavin
Luke Dawson - 5/3/24 - added error handling to the submit functions
Terry Pescosolido - 5/3/24 - small change to database call on add player stats
Terry Pescosolido - 5/4/24 - small change to reset combo boxes before loading in add player stats
Luke Dawson 5/4/24 - fixed resetButtonClicked to work with the enterGame Button and fixed
    issue regarding manually entering dates into the datePicker field
Terry Pescosolido - 5/5/24 - add crude game report
Terry Pescosolido - 5/5/24 - tweaks to game report
Terry Pescosolido - 5/5/24 - tweaks to game report to increase gridpane size and font
Luke Dawson - 5/6/24 - added error handling for data integrity
Terry Pescosolido - 5/6/24 - enahnced error-checking in submitButtonClicked
Gavin Mefford-Gibbins - 5/7/2024 - Polished the "rough" game report a little bit to line it up
Gavin Mefford-Gibbins - 5/7/2024 - Added Functionality to the Create file button on single game report page
Gavin Mefford-Gibbins - 5/7/2024 - fixed File write to write both types of single game report 
    and fixed spacing issue caused by long names
Cedrick Charles       - 5/7/2024 - Added the View multiple game report method and tested the program.
Luke Dawson - 5/8/24 - added a new menu to the viewMultiGameReportButtonClicked function
Luke Dawson - 5/8/24 - added checks on main menu report buttons to assure enough games exist
Terry Pescosolido - 5/8/24 - added totals to and tweaked formatting on game reports, restored totals to online game reports
Gavin Mefford-Gibbins - 5/8/2024 - added rough functionality to the MultiGameReport option
Terry Pescosolido - 5/9/24 - add buildReportLines to build all report lines for a game or multi-game, updated online game display to use
Terry Pescosolido - 5/9/24 - updated writeReportsToFile to use buildReportLines
Luke Dawson - 5/9/24 - added check on enter player stats button to ensure a player and a game exists
Gavin Mefford-Gibbins - 5/9/2024 - Added functioning return button and file print button to the multi game report
Gavin Mefford-Gibbins - 5/9/2024 - Fixed formatting in the multi game report to accomodate for longer names
Gavin Mefford-Gibbins - 5/9/2024 - Fixed width of scrollpane in multi game report
Gavin Mefford-Gibbins - 5/9/2024 - Removed commented out code and fixed make the second game in the multi game report required 
    to be after the first game chosen.
Terry Pescosolido - 5/10/24 - added file report headers; sort mulit-game by batting avg
Terry Pescosolido - 5/10/24 - added protection against duplicate games and duplicate players
Terry Pescosolido - 5/10/24 - fixed filewrite so it will overwrite if file exists
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Collections;
import javafx.scene.control.ScrollPane;

/**
 * JavaFX App
 */
public class App extends Application {

    // create labels
    private Label gameLabel = new Label("Game");
    private Label playerLabel = new Label("Player:");
    private Label starterLabel = new Label("Starter");
    private Label battingOrderLabel = new Label("Batting Order #");
    private Label atBatLabel = new Label("At-Bats:");
    private Label runLabel = new Label("Runs:");
    private Label singleLabel = new Label("Singles:");
    private Label doubleLabel = new Label("Doubles:");
    private Label tripleLabel = new Label("Triples:");
    private Label homeRunLabel = new Label("Home Runs:");
    private Label basesOnBallLabel = new Label("Bases-on-Balls:");
    private Label hitsByPitchLabel = new Label("Hits-by-Pitch:");
    private Label runsBattedInLabel = new Label("Runs Batted In:");
    private Label strikeOutLabel = new Label("Strike Outs:");
    private Label groundedDoublePlayLabel = new Label("Grounded Double Plays:");
    private Label stolenBaseAttemptLabel = new Label("Stolen Base Attempts:");
    private Label stolenBaseSuccessLabel = new Label("Stolen Base Successes:");
    private Label sacrificeFliesLabel = new Label("Sacrifice Flies:");
    private Label sacrificeHitsLabel = new Label("Sacrifice Hits:");
    private Label leftOnBaseLabel = new Label("Left on Base:");
    private Label firstNameLabel = new Label("First Name:");
    private Label lastNameLabel = new Label("Last Name:");
    private Label enterPlayerNumberLabel = new Label("Player Number:");
    private Label activeLabel = new Label("Active");
    private Label gameNumberLabel = new Label("Game #");
    private Label opponentLabel = new Label("Opponent");
    private Label gameDateLabel = new Label("Game Date (M/D/YYYY)");

    // create textfields
    private ComboBox<String> gameComboBox = new ComboBox<>();
    ComboBox<String> playerComboBox = new ComboBox<>();
    private CheckBox starterCheckBox = new CheckBox("");
    private TextField battingOrderField = new TextField("0");
    private TextField atBatField = new TextField("0");
    private TextField runField = new TextField("0");
    private TextField singleField = new TextField("0");
    private TextField doubleField = new TextField("0");
    private TextField tripleField = new TextField("0");
    private TextField homeRunField = new TextField("0");
    private TextField basesOnBallField = new TextField("0");
    private TextField hitsByPitchField = new TextField("0");
    private TextField runsBattedInField = new TextField("0");
    private TextField strikeOutField = new TextField("0");
    private TextField groundedDoublePlayField = new TextField("0");
    private TextField stolenBaseAttemptField = new TextField("0");
    private TextField stolenBaseSuccessField = new TextField("0");
    private TextField sacrificeFliesField = new TextField("0");
    private TextField sacrificeHitsField = new TextField("0");
    private TextField leftOnBaseField = new TextField("0");
    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField enterPlayerNumberField = new TextField();
    private CheckBox activeCheckBox = new CheckBox("");
    private TextField gameNumberField = new TextField();
    private TextField opponentField = new TextField();
    private DatePicker gameDatePicker = new DatePicker();

    BaseballStatsDB baseball_stats_db = new BaseballStatsDB();
    String shortGameReport = "ShortGameReport";
    String detailedGameReport = "DetailedGameReport";
    String detailedMultiReport = "DetailedMultiReport";
    static String TAB = "\t";
    static String SPACE = " ";
    static String OSUBaseball = "Ohio State Baseball";

    // create the primary stage for the main menu
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        // set title
        stage.setTitle("Baseball Stats");

        // create a new grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(10);
        grid.setVgap(10);

        // create the scene
        Scene scene = new Scene(grid, 250, 260);

        // create buttons for different actions
        Button enterDataButton = new Button("Enter Batter Stats");
        Button enterPlayerButton = new Button("Enter New Batter");
        Button enterGameButton = new Button("Enter New Game");
        Button viewGameReportButton = new Button("View Game Report");
        Button viewMultiGameReportButton = new Button("View Multi-Game Report");

        // VBox to hold the different options the user can choose from
        VBox optionBox = new VBox(10);
        optionBox.getChildren().add(enterDataButton);
        optionBox.getChildren().add(enterPlayerButton);
        optionBox.getChildren().add(enterGameButton);
        optionBox.getChildren().add(viewGameReportButton);
        optionBox.getChildren().add(viewMultiGameReportButton);
        grid.add(optionBox, 0, 0, 3, 1);

        enterDataButton.setOnAction(event -> enterDataButtonClicked());
        enterPlayerButton.setOnAction(event -> enterBatterButtonClicked());
        enterGameButton.setOnAction(event -> enterGameButtonClicked());
        viewGameReportButton.setOnAction(event -> viewGameReportButtonClicked());
        viewMultiGameReportButton.setOnAction(event -> viewMultiGameReportButtonClicked());

        // exit button
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> exitButtonClicked());

        // HBox to hold the utility commands
        HBox utilityBox = new HBox(10);
        utilityBox.getChildren().add(exitButton);
        grid.add(utilityBox, 0, 2, 1, 1);

        stage.setScene(scene);
        stage.show();

    }

    // method for when the user selects the "Enter Data" button
    private void enterDataButtonClicked() {
        if (baseball_stats_db.getGames().isEmpty() || baseball_stats_db.getPlayers().isEmpty()) {
            // show error message if no games or players are found
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Cannot enter player stats");
            errorAlert.setContentText("Please make sure there is at least one existing batter and game before entering stats.");
            errorAlert.showAndWait();
        } else {
            // create a new scene for entering data
            GridPane enterDataGrid = new GridPane();
            enterDataGrid.setAlignment(Pos.TOP_LEFT);
            enterDataGrid.setPadding(new Insets(25, 25, 25, 25));
            enterDataGrid.setHgap(10);
            enterDataGrid.setVgap(10);

            gameComboBox.getItems().clear();
            for (Game game : baseball_stats_db.getGames()) {
                gameComboBox.getItems().add("Game " + game.getGameNumber() + " - " + game.getGameDate() + " - " + game.getGameOpponentName()); // Add game names to ComboBox
            }
            enterDataGrid.add(gameComboBox, 1, 1, 1, 1);

            playerComboBox.getItems().clear();
            for (Player player : baseball_stats_db.getPlayers()) {
                playerComboBox.getItems().add(player.getPlayerName() + " #" + player.getPlayerNumber()); // Add player names to ComboBox
            }
            enterDataGrid.add(playerComboBox, 1, 1, 1, 1);

            VBox labelBox1 = new VBox(18);
            labelBox1.getChildren().add(gameLabel);
            labelBox1.getChildren().add(playerLabel);
            labelBox1.getChildren().add(starterLabel);
            labelBox1.getChildren().add(battingOrderLabel);
            labelBox1.getChildren().add(atBatLabel);
            labelBox1.getChildren().add(runLabel);
            labelBox1.getChildren().add(singleLabel);
            labelBox1.getChildren().add(doubleLabel);
            labelBox1.getChildren().add(tripleLabel);
            labelBox1.getChildren().add(homeRunLabel);
            enterDataGrid.add(labelBox1, 0, 0);

            VBox labelBox2 = new VBox(18);
            labelBox2.getChildren().add(basesOnBallLabel);
            labelBox2.getChildren().add(hitsByPitchLabel);
            labelBox2.getChildren().add(runsBattedInLabel);
            labelBox2.getChildren().add(strikeOutLabel);
            labelBox2.getChildren().add(groundedDoublePlayLabel);
            labelBox2.getChildren().add(stolenBaseAttemptLabel);
            labelBox2.getChildren().add(stolenBaseSuccessLabel);
            labelBox2.getChildren().add(sacrificeFliesLabel);
            labelBox2.getChildren().add(sacrificeHitsLabel);
            labelBox2.getChildren().add(leftOnBaseLabel);
            enterDataGrid.add(labelBox2, 2, 0);

            VBox textFieldBox1 = new VBox(10);
            textFieldBox1.getChildren().add(gameComboBox);
            textFieldBox1.getChildren().add(playerComboBox);
            textFieldBox1.getChildren().add(starterCheckBox);
            textFieldBox1.getChildren().add(battingOrderField);
            textFieldBox1.getChildren().add(atBatField);
            textFieldBox1.getChildren().add(runField);
            textFieldBox1.getChildren().add(singleField);
            textFieldBox1.getChildren().add(doubleField);
            textFieldBox1.getChildren().add(tripleField);
            textFieldBox1.getChildren().add(homeRunField);
            enterDataGrid.add(textFieldBox1, 1, 0);

            VBox textFieldBox2 = new VBox(10);
            textFieldBox2.getChildren().add(basesOnBallField);
            textFieldBox2.getChildren().add(hitsByPitchField);
            textFieldBox2.getChildren().add(runsBattedInField);
            textFieldBox2.getChildren().add(strikeOutField);
            textFieldBox2.getChildren().add(groundedDoublePlayField);
            textFieldBox2.getChildren().add(stolenBaseAttemptField);
            textFieldBox2.getChildren().add(stolenBaseSuccessField);
            textFieldBox2.getChildren().add(sacrificeFliesField);
            textFieldBox2.getChildren().add(sacrificeHitsField);
            textFieldBox2.getChildren().add(leftOnBaseField);
            enterDataGrid.add(textFieldBox2, 3, 0);

            // add a submit button
            Button submitButton = new Button("Submit");
            submitButton.setOnAction(event -> submitButtonClicked());
            // add a reset button
            Button resetButton = new Button("Reset");
            resetButton.setOnAction(event -> resetButtonClicked());
            // add a return button
            Button returnButton = new Button("Return");
            returnButton.setOnAction(event -> returnButtonClicked());

            // HBox to hold the submit and return buttons
            HBox submitReturnBox = new HBox(10);
            submitReturnBox.getChildren().add(submitButton);
            submitReturnBox.getChildren().add(resetButton);
            submitReturnBox.getChildren().add(returnButton);
            enterDataGrid.add(submitReturnBox, 0, 1, 2, 1);

            // set the new scene
            Scene enterDataScene = new Scene(enterDataGrid);
            primaryStage.setScene(enterDataScene);
        }
    }

    private void enterBatterButtonClicked() {
        // create a new scene for entering data
        GridPane enterDataGrid = new GridPane();
        enterDataGrid.setAlignment(Pos.TOP_LEFT);
        enterDataGrid.setPadding(new Insets(25, 25, 25, 25));
        enterDataGrid.setHgap(10);
        enterDataGrid.setVgap(10);

        VBox labelBox = new VBox(20);
        labelBox.getChildren().add(firstNameLabel);
        labelBox.getChildren().add(lastNameLabel);
        labelBox.getChildren().add(enterPlayerNumberLabel);
        //labelBox.getChildren().add(activeLabel);
        enterDataGrid.add(labelBox, 0, 0);

        VBox textBox = new VBox(10);
        textBox.getChildren().add(firstNameField);
        textBox.getChildren().add(lastNameField);
        textBox.getChildren().add(enterPlayerNumberField);
        //textBox.getChildren().add(activeCheckBox);
        enterDataGrid.add(textBox, 1, 0);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> submitPlayerButtonClicked());
        // add a reset button
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> resetButtonClicked());
        // add a return button
        Button returnButton = new Button("Return");
        returnButton.setOnAction(event -> returnButtonClicked());

        // HBox to hold the submit and return buttons
        HBox submitReturnBox = new HBox(10);
        submitReturnBox.getChildren().add(submitButton);
        submitReturnBox.getChildren().add(resetButton);
        submitReturnBox.getChildren().add(returnButton);
        enterDataGrid.add(submitReturnBox, 0, 1, 2, 1);

        // set the new scene
        Scene enterDataScene = new Scene(enterDataGrid);
        primaryStage.setScene(enterDataScene);
    }

    private void enterGameButtonClicked() {
        // create a new scene for entering data
        GridPane enterGameGrid = new GridPane();
        enterGameGrid.setAlignment(Pos.TOP_LEFT);
        enterGameGrid.setPadding(new Insets(25, 25, 25, 25));
        enterGameGrid.setHgap(10);
        enterGameGrid.setVgap(10);

        VBox labelBox = new VBox(20);
        labelBox.getChildren().add(gameNumberLabel);
        labelBox.getChildren().add(opponentLabel);
        labelBox.getChildren().add(gameDateLabel);
        enterGameGrid.add(labelBox, 0, 0);

        VBox fieldBox = new VBox(10);
        fieldBox.getChildren().add(gameNumberField);
        fieldBox.getChildren().add(opponentField);
        fieldBox.getChildren().add(gameDatePicker);
        enterGameGrid.add(fieldBox, 1, 0);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> submitGameButtonClicked());
        // add a return button
        Button returnButton = new Button("Return");
        returnButton.setOnAction(event -> returnButtonClicked());

        HBox submitReturnBox = new HBox(10);
        submitReturnBox.getChildren().add(submitButton);
        submitReturnBox.getChildren().add(returnButton);
        enterGameGrid.add(submitReturnBox, 0, 1, 2, 1);

        Scene enterGameScene = new Scene(enterGameGrid, 400, 180);
        primaryStage.setScene(enterGameScene);
    }

    private void viewGameReportButtonClicked() {
        // Error handling: if no games are found, show an error message
        if (baseball_stats_db.getGames().isEmpty()) {
            // show error message if no games are found
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Cannot generate report");
            errorAlert.setContentText("Please add a game before viewing a report");
            errorAlert.showAndWait();
        } else {

            // Create a new GridPane for layout management
            GridPane viewGameReportGrid = new GridPane();
            viewGameReportGrid.setAlignment(Pos.TOP_LEFT);
            viewGameReportGrid.setPadding(new Insets(25, 25, 25, 25));
            viewGameReportGrid.setHgap(0);
            viewGameReportGrid.setVgap(5);

            // Set up the top section of the grid
            VBox topVBox = new VBox(20);
            HBox gameReportTopBox = new HBox(20);

            // Combo box setup
            ComboBox<String> gameComboBox = new ComboBox<>();
            Label gameComboBoxLabel = new Label("Game: ");
            for (Game game : baseball_stats_db.getGames()) {
                gameComboBox.getItems().add("Game " + game.getGameNumber() + " - " + game.getGameDate() + " - " + game.getGameOpponentName());
            }
            gameReportTopBox.getChildren().addAll(gameComboBoxLabel, gameComboBox);

            // Toggle buttons setup
            ToggleGroup shortLongReportToggle = new ToggleGroup();
            RadioButton shortReport = new RadioButton("Short Report");
            shortReport.setToggleGroup(shortLongReportToggle);
            shortReport.setSelected(true);
            RadioButton longReport = new RadioButton("Detailed Report");
            longReport.setToggleGroup(shortLongReportToggle);
            gameReportTopBox.getChildren().addAll(shortReport, longReport);

            topVBox.getChildren().add(gameReportTopBox);
            viewGameReportGrid.add(topVBox, 0, 0);

            // Return and create file buttons
            HBox bottomHBox = new HBox(5);
            Button returnButton = new Button("Return");
            returnButton.setOnAction(event -> returnButtonClicked());
            bottomHBox.getChildren().add(returnButton);

            Button createFileButton = new Button("Create File");
            createFileButton.setOnAction(event -> returnButtonClicked());
            createFileButton.setOnAction(event -> {
                // Create report file when button is clicked
                int selectedGameNumber = Integer.parseInt(gameComboBox.getValue().split(" ")[1]);
                try {
                    writeReportsToFile(selectedGameNumber);
                    // Optionally, notify the user that the file was successfully created
                    System.out.println("Report file created successfully!");
                } catch (IOException e) {
                    // Handle any exceptions while writing to the file
                    System.err.println("Error while creating report file: " + e.getMessage());
                }
            });

            bottomHBox.getChildren().add(createFileButton);
            createFileButton.setDisable(true); // inactivate until game selected

            viewGameReportGrid.add(bottomHBox, 0, 2);

            // Set up the stats section
            VBox statsVBox = new VBox(5);

            // Set up event handlers for radio buttons
            shortReport.setOnAction(e -> {
                if (!createFileButton.isDisabled()) {
                    gameComboBox.fireEvent(e);
                }
            });

            longReport.setOnAction(e -> {
                if (!createFileButton.isDisabled()) {
                    gameComboBox.fireEvent(e);
                }
            });

            // Set up event handler for combo box
            gameComboBox.setOnAction(e -> {

                createFileButton.setDisable(false); // activate create file button
                // Extract the game number from the selected item
                int selectedGameNumber = Integer.parseInt(gameComboBox.getValue().split(" ")[1]);
                statsVBox.getChildren().clear(); // Clear previous data in HBox
                HBox playerStatsHBox = new HBox(5); // VBox for displaying each player's stats vertically
                viewGameReportGrid.add(playerStatsHBox, 0, 3, 5, 1);
                playerStatsHBox.setStyle("-fx-border-color: black; -fx-padding: 5;"); // Style the VBox
                Font font = Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 14);
                Label label = new Label();
                label.setFont(font);

                if (shortReport.isSelected()) { // short report
                    for (String line : buildReportLines(shortGameReport, "", SPACE, selectedGameNumber, 0)) {
                        label = new Label(line);
                        label.setFont(font);
                        playerStatsHBox = new HBox(5); // VBox for displaying each player's stats vertically
                        playerStatsHBox.setStyle("-fx-border-color: black; -fx-padding: 5;"); // Style the VBox
                        playerStatsHBox.getChildren().add(label);
                        statsVBox.getChildren().add(playerStatsHBox);
                    }
                } else {
                    for (String line : buildReportLines(detailedGameReport, "", SPACE, selectedGameNumber, 0)) {
                        label = new Label(line);
                        label.setFont(font);
                        playerStatsHBox = new HBox(5); // VBox for displaying each player's stats vertically
                        playerStatsHBox.setStyle("-fx-border-color: black; -fx-padding: 5;"); // Style the VBox
                        playerStatsHBox.getChildren().add(label);
                        statsVBox.getChildren().add(playerStatsHBox);
                    }
                }
            });

            viewGameReportGrid.add(statsVBox, 0, 3);

            // Create a scene and set it to the primary stage
            Scene enterGameScene = new Scene(viewGameReportGrid, 1100, 800);
            primaryStage.setScene(enterGameScene);
        }
    }

    private void viewMultiGameReportButtonClicked() {
        // Error handling: if there are less than 2 games, show an error message
        if (baseball_stats_db.getGames().size() < 2) {
            // Show error message if there are less than 2 games
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Cannot generate report");
            errorAlert.setContentText("Please add at least 2 games before viewing a multi-game report.");
            errorAlert.showAndWait();
            return;
        }

        // Create a GridPane for layout management
        GridPane reportGrid = new GridPane();
        reportGrid.setAlignment(Pos.TOP_LEFT);
        reportGrid.setPadding(new Insets(25, 25, 25, 25));
        reportGrid.setHgap(10);
        reportGrid.setVgap(10);

        // Set up the start game combo box
        Label startGameLabel = new Label("Start Game:");
        ComboBox<String> startGameComboBox = new ComboBox<>();
        baseball_stats_db.getGames().forEach(game -> startGameComboBox.getItems().add("Game " + game.getGameNumber() + " - " + game.getGameDate() + " - " + game.getGameOpponentName()));

        // Set up the end game combo box
        Label endGameLabel = new Label("End Game:");
        ComboBox<String> endGameComboBox = new ComboBox<>();
        baseball_stats_db.getGames().forEach(game -> endGameComboBox.getItems().add("Game " + game.getGameNumber() + " - " + game.getGameDate() + " - " + game.getGameOpponentName()));

        // Add a listener to the startGameComboBox to update the endGameComboBox
        startGameComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            endGameComboBox.getItems().clear(); // Clear previous items
            if (newValue != null) {
                int startGameNumber = Integer.parseInt(newValue.split(" ")[1]);
                baseball_stats_db.getGames().stream()
                    .filter(game -> game.getGameNumber() > startGameNumber) // Ensure the end game is strictly after the start game
                    .forEach(game -> endGameComboBox.getItems().add("Game " + game.getGameNumber() + " - " + game.getGameDate() + " - " + game.getGameOpponentName()));
            }
        });
        
        // Set up buttons
        Button returnButton = new Button("Return");
        returnButton.setOnAction(event -> returnButtonClicked());
        Button viewReportButton = new Button("View Report");
        Button saveToFileButton = new Button("Save to File");

        // Set up the stats box
        VBox statsBox = new VBox(10);
        ScrollPane scrollPane = new ScrollPane(); // Create a ScrollPane
        scrollPane.setContent(statsBox); // Set the VBox inside the ScrollPane
        scrollPane.setFitToWidth(true); // Ensure the width fits to the content
        scrollPane.setPrefWidth(1100); // Set the preferred width of the ScrollPane to 1100 pixels

        

        // Set up event handlers for buttons
        viewReportButton.setOnAction(event -> {
            // Check if both start and end games are selected
            if (startGameComboBox.getValue() == null || endGameComboBox.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selection Error");
                alert.setHeaderText("Incomplete Selection");
                alert.setContentText("Please select both start and end games.");
                alert.showAndWait();
                return;
            }

            // Check if start game is before end game
            int startGameNumber = Integer.parseInt(startGameComboBox.getValue().split(" ")[1]);
            int endGameNumber = Integer.parseInt(endGameComboBox.getValue().split(" ")[1]);
            if (startGameNumber > endGameNumber) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Selection");
                alert.setHeaderText("Game Selection Error");
                alert.setContentText("Start game must be before end game.");
                alert.showAndWait();
                return;
            }

            // Clear the stats box
            statsBox.getChildren().clear();
            
            HBox playerStatsHBox = new HBox(5); // VBox for displaying each player's stats vertically
            reportGrid.add(playerStatsHBox, 0, 3, 5, 1);
            playerStatsHBox.setStyle("-fx-border-color: black; -fx-padding: 5;"); // Style the VBox
            Font font = Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 14);
            Label label = new Label();
            label.setFont(font);
            for (String line : buildReportLines(detailedMultiReport, "", SPACE, startGameNumber, endGameNumber)) {
                label = new Label(line);
                label.setFont(font);
                playerStatsHBox = new HBox(5); // VBox for displaying each player's stats vertically
                playerStatsHBox.setStyle("-fx-border-color: black; -fx-padding: 5;"); // Style the VBox
                playerStatsHBox.getChildren().add(label);
                statsBox.getChildren().add(playerStatsHBox);
            }

        });

        // Set up event handler for save to file button
        saveToFileButton.setOnAction(event -> {
            int startGameNumber = Integer.parseInt(startGameComboBox.getValue().split(" ")[1]);
            int endGameNumber = Integer.parseInt(endGameComboBox.getValue().split(" ")[1]);
            if (startGameNumber > endGameNumber) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Selection");
                alert.setHeaderText("Game Selection Error");
                alert.setContentText("Start game must be before end game.");
                alert.showAndWait();
                return;
            }

            try {
                writeMultiGameReportToFile(startGameNumber, endGameNumber);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText("File Created Successfully");
                successAlert.setContentText("The report has been saved to MultiGameReport_" + startGameNumber + "_to_" + endGameNumber + ".txt");
                successAlert.showAndWait();
            } catch (IOException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Failed to Save File");
                errorAlert.setContentText("An error occurred while saving the file: " + e.getMessage());
                errorAlert.showAndWait();
            }
        });

        // Set up the selection box
        VBox selectionBox = new VBox(10, startGameLabel, startGameComboBox, endGameLabel, endGameComboBox, viewReportButton, returnButton, saveToFileButton);

        // Add the selection box and scroll pane to the grid pane
        reportGrid.add(selectionBox, 0, 0);
        reportGrid.add(scrollPane, 0, 1);

        // Set up the scene and set it to the primary stage
        //Scene scene = new Scene(reportGrid, 800, 600);
        Scene scene = new Scene(reportGrid, 1100, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void writeReportsToFile(int gameNumber) throws IOException {
        // Find the game with the specified game number
        Game game = null;
        for (Game g : baseball_stats_db.getGames()) {
            if (g.getGameNumber() == gameNumber) {
                game = g;
                break;
            }
        }

        if (game == null) {
            System.out.println("Game not found!");
            return; // Exit if no game is found
        }

        // Detailed report file name, matching the ComboBox entry
        String gameLabel = "Game " + game.getGameNumber() + " - " + game.getGameDate() + " - " + game.getGameOpponentName();
        String detailedFileName = gameLabel + "_Detailed" + ".txt";
        // Simple report file name
        String simpleFileName = gameLabel + "_Simple" + ".txt";

        // Detailed report
        try (FileWriter detailedWriter = new FileWriter(detailedFileName, false)) {
            for (String line : buildReportLines(detailedGameReport, OSUBaseball + "\n" + gameLabel + "\n", TAB, game.getGameNumber(), 0)) {
                detailedWriter.write(line);
            }
        }

        // Simple report
        try (FileWriter simpleWriter = new FileWriter(simpleFileName, false)) {
            for (String line : buildReportLines(shortGameReport, OSUBaseball + "\n" + gameLabel + "\n", TAB, game.getGameNumber(), 0)) {
                simpleWriter.write(line);
            }
        }

    }

    private void writeMultiGameReportToFile(int startGameNumber, int endGameNumber) throws IOException {
        // Get the list of batters for the specified start and end game numbers
        //List<Batter> batters = baseball_stats_db.getSeasonPlayerStats(startGameNumber, endGameNumber);

        // Create a file name based on the start and end game numbers
        String fileName = "MultiGameReport_" + startGameNumber + "_to_" + endGameNumber + ".txt";

        // Try to create a FileWriter object to write to the file
        try (FileWriter writer = new FileWriter(fileName, false)) {
            for (String line : buildReportLines(detailedMultiReport, 
                    OSUBaseball + "\n" +"Multiple Games Report\nGames " + startGameNumber + " thru " + endGameNumber + "\n(sorted by batting avg)\n",
                    TAB, startGameNumber, endGameNumber)) {
                writer.write(line);
            }
        }
    }

    private List<String> buildReportLines(String typeReport, String reportName, String betweenColumnChar, int startGameNumber, int endGameNumber) {
        // this method build all the output lines for a game or multi-game report,
        // if game, the endGameNumber parameter is ignored
        String headerFormat, dataRowFormatBatters, dataRowFormatTeam = "";
        List<String> reportLines = new ArrayList<String>();
        List<Batter> batters = new ArrayList<>();
        Team team = new Team();
        // database calls
        if (typeReport.equals(shortGameReport) || typeReport.equals(detailedGameReport)) {
            batters = baseball_stats_db.getGamePlayerStats(startGameNumber);
            team = baseball_stats_db.getGameTeamStats(startGameNumber);
        } else {
            batters = baseball_stats_db.getSeasonPlayerStats(startGameNumber, endGameNumber);
            team = baseball_stats_db.getSeasonTeamStats(startGameNumber, endGameNumber);
        }
        if (!reportName.isEmpty()) {
            reportLines.add(reportName + "\n");
        }
        if (typeReport.equals(shortGameReport)) {
            headerFormat = "%-20s\t%3s\t%3s\t%3s\t%4s\t%3s\t%3s\t%4s\n";
            dataRowFormatBatters = "%-20s\t%3d\t%3d\t%3d\t%4d\t%3d\t%3d\t%4d\n";
            dataRowFormatTeam = "%20s\t%3d\t%3d\t%3d\t%4d\t%3d\t%3d\t%4d\n";
            if (!betweenColumnChar.equals(TAB)) {
                headerFormat = headerFormat.replace(TAB, betweenColumnChar);
                dataRowFormatBatters = dataRowFormatBatters.replace(TAB, betweenColumnChar);
                dataRowFormatTeam = dataRowFormatTeam.replace(TAB, betweenColumnChar);
            }
            String[] headers = {"Player", "AB", "R", "H", "RBI", "BB", "SO", "LOB"};
            reportLines.add(String.format(headerFormat, headers));
            for (Batter batter : batters) {
                reportLines.add(String.format(dataRowFormatBatters,
                        batter.getPlayerName(), batter.getBatterAB(), batter.getBatterRuns(),
                        batter.getBatterHits(), batter.getBatterRBI(), batter.getBatterBB(),
                        batter.getBatterSO(), batter.getBatterLOB()));
            }
            reportLines.add(String.format(dataRowFormatTeam, "Total ",
                    team.getTeamAB(), team.getTeamRuns(), team.getTeamHits(),
                    team.getTeamRBI(), team.getTeamBB(), team.getTeamSO(),
                    team.getTeamLOB()));
        } else { // detailed
            if (typeReport.equals(detailedMultiReport)) {
                if (batters.size() > 1) { // sort in reverse batter's average
                    Collections.sort(batters, (Batter b1, Batter b2) -> b2.getBatterAVGFormatted().compareTo(b1.getBatterAVGFormatted()));
                }
            }
            headerFormat = "%2s\t%-20s\t%5s\t%3s\t%3s\t%3s\t%3s\t%3s\t%3s\t%4s\t%3s\t%6s\t%3s\t%3s\t%3s\t%4s\t%6s\t%3s\t%3s\t%7s\t%4s\n";
            dataRowFormatBatters = "%2d\t%-20s\t%5s\t%3d\t%3d\t%3d\t%3d\t%3d\t%3d\t%4d\t%3d\t%6s\t%3d\t%3d\t%3d\t%4d\t%6s\t%3d\t%3d\t%7s\t%4d\n";
            dataRowFormatTeam = "%2s\t%20s\t%5s\t%3d\t%3d\t%3d\t%3d\t%3d\t%3d\t%4d\t%3d\t%6s\t%3d\t%3d\t%3d\t%4d\t%6s\t%3d\t%3d\t%7s\t%4d\n";
            if (!betweenColumnChar.equals(TAB)) {
                headerFormat = headerFormat.replaceAll(TAB, betweenColumnChar);
                dataRowFormatBatters = dataRowFormatBatters.replaceAll(TAB, betweenColumnChar);
                dataRowFormatTeam = dataRowFormatTeam.replaceAll(TAB, betweenColumnChar);
            }
            String[] headers = {
                "p#", "Player", "Avg", "AB", "R", "H", "2B", "3B", "HR", "RBI", "TB", "SLG%", "BB", "HP", "SO", "GDP", "OB%", "SF", "SH", "SB-Att", "LOB"};
            reportLines.add(String.format(headerFormat, headers));
            for (Batter batter : batters) {
                reportLines.add(String.format(dataRowFormatBatters,
                        batter.getPlayerNumber(), batter.getPlayerName(),
                        batter.getBatterAVGFormatted(), batter.getBatterAB(),
                        batter.getBatterRuns(), batter.getBatterHits(),
                        batter.getBatter2B(), batter.getBatter3B(),
                        batter.getBatterHR(), batter.getBatterRBI(),
                        batter.getBatterTB(), batter.getBatterSLGFormatted(),
                        batter.getBatterBB(), batter.getBatterHP(),
                        batter.getBatterSO(), batter.getBatterGDP(),
                        batter.getBatterOBFormatted(), batter.getBatterSF(),
                        batter.getBatterSH(), batter.getBatterSBSBAFormatted(),
                        batter.getBatterLOB()));
            }
            reportLines.add(String.format(dataRowFormatTeam, "", "Total ",
                    team.getTeamAVGFormatted(), team.getTeamAB(), team.getTeamRuns(),
                    team.getTeamHits(), team.getTeam2B(), team.getTeam3B(),
                    team.getTeamHR(), team.getTeamRBI(), team.getTeamTB(),
                    team.getTeamSLGFormatted(), team.getTeamBB(),
                    team.getTeamHP(), team.getTeamSO(), team.getTeamGDP(),
                    team.getTeamOBFormatted(), team.getTeamSF(), team.getTeamSH(),
                    team.getTeamSBSBAFormatted(), team.getTeamLOB()));
        }
        return reportLines;
    }

    // when the user has all data entered, write the data to the database
    private void submitButtonClicked() {

        String errorMsg = "";
        String notIntMsg = " is missing or not an integer\n";

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");

        if (gameComboBox.getItems().isEmpty()) {
            errorMsg += "There are no games to select from. Go back to main menu and add at least one.\n";
        } else {
            if (gameComboBox.getSelectionModel().isEmpty()) {
                errorMsg += "No game selection made.\n";
            }
        }
        if (playerComboBox.getItems().isEmpty()) {
            errorMsg += "There are no players to select from. Go back to main menu and add at least one.\n";
        } else {
            if (playerComboBox.getSelectionModel().isEmpty()) {
                errorMsg += "No player selection made.\n";
            }
        }

        if (errorMsg.length() > 0) { // must have game and player to move on         
            alert.setContentText(errorMsg);
            alert.showAndWait();
        } else { // game and player selection good, now check if number fields filled in

            if (!IntCheck.isInteger(battingOrderField.getText())) {
                errorMsg += "Batting Order #" + notIntMsg;
            }
            if (!IntCheck.isInteger(atBatField.getText())) {
                errorMsg += "At-Bats" + notIntMsg;
            }
            if (!IntCheck.isInteger(runField.getText())) {
                errorMsg += "Runs" + notIntMsg;
            }
            if (!IntCheck.isInteger(singleField.getText())) {
                errorMsg += "Singles" + notIntMsg;
            }
            if (!IntCheck.isInteger(doubleField.getText())) {
                errorMsg += "Doubles" + notIntMsg;
            }
            if (!IntCheck.isInteger(tripleField.getText())) {
                errorMsg += "Triples" + notIntMsg;
            }
            if (!IntCheck.isInteger(homeRunField.getText())) {
                errorMsg += "Home Runs" + notIntMsg;
            }
            if (!IntCheck.isInteger(basesOnBallField.getText())) {
                errorMsg += "Base-on-Balls" + notIntMsg;
            }
            if (!IntCheck.isInteger(runsBattedInField.getText())) {
                errorMsg += "Runs Batted In" + notIntMsg;
            }
            if (!IntCheck.isInteger(strikeOutField.getText())) {
                errorMsg += "Strike Outs" + notIntMsg;
            }
            if (!IntCheck.isInteger(groundedDoublePlayField.getText())) {
                errorMsg += "Grounded Double Plays" + notIntMsg;
            }
            if (!IntCheck.isInteger(stolenBaseAttemptField.getText())) {
                errorMsg += "Stolen Base Attempts" + notIntMsg;
            }
            if (!IntCheck.isInteger(stolenBaseSuccessField.getText())) {
                errorMsg += "Stolen Base Successes" + notIntMsg;
            }
            if (!IntCheck.isInteger(sacrificeFliesField.getText())) {
                errorMsg += "Sacrifice Flies" + notIntMsg;
            }
            if (!IntCheck.isInteger(sacrificeHitsField.getText())) {
                errorMsg += "Sacrifice Hits" + notIntMsg;
            }
            if (!IntCheck.isInteger(leftOnBaseField.getText())) {
                errorMsg += "Left on Base" + notIntMsg;
            }

            if (errorMsg.length() > 0) { // at least one numeric field empty or not integer          
                alert.setContentText(errorMsg);
                alert.showAndWait();
            } else { // data is good, get it, then do integrity checks

                // Retrieve the data entered in each field
                String gameInfo = gameComboBox.getSelectionModel().getSelectedItem();
                int game_number = Integer.parseInt(gameInfo.split(" ")[1]);
                String playerNameNumber = playerComboBox.getSelectionModel().getSelectedItem();
                int batter_pn = Integer.parseInt(playerNameNumber.split("#")[1]);
                int batter_gs = starterCheckBox.isSelected() ? 1 : 0; // convert boolean to int
                int batter_bo = Integer.parseInt(battingOrderField.getText());
                int batter_ab = Integer.parseInt(atBatField.getText());
                int batter_runs = Integer.parseInt(runField.getText());
                int batter_1b = Integer.parseInt(singleField.getText());
                int batter_2b = Integer.parseInt(doubleField.getText());
                int batter_3b = Integer.parseInt(tripleField.getText());
                int batter_hr = Integer.parseInt(homeRunField.getText());
                int batter_bb = Integer.parseInt(basesOnBallField.getText());
                int batter_hp = Integer.parseInt(hitsByPitchField.getText());
                int batter_rbi = Integer.parseInt(runsBattedInField.getText());
                int batter_so = Integer.parseInt(strikeOutField.getText());
                int batter_gdp = Integer.parseInt(groundedDoublePlayField.getText());
                int batter_sba = Integer.parseInt(stolenBaseAttemptField.getText());
                int batter_sb = Integer.parseInt(stolenBaseSuccessField.getText());
                int batter_sf = Integer.parseInt(sacrificeFliesField.getText());
                int batter_sh = Integer.parseInt(sacrificeHitsField.getText());
                int batter_lob = Integer.parseInt(leftOnBaseField.getText());

                // ensure the data entered is within proper ranges and follows logical guidelines
                if (baseball_stats_db.playerStatsExistForGame(game_number, batter_pn)) {
                    errorMsg += "Player stats already exist for the selected game.\n";
                }
                if (batter_ab < (batter_1b + batter_2b + batter_3b + batter_bb + batter_hp + batter_so + batter_gdp + batter_sf + batter_sh)) {
                    errorMsg += "The At-Bats field cannot be less than the sum of the players total hits, base-on-balls, "
                            + "hits-by-pitch, strikeouts, ground-out-dps, sacrifice flies, and sacrifice hits combined.\n";
                }
                if (batter_ab < (batter_runs + batter_lob)) {
                    errorMsg += "The At-Bats field cannot be less than the sum of the players runs and left-on-bases.\n";
                }
                if (batter_rbi > ((4 * batter_hr) + ((batter_1b + batter_2b + batter_3b) * 3) + (batter_bb + batter_hp + batter_sf + batter_sh))) {
                    errorMsg += "The RBI's are inconsistent with the number and types of hits, base-on-balls, hits-by-pitch, "
                            + "sacrifice flies, and sacrifice hits.\n";
                }
                if (batter_sb > batter_sba) {
                    errorMsg += "Stolen base successes cannot be higher than stolen base attempts.\n";
                }
                if (0 > batter_ab || batter_ab > 20) {
                    errorMsg += "The number entered in the At-Bats field must be between 0 and 20.\n";
                }
                if (batter_bo < 1 || batter_bo > 9) {
                    errorMsg += "Batting Order must be a number from 1 to 9.\n";
                }

                // if an error is found, display alert
                if (errorMsg.length() > 0) {
                    alert.setContentText(errorMsg);
                    alert.showAndWait();
                } else {

                    try {
                        // Insert record into 
                        baseball_stats_db.addGamePlayerStats(game_number, batter_pn,
                                batter_bo, batter_gs,
                                batter_ab, batter_runs, batter_1b, batter_2b,
                                batter_3b, batter_hr, batter_bb, batter_hp, batter_rbi,
                                batter_so, batter_gdp, batter_sba, batter_sb,
                                batter_sf, batter_sh, batter_lob);

                        // show success message
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Success");
                        successAlert.setHeaderText("Commit successful");
                        successAlert.setContentText("Data inserted successfully");
                        successAlert.showAndWait();

                    } catch (Exception e) {

                        // show error message
                        alert.setHeaderText("Commit failed");
                        alert.setContentText("There was an error in adding the data to the database. Please check your information and try again");
                        alert.showAndWait();

                    }

                    // clear fields
                    resetButtonClicked();
                }
            }

        }
    }

    // method to handle the submit button on the enter batter menu
    private void submitPlayerButtonClicked() {
        try {
            // ensure all fields are filled
            if (firstNameField.getText().isEmpty()
                    || lastNameField.getText().isEmpty()
                    || enterPlayerNumberField.getText().isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled.");
            }

            // Retrieve the data entered in each field
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            int playerNumber = Integer.parseInt(enterPlayerNumberField.getText());
            //boolean isActive = activeCheckBox.isSelected();

            if (playerNumber > 99 || playerNumber < 0) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setHeaderText("Submit failed");
                error.setContentText("Player number must be between 0 and 99");
                error.showAndWait();
            } else {
                if (baseball_stats_db.playerExists(playerNumber)) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText("Duplicate Player");
                    errorAlert.setContentText("Player " + playerNumber + " already exists in database");
                    errorAlert.showAndWait();
                } else { 
                    baseball_stats_db.addPlayer(firstName, lastName, playerNumber);
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText("Player added successfully");
                    successAlert.setContentText("Player " + firstName + " " + lastName + " added with player number " + playerNumber); // + " and is " + activeStatus);
                    successAlert.showAndWait();

                    // Clear fields
                    resetButtonClicked();
                }
            }
        } catch (NumberFormatException e) {
            // show error message for number format exception
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Player addition failed");
            errorAlert.setContentText("Please enter a valid numeric value for player number.");
            errorAlert.showAndWait();
        } catch (IllegalArgumentException e) {
            // show error message for empty fields
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Player addition failed");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    private void submitGameButtonClicked() {
        try {
            // ensure all fields have a valid value
            if (gameNumberField.getText().isEmpty() || opponentField.getText().isEmpty() || gameDatePicker.getValue() == null) {
                throw new IllegalArgumentException("All fields must be filled.");
            }

            int gameNumber = Integer.parseInt(gameNumberField.getText());
            String opponent = opponentField.getText();
            LocalDate gameDate = gameDatePicker.getValue();

            // if the value is entered manually, parse the date
            if (gameDatePicker.getValue() != null) {
                gameDate = gameDatePicker.getValue();
            } else {
                String dateString = gameDatePicker.getEditor().getText();
                gameDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("M/d/yyyy"));
            }
            
            if (baseball_stats_db.gameExists(gameNumber)) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Duplicate Game");
                errorAlert.setContentText("Game " + gameNumber + " already exists in database");
                errorAlert.showAndWait();
            } else { 

                baseball_stats_db.addGame(gameNumber, opponent, String.valueOf(gameDate));

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText("Game added successfully");
                successAlert.setContentText("Game " + gameNumber + " added with opponent " + opponent + " and date " + gameDate);
                successAlert.showAndWait();
                         
                // Clear fields
                resetButtonClicked();      
            }

        } catch (NumberFormatException e) {
            // show error message for number format exception
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Game addition failed");
            errorAlert.setContentText("Please enter a valid numeric value for game number.");
            errorAlert.showAndWait();
        } catch (IllegalArgumentException e) {
            // show error message for empty fields
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Game addition failed");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        } catch (DateTimeParseException e) {
            // handle invalid date format
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Game addition failed");
            errorAlert.setContentText("Please enter the date in the format M/D/YYYY.");
            errorAlert.showAndWait();
        }

    }

    // function to return back to the main menu
    private void returnButtonClicked() {
        // navigate back to the main menu
        start(primaryStage);
    }

    // function to reset all data entry boxes
    private void resetButtonClicked() {
        // reset all data input, except gameComboBox
        playerComboBox.getSelectionModel().clearSelection();
        starterCheckBox.setSelected(false);
        battingOrderField.setText("0");
        atBatField.setText("0");
        runField.setText("0");
        singleField.setText("0");
        doubleField.setText("0");
        tripleField.setText("0");
        homeRunField.setText("0");
        basesOnBallField.setText("0");
        hitsByPitchField.setText("0");
        runsBattedInField.setText("0");
        strikeOutField.setText("0");
        groundedDoublePlayField.setText("0");
        stolenBaseAttemptField.setText("0");
        stolenBaseSuccessField.setText("0");
        sacrificeFliesField.setText("0");
        sacrificeHitsField.setText("0");
        leftOnBaseField.setText("0");

        // reset data input for enter player menu
        firstNameField.setText("");
        lastNameField.setText("");
        enterPlayerNumberField.setText("");
        activeCheckBox.setSelected(false);

        // reset data input for enter game menu
        gameNumberField.setText("");
        opponentField.setText("");
        gameDatePicker.setValue(null);
    }

    // function to end the program
    private void exitButtonClicked() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }

}
