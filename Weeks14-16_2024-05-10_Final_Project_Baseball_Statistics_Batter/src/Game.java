/*
    Created by:  xxx
    Created on:  05/10/2024
    Purpose:     Game method
    Description: This method stores all the info related to games.

Terry Pescosolido - 4/30/24 - created
*/

//package com.mycompany.mavenproject1;

public class Game {

    private int game_number;
    private String game_opponent_name;
    private String game_date;
    
    public Game() {
        this(0, "", "");
    }

    public Game(int game_number, String game_opponent_name, String game_date) { 
        this.game_number = game_number;
        this.game_opponent_name = game_opponent_name;
        this.game_date = game_date;
    }
    
    public void setGameNumber(int game_number) {
        this.game_number = game_number;
    }
    
    public int getGameNumber() {
        return game_number;
    }

    public void setGameOpponentName(String game_opponent_name) {
        this.game_opponent_name = game_opponent_name;
    }
    
    public String getGameOpponentName() {
        return game_opponent_name;
    }
 
    public void setGameActive(String game_date) {
        this.game_date = game_date;
    }
        
    public String getGameDate() {
        return game_date;
    }
   
}

