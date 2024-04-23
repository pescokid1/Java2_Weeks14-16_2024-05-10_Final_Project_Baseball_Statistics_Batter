/*
    Created by:  xxx
    Created on:  05/10/2024
    Purpose:     Baseball Batter Stats App
    Description: This app manages batter stats for a baseball team.
*/

import java.util.List;

public class BaseballBatterStats {

    public static void main(String args[]) {     
        
        BaseballStatsDB baseball_stats_db = new BaseballStatsDB();
        Batter task = new Batter();
        List<Batter> batters = null;        
        String [] validCommands = {"add", "game", "season", "menu", "help", "exit"};
             
        Console.println("Welcome to the Baseball Batter Stats App");
        displayCommandMenu();
        
        String command = "nothing";
        while (!command.equals("exit")) {
            
            int game_number = 0;
            
            command = Console.getString("\nCommand: ", validCommands);
            switch(command) {
                case "add" :
                    game_number = Console.getInt("Game number: ");               
                    String player_continue = "y";
                    while (player_continue.equals("y")) {
                        Console.println("Enter batter info");
                        int player_number = Console.getInt("Player number: ");
                        int batter_order_number = Console.getInt("Batter order number: ");
                        int batter_ab = Console.getInt("Batter ab: ");
                        int batter_runs = Console.getInt("Batter runs: ");
                        int batter_1b = Console.getInt("Batter 1b: ");
                        int batter_2b = Console.getInt("Batter 2b: ");
                        int batter_3b = Console.getInt("Batter 3b: ");
                        int batter_hr = Console.getInt("Batter hr: ");

                        baseball_stats_db.addGamePlayerStats(game_number, player_number, 
                              batter_order_number, batter_ab, batter_runs, batter_1b, 
                              batter_2b, batter_3b, batter_hr);
                        
                        player_continue = Console.getString("Another batter? (y/n): ", "y", "n");
                    }
//                    tasks = taskdb.getPendingTasks();
//                    if (!tasks.isEmpty()) {
//                        displayTasks(tasks, "");
//                    } else {
//                        Console.println("There are no pending tasks."); 
//                    }
                    break;
                case "game" :
                    game_number = Console.getInt("Game number: ");
                    batters = baseball_stats_db.getGamePlayerStats(game_number);
                    Console.println("game\t" + "pn\t" + "bn\t" + "ab\t" +
                                    "r\t" + "h\t" + "2b\t" + "3b\t" + "hr\t");
                    int i = 0;
                    for (Batter b : batters) {
                        i++;
                        Console.println(game_number + "\t" +
                                String.valueOf(b.getPlayerNumber()) + "\t" +
                                String.valueOf(b.getBatterOrderNumber()) + "\t" +
                                String.valueOf(b.getBatterAB()) + "\t" +
                                String.valueOf(b.getBatterRuns()) + "\t" +
                                String.valueOf(b.getBatter1B() + b.getBatter2B() + b.getBatter3B() + b.getBatterHR()) + "\t" +
                                String.valueOf(b.getBatter2B()) + "\t" +
                                String.valueOf(b.getBatter3B()) + "\t" +
                                String.valueOf(b.getBatterHR())); 
                    }

//                    tasks = taskdb.getCompletedTasks();
//                    if (!tasks.isEmpty()) {
//                        displayTasks(tasks, " (DONE!)");
//                    } else {
//                        Console.println("There are no completed tasks."); 
//                    }
                    break;
                case "season" :
//                    String taskDescription = Console.getString("Description: ");
//                    taskdb.addTask(taskDescription);
                    break;
                case "menu" :
                    displayCommandMenu();
                break;
                case "help" :
//                    int numberOfPendingtasks = taskdb.getNumberOfPendingTasks();
//                    if (numberOfPendingtasks == 0) {
//                        Console.println("There are no pending tasks to mark as completed."); 
//                    } else { 
//                        int taskToComplete = Console.getInt("Number: ", 1, numberOfPendingtasks);
//                        taskdb.completeTask(taskToComplete);
//                    }
                    break;
                case "exit" :
                    break;
            }  
        
        }
        
        Console.println("\nBye!");
        
    }
    
    public static void displayCommandMenu() {
        Console.println("\nCOMMAND MENU");
        Console.println("add      - Add stats for a game");
        Console.println("game     - View stats for a game");
        Console.println("season   - View stats for a season or part of a season");
        Console.println("menu     - View this command menu");
        Console.println("help     - View help for this app");
        Console.println("exit     - Exit program");
    }
        
//    public static void displayTasks(List<Batter> tasks, String trailer) {
//        int i = 0;
//        for (Batter t : tasks) {
//            i++;
//            Console.println(i + ". " + t.getDescription() + trailer); 
//        }
//    }
        
}