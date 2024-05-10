/*
    Authors: Luke Dawson, Terry Pescosolido, Cedrick Charles, Gavin Mefford-Gibbins, Wesley Morah
    Date created: 4/24/24
    Purpose:     Player method
    Description: This method stores all the info related to players.
*/

package com.mycompany.mavenproject1;

public class Player {

    private int player_number;
    private String player_name;
    private boolean player_active;
    
    public Player() {
        this(0, "", true);
    }

    public Player(int player_number, String player_name, boolean player_active) { 
        this.player_number = player_number;
        this.player_name = player_name;
        this.player_active = player_active;
    }
    
    public void setPlayerNumber(int player_number) {
        this.player_number = player_number;
    }
    
    public int getPlayerNumber() {
        return player_number;
    }

    public void setPlayerName(String player_name) {
        this.player_name = player_name;
    }
    
    public String getPlayerName() {
        return player_name;
    }
    
    public void setPlayerActive(boolean player_active) {
        this.player_active = player_active;
    }
    
    public boolean getPlayerActive() {
        return player_active;
    }

}
