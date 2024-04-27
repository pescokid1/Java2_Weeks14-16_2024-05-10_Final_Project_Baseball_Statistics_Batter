/*
    Created by:  xxx
    Created on:  05/10/2024
    Purpose:     Console class
    Description: Provides a set of methods to interact with user
*/

import java.util.*;

public class Console {
    
    private static Scanner sc = new Scanner(System.in);

    public static void print(String s){
        System.out.print(s);
    }
    public static void println(){
        System.out.println();
    }
    public static void println(String s){
        System.out.println(s);
    }
    public static void printlnerr(String s){
        System.err.println(s);
    }

    public static String getString(String prompt)
    {
        System.out.print(prompt);
        String response = sc.nextLine();   // read the first string on the line
        while (response.equals(""))
        {
            System.out.println("Error! This entry is required. Try again.");
            System.out.print(prompt);
            response = sc.nextLine();   // read the first string on the line
        }
        return response; 
    }
    
    public static String getString(String prompt, String [] validStrings) 
    {
        String response = getString(prompt);
        while (!Arrays.asList(validStrings).contains(response))
        {
            System.out.println("Error! \"" + response + "\" is not valid. Try again.");
            response = getString(prompt);
        }
        return response; 
    }
   
    public static String getString(String prompt, String validResponse1, String validResponse2)
    {
        String response = getString(prompt);
        while (!(response.equalsIgnoreCase(validResponse1)) && !(response.equalsIgnoreCase(validResponse2)))
        {
            System.out.println("Error! Entry must be '" + validResponse1 + "' or '" + validResponse2 + "'. Try again.");
            response = getString(prompt);
        }
        return response;
    }
    
    public static int getInt(String prompt) {
        while (true) {
            String response = getString(prompt);
            try {
                return Integer.parseInt(response);
            } catch(NumberFormatException e) {
                System.out.println("Error! Invalid integer value. Try again.");
            }
        }
    }
   
    public static int getInt(String prompt, int min, int max) {
        while (true) {
            int value = getInt(prompt);
            if (value < min) { 
                System.out.println("Error! Number must be greater than or equal to " 
                        + min + ". Try again.");
            } else if (value > max) {
                System.out.println("Error! Number must be less than or equal to " 
                        + max + ". Try again.");
            } else { // good value input by user
                return value;
            } 
        }
    }
    
    public static double getDouble(String prompt) {

        while (true) {
            String response = getString(prompt);
            try {
                return Double.parseDouble(response);
            } catch(NumberFormatException e) {
                System.out.println("Error! Invalid double value. Try again.");  
            }
        }
    }
    
    public static double getDouble(String prompt, double min, double max) {
        while (true) {
            double value = getDouble(prompt);
            if (value < (double)min) { 
                System.out.println("Error! Number must be greater than or equal to " 
                        + (double)min + ". Try again.");
            } else if (value > (double)max) {
                System.out.println("Error! Number must be less than or equal to " 
                        + (double)max + ". Try again.");
            } else { // good value input by user
                return value;
            }
        }
    }
    
}