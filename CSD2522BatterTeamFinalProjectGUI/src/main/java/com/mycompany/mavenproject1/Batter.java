/*
    Created by:  xxx
    Created on:  05/10/2024
    Purpose:     Batter methods
    Description: This method stores all the info related to a batters.

Terry Pescosolido - 4/30/24 - added methods for multiple game / season stats
*/

package com.mycompany.mavenproject1;

import java.text.NumberFormat;

public class Batter {

    private String player_name;
    private int player_number;
    private int batter_order_number;
    private int batter_gp;
    private int batter_gs;
    private int batter_ab;
    private int batter_runs;
    private int batter_1b;
    private int batter_2b;
    private int batter_3b;
    private int batter_hr;
    private int batter_bb;
    private int batter_hp;
    private int batter_rbi;
    private int batter_so;
    private int batter_gdp;
    private int batter_sba;
    private int batter_sb;
    private int batter_sf;
    private int batter_sh;
    private int batter_lob;
    
    public Batter() {
        this("", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }
    
    public Batter(String player_name, int player_number) {
        this(player_name, player_number, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Batter(String player_name, int player_number, int batter_order_number, 
            int batter_gp, int batter_gs,
            int batter_ab, int batter_runs, int batter_1b, 
            int batter_2b, int batter_3b, int batter_hr,
            int batter_bb, int batter_hp, int batter_rbi, 
            int batter_so, int batter_gdp, int batter_sba, int batter_sb,
            int batter_sf, int batter_sh, int batter_lob) {
        this.player_name = player_name;
        this.player_number = player_number;
        this.batter_order_number = batter_order_number;
        this.batter_gp = batter_gp;
        this.batter_gs = batter_gs;
        this.batter_ab = batter_ab;
        this.batter_runs = batter_runs;
        this.batter_1b = batter_1b;
        this.batter_2b = batter_2b;
        this.batter_3b = batter_3b;
        this.batter_hr = batter_hr;
        this.batter_bb = batter_bb;
        this.batter_hp = batter_hp;
        this.batter_rbi = batter_rbi;
        this.batter_so = batter_so;
        this.batter_gdp = batter_gdp;
        this.batter_sba = batter_sba;
        this.batter_sb = batter_sb;
        this.batter_sf = batter_sf;
        this.batter_sh = batter_sh;
        this.batter_lob = batter_lob;
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
    
    public void setBatterOrderNumber(int batter_order_number) {
        this.batter_order_number = batter_order_number;
    }
    
    public int getBatterOrderNumber() {
        return batter_order_number;
    }
   
    public void setBatterGP(int batter_gp) {
        this.batter_gp = batter_gp;
    }
    
    public int getBatterGP() {
        return batter_gp;
    }
    
    public void setBatterGS(int batter_gs) {
        this.batter_gs = batter_gs;
    }
    
    public int getBatterGS() {
        return batter_gs;
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
    
    public int getBatterHits() {
        return getBatter1B() + getBatter2B() + getBatter3B() + getBatterHR();
    }
    
    public void setBatterBB(int batter_bb) {
        this.batter_bb = batter_bb;
    }
    
    public int getBatterBB() {
        return batter_bb;
    }
    
    public void setBatterHP(int batter_hp) {
        this.batter_hp = batter_hp;
    }
    
    public int getBatterHP() {
        return batter_hp;
    }
     
    public void setBatterRBI(int batter_rbi) {
        this.batter_rbi = batter_rbi;
    }
    
    public int getBatterRBI() {
        return batter_rbi;
    }
     
    public void setBatterSO(int batter_so) {
        this.batter_so = batter_so;
    }
    
    public int getBatterSO() {
        return batter_so;
    }
     
    public void setBatterGDP(int batter_gdp) {
        this.batter_gdp = batter_gdp;
    }
    
    public int getBatterGDP() {
        return batter_gdp;
    }
 
    public void setBatterSBA(int batter_sba) {
        this.batter_sba = batter_sba;
    }
   
    public int getBatterSBA() {
        return batter_sba;
    }
    
    public void setBatterSB(int batter_sb) {
        this.batter_sb = batter_sb;
    }
   
    public int getBatterSB() {
        return batter_sb;
    }
    
    public void setBatterSF(int batter_sf) {
        this.batter_sf = batter_sf;
    }
   
    public int getBatterSF() {
        return batter_sf;
    }
   
    public void setBatterSH(int batter_sh) {
        this.batter_sh = batter_sh;
    }
   
    public int getBatterSH() {
        return batter_sh;
    }
    
    public void setBatterLOB(int batter_lob) {
        this.batter_lob = batter_lob;
    }
   
    public int getBatterLOB() {
        return batter_lob;
    }
    
    // stats for a group or set of games
    
    public int getBatterTB() {
        return getBatter1B() + (getBatter2B() * 2) +
               (getBatter3B() * 3) + (getBatterHR() * 4);   
    } 
    
    public String getBatterGPGSFormatted() {
        // format as "xxx-xxx"?
        return getBatterGP() + "-" + getBatterGS();
    }
    
    public String getBatterSBSBAFormatted() {
        // format as "xxx-xxx"
        return getBatterSB() + "-" + getBatterSBA();
    }
    
    public String getBatterAVGFormatted() {
        if (getBatterAB() == 0) {
            return " ---"; // if no at-bats, cannot compute
        } else {
            double avg = (double) getBatterHits() / (double) getBatterAB();  
            return formatPercentage(avg);
        }
    }
    
    public String getBatterSLGFormatted() {
        // format as ".xxx", can be x.xxx!
        if (getBatterTB() == 0) {
            return " ---"; // no slg if no bases (or should this be ".000"
        } else {
            double slg = (double) getBatterTB() / (double) getBatterAB();  
            return formatPercentage(slg);
        }
    }
    
    public String getBatterOBFormatted() {
        // format as ".xxx"
        // OBP = (Hits + Walks + Hit-By-Pitch) / (At Bats + Walks + Hit By Pitch + Sacrifice Flies)
        int divisor = getBatterAB() + getBatterBB() + getBatterHP() + getBatterHP();
        if (divisor == 0) {
            return " ---"; // no slg if no bases (or should this be ".000"?)
        } else {
            double ob = (double) (getBatterHits() + getBatterBB() + getBatterHP()) / (double) divisor;
            return formatPercentage(ob); // format batterTotalOnBase() 
        }
    }
    
    public String formatPercentage(double number) {
        
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumFractionDigits(3);
        formatter.setMinimumFractionDigits(3);
        String formattedNumber = formatter.format((double) Math.round(number * 1000) / 1000); 
        String[] parsedFormattedNumber = formattedNumber.split("\\.");
        if (parsedFormattedNumber[0].equals("0")) { // number is lees than 1
            return "." + parsedFormattedNumber[1]; // don't return "0" whole number
        } else { // number is more than 0, return whole formatted number
            return formattedNumber;
        }
         
    }  
    
}
