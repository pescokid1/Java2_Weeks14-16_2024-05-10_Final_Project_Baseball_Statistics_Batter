/*
    Created by:  xxx
    Created on:  05/10/2024
    Purpose:     integer check
    Description: This method verifies a string is an integer

Terry Pescosolido - 5/06/24 - initial commit
*/
package com.mycompany.mavenproject1;

public class IntCheck {
    
    public static boolean isInteger(String str) {
        
        if (str.length() > 0) {
            try {
                int intValue = Integer.parseInt(str);
                return true;
            } catch (Exception ex) {
                return false;
            }
        } else {
            return false;
        }
    }  
    
}