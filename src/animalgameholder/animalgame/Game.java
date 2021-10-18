package animalgameholder.animalgame;


import java.util.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is our Game class where we have all the menus right now
 *
 * Author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class Game {

private int amountOfTurns;
private int currentTurn;
private Player currentPlayer;
private int playerIndex;
private int numberOfPlayers = 0;
boolean exit = false;

private final GameLogic logic = new GameLogic();
    Scanner console = new Scanner(System.in);

ArrayList<Player> players = new ArrayList<>();
ArrayList<Player> loss = new ArrayList<>();





    public Game() {

        boolean start = true;
        while (start) {

            players.clear();
            newScreen();
            System.out.println("Welcome to our animal game!");
            System.out.println("1. Start game 2. Information 3. Load Game 4. Exit Game");
            int input = console.nextInt();

            switch (input) {
                case 1 -> startMenu();
                case 2 -> gameRules();
                case 3 -> System.out.println("Save"); //Printar ut när man går in på information
                case 4 -> {
                    boolean exit = true;
                    while (exit)
                        System.out.println("1.Start Game \n2.Turn off game");
                        int input2 = console.nextInt();
                        if (input2 == 1) {
                            System.out.println("Turning off game");
                            start = false;
                            exit = false;
                        }
                        if (input2 == 2) {
                            exit = false;
                        }
                    }
                }
            }
        }


    public void startMenu(){

        currentTurn = 1;
        exit = false;
        amountOfTurns = gameSettings("How many rounds do you wanna play 5-30 rounds", 5, 30);
        numberOfPlayers = gameSettings("How many players do you want, between 2-4", 2, 4);
        pickPlayerName();
        information();
        gameMenu();

    }

    public void game(){
        Store store = new Store();
        Breeding breed = new Breeding();
        boolean game = true;

        while (game) {
            int pick = 0;

            while (pick < 1 || pick > 7){
                currentPlayer.playerInv();
                System.out.println("It is round " + currentTurn);
                System.out.println(currentPlayer.getName() + " turn");
                System.out.println("["+"1.Store"+"]"+ " ["+ "2.Breed" +"]"+ " ["+ "3.Feed Animal"+"]" + " ["+"4.Next Player"+"]"+ " ["+"5.Game Info"+"]"+ " ["+"6.Exit to main menu"+"]");
                pick = console.nextInt();
            }

            switch (pick) {
                case 1 -> store.buyMenu(currentPlayer);
                case 2 -> breed.animalBreed(currentPlayer);
                case 3 -> currentPlayer.animalFeeding(currentPlayer);
                case 4 -> game = false;
                case 5 ->information();
                case 6 -> {
                    game = false;
                    exit = true;
                }
            }
        }
    }

    public void pickPlayerName(){
        Scanner scan = new Scanner(System.in);
        newScreen();
        System.out.println("You picked " + numberOfPlayers + " amount of players");
        for (int i = 1; i < numberOfPlayers + 1; i++ ) {
            System.out.println("Player " + i + " pick your name: ");
            String name = scan.next();
            players.add(new Player(name));
        }
    }

    public void gameMenu(){ //comment

        for (int pick = currentTurn; pick < amountOfTurns + 1; pick++) {

            for (int pick1 = playerIndex; pick1 < players.size(); pick1++){
                currentPlayer = players.get(pick1);
                currentPlayer.trueBooleans();

                logic.startRound(currentPlayer);
                game();
                logic.endRound(currentPlayer, this);

                if (exit)
                    break;
                playerIndex++;
            } if (exit)
                break;

            playerIndex = 0;
            currentTurn++;
        }
        logic.winnerPick(this);
    }



    public int gameSettings(String text, int min, int max) {

        System.out.println(text);

        int pick = -1;
        try {
            pick = console.nextInt();
        } catch (Exception e) {
            console.next();
        }
        return pick < min || pick > max ?
                gameSettings(text, min, max) : pick;
    }

    public void gameRules(){
        System.out.println("All the game rules");
    }

    public void information (){

        System.out.println("Information");

        System.out.println("Amount of rounds = " + amountOfTurns);
        int amountOfPlayer = 1;
        for (Player player : players){
            System.out.println("Player " + amountOfPlayer + ": " + player.getName());
            amountOfPlayer++;
        }
        System.out.println("-------");
        System.out.println(players.get(0).getName() + " start the round");
    }




        public static void continueButton(){
        Scanner console = new Scanner(System.in); //We need to use one scanner, not crating more in the methods.
            System.out.println("\n");
            System.out.println("Press enter to continue");
            console.nextLine();
        }
      public static void newScreen(){ //Static so we can reach outside Game class
        System.out.println("\n".repeat(50));
      }

        public void foodSelect(){
            System.out.println("food");
        }





}
