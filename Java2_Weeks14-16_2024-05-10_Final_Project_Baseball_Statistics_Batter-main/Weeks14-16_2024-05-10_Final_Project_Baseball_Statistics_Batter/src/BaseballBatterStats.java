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
        Team team = new Team();
        List<Batter> batters = null; 
        List<Player> players = null;     
        String [] validCommands = {"addgame", "game", "season", "addplayer", "players", "menu", "help", "exit"};
             
        Console.println("Welcome to the Baseball Batter Stats App");
        displayCommandMenu();
        
        String command = "nothing";
        while (!command.equals("exit")) {
            
            int game_number = 0;
            int i = 0;
            
            command = Console.getString("\nCommand: ", validCommands);
            switch(command) {
                case "addgame" :
                    game_number = Console.getInt("Game number: ");               
                    String player_continue = "y";
                    while (player_continue.equals("y")) {
                        Console.println("Enter batter info");
                        int player_number = Console.getInt("Player number: ");
                        int batter_order_number = Console.getInt("Batter order number: ");
                        int batter_gs = Console.getInt("Batter gs: ");
                        int batter_ab = Console.getInt("Batter ab: ");
                        int batter_runs = Console.getInt("Batter runs: ");
                        int batter_1b = Console.getInt("Batter 1b: ");
                        int batter_2b = Console.getInt("Batter 2b: ");
                        int batter_3b = Console.getInt("Batter 3b: ");
                        int batter_hr = Console.getInt("Batter hr: ");
                        int batter_bb = Console.getInt("Batter bb: ");
                        int batter_hp = Console.getInt("Batter hp: ");
                        int batter_rbi = Console.getInt("Batter rbi: ");
                        int batter_so = Console.getInt("Batter so: ");
                        int batter_gdp = Console.getInt("Batter gdp: ");
                        int batter_sba = Console.getInt("Batter sba: ");
                        int batter_sb = Console.getInt("Batter sb: ");
                        int batter_sh = Console.getInt("Batter sh: ");
                        int batter_lob = Console.getInt("Batter lob: ");

                        baseball_stats_db.addGamePlayerStats(game_number, player_number, 
                              batter_order_number, batter_gs,
                              batter_ab, batter_runs, batter_1b, batter_2b, 
                              batter_3b, batter_hr, batter_bb, batter_hp, batter_rbi,
                              batter_so, batter_gdp, batter_sba, batter_sb,
                              batter_sh, batter_lob);
                        
                        player_continue = Console.getString("Another batter? (y/n): ", "y", "n");
                    }
                    break;
                case "game" :
                    game_number = Console.getInt("Game number: ");
                    batters = baseball_stats_db.getGamePlayerStats(game_number);
                    Console.println("game\t" + "p#\t" + "bn\t" + "gs\t" + 
                                    "avg\t" + "ab\t" +
                                    "r\t" + "h\t" + "2b\t" + "3b\t" + "hr\t" +
                                    "rbi\t" + 
                                    "tb\t" + "slg\t" + "bb\t" + "hp\t" + "so\t" + "gdp\t" + "ob%\t" +
                                    "sba\t" + "sb\t" + "sf\t" + "sh\t" + "lob\t"  + 
                                    "pn");
                    for (Batter b : batters) {
                        i++;
                        Console.println(game_number + "\t" +
                                String.valueOf(b.getPlayerNumber()) + "\t" +
                                String.valueOf(b.getBatterOrderNumber()) + "\t" +
                                String.valueOf(b.getBatterGS()) + "\t" +
                                b.getBatterAVGFormatted() + "\t" +
                                String.valueOf(b.getBatterAB()) + "\t" +
                                String.valueOf(b.getBatterRuns()) + "\t" +
                                String.valueOf(b.getBatterHits()) + "\t" +
                                String.valueOf(b.getBatter2B()) + "\t" +
                                String.valueOf(b.getBatter3B()) + "\t" +
                                String.valueOf(b.getBatterHR()) + "\t" +
                                String.valueOf(b.getBatterRBI()) + "\t" +
                                String.valueOf(b.getBatterTB()) + "\t" +
                                b.getBatterSLGFormatted() + "\t" +  
                                String.valueOf(b.getBatterBB()) + "\t" +
                                String.valueOf(b.getBatterHP()) + "\t" +
                                String.valueOf(b.getBatterSO()) + "\t" +
                                String.valueOf(b.getBatterGDP()) + "\t" +
                                b.getBatterOBFormatted() + "\t" + 
                                String.valueOf(b.getBatterSBA()) + "\t" +
                                String.valueOf(b.getBatterSB()) + "\t" +
                                String.valueOf(b.getBatterSF()) + "\t" +
                                String.valueOf(b.getBatterSH()) + "\t" +
                                String.valueOf(b.getBatterLOB() + "\t" + 
                                b.getPlayerName()));
                    }
                    team = baseball_stats_db.getGameTeamStats(game_number);
                    Console.println( 
                                "Totals\t\t\t\t" +
                                team.getTeamAVGFormatted() + "\t" + 
                                String.valueOf(team.getTeamAB()) + "\t" +
                                String.valueOf(team.getTeamRuns()) + "\t" +
                                String.valueOf(team.getTeamHits()) + "\t" +
                                String.valueOf(team.getTeam2B()) + "\t" +
                                String.valueOf(team.getTeam3B()) + "\t" +
                                String.valueOf(team.getTeamHR()) + "\t" +
                                String.valueOf(team.getTeamRBI()) + "\t" + 
                                String.valueOf(team.getTeamTB()) + "\t" +        
                                team.getTeamSLGFormatted() + "\t" +                
                                String.valueOf(team.getTeamBB()) + "\t" +
                                String.valueOf(team.getTeamHP()) + "\t" + 
                                String.valueOf(team.getTeamSO()) + "\t" +
                                String.valueOf(team.getTeamGDP()) + "\t" +
                                team.getTeamOBFormatted() + "\t" + 
                                String.valueOf(team.getTeamSBA()) + "\t" +
                                String.valueOf(team.getTeamSB()) + "\t" +
                                String.valueOf(team.getTeamSF()) + "\t" +  
                                String.valueOf(team.getTeamSH()) + "\t" + 
                                String.valueOf(team.getTeamLOB()));
                    break;
                case "season" :
                    int starting_game_number = Console.getInt("Starting game number: "); 
                    int ending_game_number = Console.getInt("  Ending game number: "); 
                    batters = baseball_stats_db.getSeasonPlayerStats(starting_game_number, ending_game_number);
                    Console.println("Season stats for game " + starting_game_number + " thru game " + ending_game_number);
                    Console.println( "p#\t" + "avg\t" + "gp-gs\t" + "ab\t" +
                                    "r\t" + "h\t" + "2b\t" + "3b\t" + "hr\t" +
                                    "rbi\t" + "tb\t"  + "slg%\t" + "bb\t" + 
                                    "hp\t"  + "so\t" + "gdp\t" + "ob%\t" +
                                    "sf\t" + "sh\t" + "sb-att\t" + "lob\t" + "pn");                                          
                    for (Batter b : batters) {
                        i++;
                        Console.println( 
                                String.valueOf(b.getPlayerNumber()) + "\t" +
                                b.getBatterAVGFormatted() + "\t" + 
                                b.getBatterGPGSFormatted() + "\t" +
                                String.valueOf(b.getBatterAB()) + "\t" +
                                String.valueOf(b.getBatterRuns()) + "\t" +
                                String.valueOf(b.getBatterHits()) + "\t" +
                                String.valueOf(b.getBatter2B()) + "\t" +
                                String.valueOf(b.getBatter3B()) + "\t" +
                                String.valueOf(b.getBatterHR()) + "\t" +
                                String.valueOf(b.getBatterRBI()) + "\t" + 
                                String.valueOf(b.getBatterTB()) + "\t" +        
                                b.getBatterSLGFormatted() + "\t" +                
                                String.valueOf(b.getBatterBB()) + "\t" +
                                String.valueOf(b.getBatterHP()) + "\t" + 
                                String.valueOf(b.getBatterSO()) + "\t" +
                                String.valueOf(b.getBatterGDP()) + "\t" +
                                b.getBatterOBFormatted() + "\t" + 
                                String.valueOf(b.getBatterSF()) + "\t" +  
                                String.valueOf(b.getBatterSH()) + "\t" +        
                                b.getBatterSBSBAFormatted() + "\t" + 
                                String.valueOf(b.getBatterLOB() + "\t" + 
                                b.getPlayerName()));
                    }
                    team = baseball_stats_db.getSeasonTeamStats(starting_game_number, ending_game_number);
                    Console.println( 
                                "Totals\t" +
                                team.getTeamAVGFormatted() + "\t\t" + 
                                String.valueOf(team.getTeamAB()) + "\t" +
                                String.valueOf(team.getTeamRuns()) + "\t" +
                                String.valueOf(team.getTeamHits()) + "\t" +
                                String.valueOf(team.getTeam2B()) + "\t" +
                                String.valueOf(team.getTeam3B()) + "\t" +
                                String.valueOf(team.getTeamHR()) + "\t" +
                                String.valueOf(team.getTeamRBI()) + "\t" + 
                                String.valueOf(team.getTeamTB()) + "\t" +        
                                team.getTeamSLGFormatted() + "\t" +                
                                String.valueOf(team.getTeamBB()) + "\t" +
                                String.valueOf(team.getTeamHP()) + "\t" + 
                                String.valueOf(team.getTeamSO()) + "\t" +
                                String.valueOf(team.getTeamGDP()) + "\t" +
                                team.getTeamOBFormatted() + "\t" + 
                                String.valueOf(team.getTeamSF()) + "\t" +  
                                String.valueOf(team.getTeamSH()) + "\t" +        
                                team.getTeamSBSBAFormatted() + "\t" + 
                                String.valueOf(team.getTeamLOB()));
                    break;
                case "addplayer" :
                    String player_firstName = Console.getString("Player first name: "); 
                    String player_lastName = Console.getString("Player last name:  "); 
                    int player_number = Console.getInt("Player number: ", 1, 99); 
                    baseball_stats_db.addPlayer(player_firstName, player_lastName, 
                          player_number); 
                    break;  
                case "players" :
                    players = baseball_stats_db.getPlayers();
                    Console.println("p#\t" + "active\t" + "pn"); 
                    for (Player p : players) {
                        Console.println(
                                String.valueOf(p.getPlayerNumber()) + "\t" +
                                String.valueOf(p.getPlayerActive()) + "\t" +
                                String.valueOf(p.getPlayerName()) );
                    }
                    break;
                case "menu" :
                    displayCommandMenu();
                    break;
                case "help":
                    break;
                case "exit" :
                    break;
            }  
        
        }
        
        Console.println("\nBye!");
        
    }
    
    public static void displayCommandMenu() {
        Console.println("\nCOMMAND MENU");
        Console.println("addgame    - Add stats for a game");
        Console.println("game       - View stats for a game");
        Console.println("season     - View stats for a season or part of a season");
        Console.println("addplayer  - Add stats for a game");
        Console.println("players    - List the players");
        Console.println("menu       - View this command menu");
        Console.println("help       - View help for this app");
        Console.println("exit       - Exit program");
    }

}