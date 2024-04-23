/*
    Created by:  xxx
    Created on:  05/10/2024
    Purpose:     Batter method
    Description: This method stores all the info related to a batters.
*/

public class Batter {

    private int player_number;
    //private String player_name;
    private int batter_order_number;
    private int batter_ab;
    private int batter_runs;
    private int batter_1b;
    private int batter_2b;
    private int batter_3b;
    private int batter_hr;

    public Batter() {
        this(0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Batter(int player_number, int batter_order_number, 
            int batter_ab, int batter_runs, int batter_1b, 
            int batter_2b, int batter_3b, int batter_hr) {
        this.player_number = player_number;
        this.batter_order_number = batter_order_number;
        this.batter_ab = batter_ab;
        this.batter_runs = batter_runs;
        this.batter_1b = batter_1b;
        this.batter_2b = batter_2b;
        this.batter_3b = batter_3b;
        this.batter_hr = batter_hr;
    }

    public void setPlayerNumber(int player_number) {
        this.player_number = player_number;
    }
    
    public int getPlayerNumber() {
        return player_number;
    }

//    public void setPlayerName(String player_name) {
//        this.player_name = player_name;
//    }
//    
//    public String getPlayerName() {
//        return player_name;
//    }
    
    public void setBatterOrderNumber(int batter_order_number) {
        this.batter_order_number = batter_order_number;
    }
    
    public int getBatterOrderNumber() {
        return batter_order_number;
    }
   
    public void setBatterAB(int batter_ab) {
        this.batter_ab = batter_ab;
    }
    
    public int getBatterAB() {
        return batter_ab;
    }
    
    public void setBatterRuns(int batter_runs) {
        this.batter_runs = batter_runs;
    }
    
    public int getBatterRuns() {
        return batter_runs;
    }
    
    public void setBatter1B(int batter_1b) {
        this.batter_1b = batter_1b;
    }
    
    public int getBatter1B() {
        return batter_1b;
    }
    
    public void setBatter2B(int batter_2b) {
        this.batter_2b = batter_2b;
    }
    
    public int getBatter2B() {
        return batter_2b;
    }
    
    public void setBatter3B(int batter_3b) {
        this.batter_3b = batter_3b;
    }
    
    public int getBatter3B() {
        return batter_3b;
    }
    
    public void setBatterHR(int batter_hr) {
        this.batter_hr = batter_hr;
    }
    
    public int getBatterHR() {
        return batter_hr;
    }

}
