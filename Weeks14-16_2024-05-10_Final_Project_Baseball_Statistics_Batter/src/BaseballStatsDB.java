/*
    Created by:  xxx
    Created on:  05/10/2024
    Purpose:     Baseball Batter Stats DB method
    Description: This method contains all the access for the Baseball Batter Stats database.
*/

import java.util.*;
import java.sql.*;

public class BaseballStatsDB {

    private static String dbUrl = "jdbc:sqlite:baseball_batter_stats.sqlite";
    private static Connection connection;
//    private static int PENDING = 1;
//    private static int COMPLETED = 0;
    
    private List<Batter> batters = null;

    public void openConnection() {
        try {       
            // sqlite will create database if it does not already exist
            connection = DriverManager.getConnection(dbUrl);
            try (Statement stmt = connection.createStatement()) {
                // for new database, create the table
//                stmt.execute("CREATE TABLE IF NOT EXISTS Baseball_Player (" +
//                " Player_Number INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
//                " Player_Name TEXT NOT NULL )");
                stmt.execute("CREATE TABLE IF NOT EXISTS Baseball_Game_Player_Stat (" +
                " Game_Number INTEGER NOT NULL," +
                " Player_Number INTEGER NOT NULL," +
                " Batter_Order_Number INTEGER NOT NULL," + 
                " Batter_AB   INTEGER NOT NULL," +
                " Batter_Runs INTEGER NOT NULL," +
                " Batter_1B   INTEGER NOT NULL," +
                " Batter_2B   INTEGER NOT NULL," +
                " Batter_3B   INTEGER NOT NULL," +
                " Batter_HR   INTEGER NOT NULL," +
                " PRIMARY KEY (Game_Number, Player_Number))" );
                //                " batter_positions TEXT NOT NULL" +
//                " batter_rbi  INTEGER NOT NULL" +
//                " batter_bb   INTEGER NOT NULL" +
//                " batter_hb   INTEGER NOT NULL" +
//                " batter_sf   INTEGER NOT NULL" +
//                " batter_sh   INTEGER NOT NULL" +
//                " batter_sb   INTEGER NOT NULL" +
//                " batter_sba  INTEGER NOT NULL");
//                stmt.execute("CREATE TABLE IF NOT EXISTS Baseball_Team_Schedule (" +
//                " season_year INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
//                " game_number INTEGER SECONDARY KEY AUTOINCREMENT NOT NULL," +
//                " game_date INTEGER NOT NULL," + // fix date format
//                " game_time INTEGER NOT NULL," + // fix time format
//                " game_opponent_number SECONDARY INTEGER NOT NULL," + 
//                " game_location TEXT NOT NULL" +
//                " game_location_name TEXT NOT NULL"); 
//                stmt.execute("CREATE TABLE IF NOT EXISTS Baseball_Team_Opponent (" +
//                " opponent_number INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
//                " opponent_team_name TEXT PRIMARY KEY AUTOINCREMENT NOT NULL");

            } catch (SQLException e) {
                Console.printlnerr("openConnection create table failed: " + e); 
            }
        } catch (SQLException e) {
            Console.printlnerr("openConnection connection failed: " + e); 
        }    
    }
    
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            Console.printlnerr("closeConnection failed: " + e);          
        }
    }
    
    public void addGamePlayerStats(int game_number, int player_number, 
            int batter_order_number, int batter_ab, int batter_runs, int batter_1b, 
            int batter_2b, int batter_3b, int batter_hr) {
        openConnection(); 
        String insertPlayerStat =
                "INSERT INTO Baseball_Game_Player_Stat (Game_Number, Player_Number, " +
                  "Batter_Order_Number, Batter_AB, Batter_Runs, Batter_1B, " +  
                  "Batter_2B, Batter_3B, Batter_HR)" + 
                  " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(insertPlayerStat)) {
            ps.setInt(1, game_number);
            ps.setInt(2, player_number);
            ps.setInt(3, batter_order_number);
            ps.setInt(4, batter_ab);
            ps.setInt(5, batter_runs);
            ps.setInt(6, batter_1b);
            ps.setInt(7, batter_2b);
            ps.setInt(8, batter_3b);
            ps.setInt(9, batter_hr);
            ps.executeUpdate();
        } catch (SQLException e) {
            Console.printlnerr("addGamePlayerStats failed: " + e);
        }
        closeConnection();
    } 
    
    public List<Batter> getGamePlayerStats(int game_number) {
        batters = new ArrayList<>();
        openConnection();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM Baseball_Game_Player_Stat WHERE Game_Number = ?")) {
            ps.setString(1, String.valueOf(game_number));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Batter b = new Batter(rs.getInt(2), rs.getInt(3),
                                      rs.getInt(4), rs.getInt(5), rs.getInt(6),
                                      rs.getInt(7), rs.getInt(8), rs.getInt(9));
                batters.add(b);
            }
            rs.close();
        } catch (SQLException e) {
            Console.printlnerr("getGamePlayerStats failed: " + e);
        }
        closeConnection();
        return batters;
    }
    
    
//    public int getNumberOfTasks() {
//        try (PreparedStatement ps = connection.prepareStatement(
//                "SELECT COUNT(*) AS recordCount FROM Task")) {
//            ResultSet rs = ps.executeQuery();
//            return rs.getInt("recordCount");
//        } catch (SQLException e) {
//            Console.printlnerr("getNumberOfTasks failed: " + e);
//            return 0;
//        }  
//    }
//    
//    public int getNumberOfTasksByType(int completionCode) {
//        int recordCount = 0;
//        openConnection();
//        try (PreparedStatement ps = connection.prepareStatement(
//                "SELECT COUNT(*) AS recordCount FROM Task WHERE completed = ?")) {
//            ps.setString(1, String.valueOf(completionCode));
//            ResultSet rs = ps.executeQuery();
//            recordCount = rs.getInt("recordCount");
//        } catch (SQLException e) {
//            Console.printlnerr("getNumberOfTasksByType failed: " + e);
//        }  
//        closeConnection();
//        return recordCount;
//    }
//    
//    public int getNumberOfPendingTasks() {
//        return getNumberOfTasksByType(PENDING);
//    }
//    
//    public int getNumberOfCompletedTasks() {
//        return getNumberOfTasksByType(COMPLETED);
//    }
//    
//    public List<Task> getTasksByCompletedCode(int completedCode) {
//        tasks = new ArrayList<>();
//        openConnection();
//        try (PreparedStatement ps = connection.prepareStatement(
//                "SELECT * FROM Task WHERE completed = ?")) {
//            ps.setString(1, String.valueOf(completedCode));
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Task t = new Task(rs.getInt(1), rs.getString(2), rs.getInt(3));
//                tasks.add(t);
//            }
//            rs.close();
//        } catch (SQLException e) {
//            Console.printlnerr("getTasksByCompletedCode failed: " + e);
//        }
//        closeConnection();
//        return tasks;
//    }
//    
//    public List<Task> getPendingTasks() {
//        return getTasksByCompletedCode(PENDING);
//    }
//       
//    public List<Task> getCompletedTasks() {
//        return getTasksByCompletedCode(COMPLETED);
//    }
//    
//    public void addTask(String description) {
//        openConnection(); 
//        String insertTask =
//                "INSERT INTO TASK (Description, Completed) VALUES (?, ?)";
//        try (PreparedStatement ps = connection.prepareStatement(insertTask)) {
//            ps.setString(1, description);
//            ps.setDouble(2, PENDING);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            Console.printlnerr("addTask failed: " + e);
//        }
//        closeConnection();
//    } 
//    
//    public void completeTask(int taskToComplete) {
//        tasks = getPendingTasks();
//        openConnection();
//        String updateTask = 
//                "UPDATE Task SET completed = " + COMPLETED + " WHERE taskID = ?";
//        try (PreparedStatement ps = connection.prepareStatement(updateTask)) {
//            ps.setString(1, String.valueOf(tasks.get(taskToComplete - 1).getTaskID()));
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            Console.printlnerr("completeTask failed: " + e);
//        }
//        closeConnection();
//    } 
//    
//    public void deleteTask(int taskToDelete) {    
//        tasks = getCompletedTasks();
//        openConnection();
//        String updateTask = "DELETE FROM Task WHERE taskID = ?";
//        try (PreparedStatement ps = connection.prepareStatement(updateTask)) {
//            ps.setString(1, String.valueOf(tasks.get(taskToDelete - 1).getTaskID()));
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            Console.printlnerr("deleteTask failed: " + e);
//        }
//        closeConnection();
//    } 
//        
}
