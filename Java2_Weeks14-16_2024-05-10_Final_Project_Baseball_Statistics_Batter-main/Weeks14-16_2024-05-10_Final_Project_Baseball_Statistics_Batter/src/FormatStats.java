/*
    Created by:  xxx
    Created on:  05/3/2024
    Purpose:     BaseballStats methods
    Description: This method computes stats needed for the baseball reports.

Terry Pescosolido - 5/3/24 - created
*/

//package com.mycompany.mavenproject1;

import java.text.NumberFormat;

public class FormatStats {
    
    // stats for a group or set of games
    
    public static String getGPGSFormatted(int gp, int gs) {
        // format as "xxx-xxx"?
        return gp + "-" + gs;
    }
    
    public static String getSBSBAFormatted(int sb, int sba) {
        // format as "xxx-xxx"
        return sb + "-" + sba;
    }
    
    public static String getAVGFormatted(int ab, int hits) {
        if (ab == 0) {
            return " ---"; // if no at-bats, cannot compute
        } else {
            double avg = (double) hits / (double) ab;  
            return formatPercentage(avg);
        }
    }
    
    public static String getSLGFormatted(int ab, int tb) {
        // format as ".xxx", can be x.xxx!
        if (ab == 0) {
            return " ---"; // no slg if no bases (or should this be ".000"
        } else {
            double slg = (double) tb / (double) ab;
            return formatPercentage(slg);
        }
    }
    
    public static String getOBFormatted(int ab, int hits, int bb, int hp, int sf) {
        // format as ".xxx"
        // OBP = (Hits + Walks + Hit-By-Pitch) / (At Bats + Walks + Hit By Pitch + Sacrifice Flies)
        int divisor = ab + bb + hp + sf;
        if (divisor == 0) {
            return " ---"; // no slg if no bases (or should this be ".000"?)
        } else {
            double ob = (double) (hits + bb + hp) / (double) divisor;
            return formatPercentage(ob); // format batterTotalOnBase() 
        }
    }
    
    public static String formatPercentage(double number) {
        
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
