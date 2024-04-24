/*
Authors: Luke Dawson, Terry Pescosolido, Cedrick Charles, Gavin Mefford-Gibbins, Wesley Morah
Date created: 4/24/24
Purpose: create a GUI to accept input from a user and write it into a Sqlite database
Updated by:
Luke Dawson - 4/24/24 - created the skeleton for the GUI
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


/**
 * JavaFX App
 */
public class App extends Application {
    // create labels
    private Label gameNumberLabel = new Label("Game #");
    private Label playerNumberLabel = new Label("Player #");
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
    private TextField gameNumberField = new TextField();;
    private TextField playerNumberField = new TextField();;
    private TextField battingOrderField = new TextField();
    private TextField atBatField = new TextField();
    private TextField runField = new TextField();
    private TextField singleField = new TextField();
    private TextField doubleField = new TextField();
    private TextField tripleField = new TextField();
    private TextField homeRunField = new TextField();
    private TextField basesOnBallField= new TextField();
    private TextField hitsByPitchField = new TextField();
    private TextField runsBattedInField = new TextField();
    private TextField strikeOutField = new TextField();
    private TextField groundedDoublePlayField = new TextField();
    private TextField stolenBaseAttemptField = new TextField();
    private TextField stolenBaseSuccessField = new TextField();
    private TextField sacrificeFliesField = new TextField();
    private TextField leftOnBaseField = new TextField();
    
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
        Button enterDataButton = new Button("Enter Data");
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
        // then navigate back to the main menu
        start(primaryStage);
    }
    
    // function to return back to the main menu
    private void returnButtonClicked() {
        // navigate back to the main menu
        start(primaryStage);
    }
    
    // function to reset all data entry boxes
    private void resetButtonClicked() {
        // reset all data input
        gameNumberField.clear();
        playerNumberField.clear();
        battingOrderField.clear();
        atBatField.clear();
        runField.clear();
        singleField.clear();
        doubleField.clear();
        tripleField.clear();
        homeRunField.clear();
        basesOnBallField.clear();
        hitsByPitchField.clear();
        runsBattedInField.clear();
        strikeOutField.clear();
        groundedDoublePlayField.clear();
        stolenBaseAttemptField.clear();
        stolenBaseSuccessField.clear();
        sacrificeFliesField.clear();
        leftOnBaseField.clear();
    }
    
    // function to end the program
    private void exitButtonClicked() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }

}

