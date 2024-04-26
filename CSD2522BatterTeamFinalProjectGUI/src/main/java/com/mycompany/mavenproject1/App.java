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
import java.sql.*;


/**
 * JavaFX App
 */
public class App extends Application {
    // create labels
    private Label gameNumberLabel = new Label("Game #");
    private Label playerNumberLabel = new Label("Player #");
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
    private Label leftOnBaseLabel = new Label("Left on Base:");
    
    // create textfields
    private TextField gameNumberField = new TextField("0");
    private TextField playerNumberField = new TextField("0");
    private CheckBox starterCheckBox = new CheckBox("");
    private TextField battingOrderField = new TextField("0");
    private TextField atBatField = new TextField("0");
    private TextField runField = new TextField("0");
    private TextField singleField = new TextField("0");
    private TextField doubleField = new TextField("0");
    private TextField tripleField = new TextField("0");
    private TextField homeRunField = new TextField("0");
    private TextField basesOnBallField= new TextField("0");
    private TextField hitsByPitchField = new TextField("0");
    private TextField runsBattedInField = new TextField("0");
    private TextField strikeOutField = new TextField("0");
    private TextField groundedDoublePlayField = new TextField("0");
    private TextField stolenBaseAttemptField = new TextField("0");
    private TextField stolenBaseSuccessField = new TextField("0");
    private TextField sacrificeFliesField = new TextField("0");
    private TextField leftOnBaseField = new TextField("0");
    
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
        Scene scene = new Scene(grid);

        // create buttons for different actions
        Button enterDataButton = new Button("Enter Batter Stats");
        Button viewGameReportButton = new Button("View Game Report");
        Button viewMultiGameReportButton = new Button("View Multi-Game Report");
        
        // VBox to hold the different options the user can choose from
        VBox optionBox = new VBox(10);
        optionBox.getChildren().add(enterDataButton);
        optionBox.getChildren().add(viewGameReportButton);
        optionBox.getChildren().add(viewMultiGameReportButton); 
        grid.add(optionBox, 0, 0, 3, 1);
        
        enterDataButton.setOnAction(event -> enterDataButtonClicked());
        
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
    
    // method to connect to the sql database
    private Connection getConnection() throws SQLException {
        String dbUrl = "jdbc:sqlite:baseball_batter_stats.sqlite"; // enter file name here
        Connection connection = DriverManager.getConnection(dbUrl);
        return connection;
    }
    
    // method for when the user selects the "Enter Data" button
    private void enterDataButtonClicked() {
        // create a new scene for entering data
        GridPane enterDataGrid = new GridPane();
        enterDataGrid.setAlignment(Pos.TOP_LEFT);
        enterDataGrid.setPadding(new Insets(25, 25, 25, 25));
        enterDataGrid.setHgap(10);
        enterDataGrid.setVgap(10);
        
        VBox labelBox = new VBox(18);
        labelBox.getChildren().add(gameNumberLabel);
        labelBox.getChildren().add(playerNumberLabel);
        labelBox.getChildren().add(starterLabel);
        labelBox.getChildren().add(battingOrderLabel);
        labelBox.getChildren().add(atBatLabel);
        labelBox.getChildren().add(runLabel);
        labelBox.getChildren().add(singleLabel);
        labelBox.getChildren().add(doubleLabel);
        labelBox.getChildren().add(tripleLabel);
        labelBox.getChildren().add(homeRunLabel);
        labelBox.getChildren().add(basesOnBallLabel);
        labelBox.getChildren().add(hitsByPitchLabel);
        labelBox.getChildren().add(runsBattedInLabel);
        labelBox.getChildren().add(strikeOutLabel);
        labelBox.getChildren().add(groundedDoublePlayLabel);
        labelBox.getChildren().add(stolenBaseAttemptLabel);
        labelBox.getChildren().add(stolenBaseSuccessLabel);
        labelBox.getChildren().add(sacrificeFliesLabel);
        labelBox.getChildren().add(leftOnBaseLabel);
        enterDataGrid.add(labelBox, 0, 0);
        
        VBox textFieldBox = new VBox(10);
        textFieldBox.getChildren().add(gameNumberField);
        textFieldBox.getChildren().add(playerNumberField);
        textFieldBox.getChildren().add(starterCheckBox);
        textFieldBox.getChildren().add(battingOrderField);
        textFieldBox.getChildren().add(atBatField);
        textFieldBox.getChildren().add(runField);
        textFieldBox.getChildren().add(singleField);
        textFieldBox.getChildren().add(doubleField);
        textFieldBox.getChildren().add(tripleField);
        textFieldBox.getChildren().add(homeRunField);
        textFieldBox.getChildren().add(basesOnBallField);
        textFieldBox.getChildren().add(hitsByPitchField);
        textFieldBox.getChildren().add(runsBattedInField);
        textFieldBox.getChildren().add(strikeOutField);
        textFieldBox.getChildren().add(groundedDoublePlayField);
        textFieldBox.getChildren().add(stolenBaseAttemptField);
        textFieldBox.getChildren().add(stolenBaseSuccessField);
        textFieldBox.getChildren().add(sacrificeFliesField);
        textFieldBox.getChildren().add(leftOnBaseField);
        enterDataGrid.add(textFieldBox, 1, 0);

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

        // aet the new scene
        Scene enterDataScene = new Scene(enterDataGrid);
        primaryStage.setScene(enterDataScene);
    }
    
    // when the user has all data entered, write the data to the database
    private void submitButtonClicked() {
        try {
            // Retrieve the data entered in each field
            int gameNumber = Integer.parseInt(gameNumberField.getText());
            int playerNumber = Integer.parseInt(playerNumberField.getText());
            boolean starterStatus = starterCheckBox.isSelected();
            int battingOrder = Integer.parseInt(battingOrderField.getText());
            int atBat = Integer.parseInt(atBatField.getText());
            int run = Integer.parseInt(runField.getText());
            int single = Integer.parseInt(singleField.getText());
            int doubleCount = Integer.parseInt(doubleField.getText()); // "double" is a reserved keyword, so use "doubleCount" instead
            int triple = Integer.parseInt(tripleField.getText());
            int homeRun = Integer.parseInt(homeRunField.getText());
            int basesOnBall = Integer.parseInt(basesOnBallField.getText());
            int hitsByPitch = Integer.parseInt(hitsByPitchField.getText());
            int runsBattedIn = Integer.parseInt(runsBattedInField.getText());
            int strikeOut = Integer.parseInt(strikeOutField.getText());
            int groundedDoublePlay = Integer.parseInt(groundedDoublePlayField.getText());
            int stolenBaseAttempt = Integer.parseInt(stolenBaseAttemptField.getText());
            int stolenBaseSuccess = Integer.parseInt(stolenBaseSuccessField.getText());
            int sacrificeFlies = Integer.parseInt(sacrificeFliesField.getText());
            int leftOnBase = Integer.parseInt(leftOnBaseField.getText());

            // SQL INSERT statement
            String sql = "INSERT INTO ENTERTABLENAME (game_number, player_number, starter, batting_order, at_bat, run, single, _double, triple, home_run, bases_on_ball, hits_by_pitch, runs_batted_in, strike_out, grounded_double_play, stolen_base_attempt, stolen_base_success, sacrifice_flies, left_on_base) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Get connection
            try (Connection connection = getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql)) {
                // Set parameters
                ps.setInt(1, gameNumber);
                ps.setInt(2, playerNumber);
                ps.setBoolean(3, starterStatus);
                ps.setInt(4, battingOrder);
                ps.setInt(5, atBat);
                ps.setInt(6, run);
                ps.setInt(7, single);
                ps.setInt(8, doubleCount);
                ps.setInt(9, triple);
                ps.setInt(10, homeRun);
                ps.setInt(11, basesOnBall);
                ps.setInt(12, hitsByPitch);
                ps.setInt(13, runsBattedIn);
                ps.setInt(14, strikeOut);
                ps.setInt(15, groundedDoublePlay);
                ps.setInt(16, stolenBaseAttempt);
                ps.setInt(17, stolenBaseSuccess);
                ps.setInt(18, sacrificeFlies);
                ps.setInt(19, leftOnBase);

                // execute the INSERT statement
                ps.executeUpdate();

                // show success message
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText("Commit successful");
                successAlert.setContentText("Data inserted successfully");
                successAlert.showAndWait();

                // clear fields
                resetButtonClicked();
            }
        } catch (NumberFormatException | SQLException e) {
            // show error message
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Commit failed");
            errorAlert.setContentText("There was an error in adding the data to the database. Please check your information and try again");
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
        // reset all data input
        gameNumberField.setText("0");
        playerNumberField.setText("0");
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
        leftOnBaseField.setText("0");
    }
    
    // function to end the program
    private void exitButtonClicked() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }

}
