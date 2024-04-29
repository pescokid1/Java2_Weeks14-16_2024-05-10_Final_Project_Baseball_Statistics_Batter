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
import java.util.List;


/**
 * JavaFX App
 */
public class App extends Application {
    // create labels
    private Label gameNumberLabel = new Label("Game #");
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
    private Label leftOnBaseLabel = new Label("Left on Base:");
    private Label firstNameLabel = new Label("First Name:");
    private Label lastNameLabel = new Label("Last Name:");
    private Label enterPlayerNumberLabel = new Label("Player Number:");
    private Label activeLabel = new Label("Active");
    
    // create textfields
    private TextField gameNumberField = new TextField("0");
    ComboBox<String> playerComboBox = new ComboBox<>();
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
    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField enterPlayerNumberField = new TextField();
    private CheckBox activeCheckBox = new CheckBox("");
    
    BaseballStatsDB baseball_stats_db = new BaseballStatsDB();
    List<Batter> batters = null; 
    private List<Player> players = new ArrayList<>();
    
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
        Scene scene = new Scene(grid, 250, 230);

        // create buttons for different actions
        Button enterDataButton = new Button("Enter Batter Stats");
        Button enterPlayerButton = new Button("Enter New Batter");
        Button viewGameReportButton = new Button("View Game Report");
        Button viewMultiGameReportButton = new Button("View Multi-Game Report");
        
        // VBox to hold the different options the user can choose from
        VBox optionBox = new VBox(10);
        optionBox.getChildren().add(enterDataButton);
        optionBox.getChildren().add(enterPlayerButton);
        optionBox.getChildren().add(viewGameReportButton);
        optionBox.getChildren().add(viewMultiGameReportButton); 
        grid.add(optionBox, 0, 0, 3, 1);
        
        enterDataButton.setOnAction(event -> enterDataButtonClicked());
        enterPlayerButton.setOnAction(event -> enterBatterButtonClicked());
        
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
    
//    // method to connect to the sql database
//    private Connection getConnection() throws SQLException {
//        String dbUrl = "jdbc:sqlite:baseball_batter_stats.sqlite"; // enter file name here
//        Connection connection = DriverManager.getConnection(dbUrl);
//        return connection;
//    }
    
    // method for when the user selects the "Enter Data" button
    private void enterDataButtonClicked() {
        // create a new scene for entering data
        GridPane enterDataGrid = new GridPane();
        enterDataGrid.setAlignment(Pos.TOP_LEFT);
        enterDataGrid.setPadding(new Insets(25, 25, 25, 25));
        enterDataGrid.setHgap(10);
        enterDataGrid.setVgap(10);
        
        for (Player player : players) {
            playerComboBox.getItems().add(player.getPlayerName()); // Add player names to ComboBox
        }
        enterDataGrid.add(playerComboBox, 1, 1, 1, 1);
        
        VBox labelBox1 = new VBox(18);
        labelBox1.getChildren().add(gameNumberLabel);
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
        labelBox2.getChildren().add(leftOnBaseLabel);
        enterDataGrid.add(labelBox2, 2, 0);
        
        VBox textFieldBox1 = new VBox(10);
        textFieldBox1.getChildren().add(gameNumberField);
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
        labelBox.getChildren().add(activeLabel);
        enterDataGrid.add(labelBox, 0, 0);
        
        VBox textBox = new VBox(10);
        textBox.getChildren().add(firstNameField);
        textBox.getChildren().add(lastNameField);
        textBox.getChildren().add(enterPlayerNumberField);
        textBox.getChildren().add(activeCheckBox);
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
    
    // when the user has all data entered, write the data to the database
    private void submitButtonClicked() {
//        try {
            // Retrieve the data entered in each field
            int gameNumber = Integer.parseInt(gameNumberField.getText());
            // add code to get values from the combo box
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
            int batter_sh = Integer.parseInt(sacrificeFliesField.getText());
            int batter_lob = Integer.parseInt(leftOnBaseField.getText());
            
            // Insert record into 
            //try (
                baseball_stats_db.addGamePlayerStats(gameNumber, batter_pn, 
                              batter_bo, batter_gs,
                              batter_ab, batter_runs, batter_1b, batter_2b, 
                              batter_3b, batter_hr, batter_bb, batter_hp, batter_rbi,
                              batter_so, batter_gdp, batter_sb, 
                              batter_sf, batter_sh, batter_lob);

                // show success message
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText("Commit successful");
                successAlert.setContentText("Data inserted successfully");
                successAlert.showAndWait();

                // clear fields
                resetButtonClicked();
//            }
//        } catch (NumberFormatException | SQLException e) {
//            // show error message
//            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//            errorAlert.setTitle("Error");
//            errorAlert.setHeaderText("Commit failed");
//            errorAlert.setContentText("There was an error in adding the data to the database. Please check your information and try again");
//            errorAlert.showAndWait();
//        }
    }
    
    // method to handle the submit button on the enter batter menu
    private void submitPlayerButtonClicked() {
        // Retrieve the data entered in each field
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int playerNumber = Integer.parseInt(enterPlayerNumberField.getText());
        boolean isActive = activeCheckBox.isSelected();

        // Create a new Player object with the entered data
        Player player = new Player(playerNumber, firstName + " " + lastName, isActive);

        // Add the new player to the list
        players.add(player);

        // Show success message
        String activeStatus = isActive ? "active" : "inactive";
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText("Player added successfully");
        successAlert.setContentText("Player " + firstName + " " + lastName + " added with player number " + playerNumber + " and is " + activeStatus);
        successAlert.showAndWait();

        // Clear fields
        resetButtonClicked();
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
        leftOnBaseField.setText("0");
        
        // reset data input for enter player menu
        firstNameField.setText("");
        lastNameField.setText("");
        enterPlayerNumberField.setText("");
        activeCheckBox.setSelected(false);
    }
    
    // function to end the program
    private void exitButtonClicked() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }

}
