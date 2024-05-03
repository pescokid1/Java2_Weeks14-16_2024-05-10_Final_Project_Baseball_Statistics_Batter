/*
    Created by:  xxx
    Created on:  05/3/2024
    Purpose:     Team methods
    Description: This method stores all the info related to a team..

Terry Pescosolido - 5/3/24 - created
*/

//package com.mycompany.mavenproject1;

public class Team {

    private int team_ab;
    private int team_runs;
    private int team_1b;
    private int team_2b;
    private int team_3b;
    private int team_hr;
    private int team_bb;
    private int team_hp;
    private int team_rbi;
    private int team_so;
    private int team_gdp;
    private int team_sba;
    private int team_sb;
    private int team_sf;
    private int team_sh;
    private int team_lob;
    
    public Team() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public Team(
            int team_ab, int team_runs, int team_1b, 
            int team_2b, int team_3b, int team_hr,
            int team_bb, int team_hp, int team_rbi, 
            int team_so, int team_gdp, int team_sba, int team_sb,
            int team_sf, int team_sh, int team_lob) {

        this.team_ab = team_ab;
        this.team_runs = team_runs;
        this.team_1b = team_1b;
        this.team_2b = team_2b;
        this.team_3b = team_3b;
        this.team_hr = team_hr;
        this.team_bb = team_bb;
        this.team_hp = team_hp;
        this.team_rbi = team_rbi;
        this.team_so = team_so;
        this.team_gdp = team_gdp;
        this.team_sba = team_sba;
        this.team_sb = team_sb;
        this.team_sf = team_sf;
        this.team_sh = team_sh;
        this.team_lob = team_lob;
    }
   
    public void setTeamAB(int team_ab) {
        this.team_ab = team_ab;
    }
    
    public int getTeamAB() {
        return team_ab;
    }
   
    public void setTeamRuns(int team_runs) {
        this.team_runs = team_runs;
    }
    
    public int getTeamRuns() {
        return team_runs;
    }
    
    public void setTeam1B(int team_1b) {
        this.team_1b = team_1b;
    }
    
    public int getTeam1B() {
        return team_1b;
    }
    
    public void setTeam2B(int team_2b) {
        this.team_2b = team_2b;
    }
    
    public int getTeam2B() {
        return team_2b;
    }
    
    public void setTeam3B(int team_3b) {
        this.team_3b = team_3b;
    }
    
    public int getTeam3B() {
        return team_3b;
    }
    
    public void setTeamHR(int team_hr) {
        this.team_hr = team_hr;
    }
    
    public int getTeamHR() {
        return team_hr;
    }
    
    public int getTeamHits() {
        return getTeam1B() + getTeam2B() + getTeam3B() + getTeamHR();
    }
    
    public void setTeamBB(int team_bb) {
        this.team_bb = team_bb;
    }
    
    public int getTeamBB() {
        return team_bb;
    }
    
    public void setTeamHP(int team_hp) {
        this.team_hp = team_hp;
    }
    
    public int getTeamHP() {
        return team_hp;
    }
     
    public void setTeamRBI(int team_rbi) {
        this.team_rbi = team_rbi;
    }
    
    public int getTeamRBI() {
        return team_rbi;
    }
     
    public void setTeamSO(int team_so) {
        this.team_so = team_so;
    }
    
    public int getTeamSO() {
        return team_so;
    }
     
    public void setTeamGDP(int team_gdp) {
        this.team_gdp = team_gdp;
    }
    
    public int getTeamGDP() {
        return team_gdp;
    }
 
    public void setTeamSBA(int team_sba) {
        this.team_sba = team_sba;
    }
   
    public int getTeamSBA() {
        return team_sba;
    }
    
    public void setTeamSB(int team_sb) {
        this.team_sb = team_sb;
    }
   
    public int getTeamSB() {
        return team_sb;
    }
    
    public void setTeamSF(int team_sf) {
        this.team_sf = team_sf;
    }
   
    public int getTeamSF() {
        return team_sf;
    }
   
    public void setTeamSH(int team_sh) {
        this.team_sh = team_sh;
    }
   
    public int getTeamSH() {
        return team_sh;
    }
    
    public void setTeamLOB(int team_lob) {
        this.team_lob = team_lob;
    }
   
    public int getTeamLOB() {
        return team_lob;
    }
    
    // the following are computed / formatted stats
    
    public int getTeamTB() {
        return getTeam1B() + (getTeam2B() * 2) +
               (getTeam3B() * 3) + (getTeamHR() * 4);   
    } 
    
    public String getTeamSBSBAFormatted() {
        return FormatStats.getSBSBAFormatted(getTeamSB(), getTeamSBA());
    }
    
    public String getTeamAVGFormatted() {
        return FormatStats.getAVGFormatted(getTeamAB(), getTeamHits());
    }
    
    public String getTeamSLGFormatted() {
        return FormatStats.getSLGFormatted(getTeamAB(), getTeamTB());
    }
    
    public String getTeamOBFormatted() {
        return FormatStats.getOBFormatted(getTeamAB(), getTeamHits(),
                               getTeamBB(), getTeamHP(), getTeamSF());
    }
    
}
