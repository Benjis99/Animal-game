package animalgameholder.animalgame;

import animalgameholder.animalgame.animals.Bird;
import animalgameholder.animalgame.animals.Cat;
import animalgameholder.animalgame.animals.Dog;
import animalgameholder.animalgame.foods.DryFoodDog;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {


private int amountOfTurns;
private int currentTurn;
private Player currentPlayer;
private int playerIndex;
private int numberOfPlayers = 0;
boolean exit = false;


ArrayList<Player> players = new ArrayList<>();
Scanner console = new Scanner(System.in);



    public Game() {

        boolean start = true;
        while (start) {

            players.clear();
            newScreen();
            System.out.println("Welcome to our animal game!");
            System.out.println("1. Start game 2. Information 3. Load Game 4. Exit Game");
            int input = console.nextInt();

            switch (input){
                case 1:
                startMenu();
                case 2:
                    information();
                case 3:
                    System.out.println("Save");
                case 4:
                    boolean exit = true;
                    while (exit) {
                        System.out.println("Exit Game 1. Yes 2. No");
                        input = console.nextInt();
                        if (input == 1){
                            System.out.println("Game is turning off");
                            exit = false;
                            start = false;
                        }
                        if (input == 2){
                            start = false;
                        }
                    }



            }
        }
    }

    public void startMenu(){
        currentTurn = 1;
        exit = false;
        amountOfTurns = gameSettings("How many rounds do you wanna play", 5, 30);
        numberOfPlayers = gameSettings("How many players do you want, between 2-5", 2, 5);
        pickPlayerName();
        information();
        gameMenu();


    }
    public void pickPlayerName(){
        newScreen();
        System.out.println("You picked " + numberOfPlayers + " amount of players");
        for (int i = 1; i < numberOfPlayers + 1; i++ ) {
            System.out.println("First Player " + i + " pick your name: ");
            String name = console.next();
            players.add(new Player(name));
        }

    }

    public void gameMenu(){ //comment

        for (int pick = currentTurn; pick < amountOfTurns + 1; pick++) {

            for (int pick1 = playerIndex; pick1 < players.size(); pick1++){
                currentPlayer = players.get(pick1);
                currentPlayer.trueBooleans();

                //Event class

                if (exit)
                    break;
                playerIndex++;
            } if (exit)
                break;

            playerIndex = 0;
            currentTurn++;
        }

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

    public void information (){
        System.out.println("Information");

        System.out.println("Amount of rounds = " + amountOfTurns);
        int amountOfPlayer = 1;
        for (Player player : players){
            System.out.println("Player " + amountOfPlayer + ": " + player.getName());
            amountOfPlayer++;
        }
        System.out.println(players.get(0).getName() + " start the round");
    }




        public static void continueButtom(){
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
