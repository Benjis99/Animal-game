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
private int currentPlayer;
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
                    break;
                case 2:
                    information();
                    break;
                case 3:
                    System.out.println("to be added");
                    break;
                case 4:
                    System.exit(0);

            }
        }
    }

    public void startMenu(){
        currentTurn = 1;
        exit = false;
        amountOfTurns = gameSettings("How many rounds do you wanna play (between 5-30 rounds) ?", 5, 30);
        numberOfPlayers = gameSettings("How many players do you want (between 2-4 players) ?", 2, 4);
        pickPlayerName();
        information();


    }
    public void pickPlayerName(){
        newScreen();
        System.out.println("You picked " + numberOfPlayers + " players");
        for (int i = 1; i < numberOfPlayers + 1; i++ ) {
            System.out.println("Player: " + i + " Enter your name: ");
            String name = console.next();
            players.add(new Player(name));
        }

    }

    public void mainGame(){ //comment

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



    /*
    public void scannerPlayer(){
        String input = console.nextLine();
    }

    public void amountOfPlayers(){ //Method that will check how many players the user picked, and check it with the if else statement.
        System.out.println("How many players do you want? 2-4");
        numberOfPlayers = console.nextInt();
        if (numberOfPlayers < 2 || numberOfPlayers > 4){
            System.out.println("Invalid amount of players, you must choose between 2-4");
            amountOfPlayers();
        } else{
            System.out.println("You picked " + numberOfPlayers + " players.");
        }
    }

    public void createPlayer(){
        System.out.println("Whats your usernames?"); // Enter all names of players and loops through amount of players chosen
        for(int i = 1; i < numberOfPlayers + 1; i++){
            System.out.println("Player " + i + " name:");
            String name = console.next();
            players.add(new Player(name));
        }
        amountOfRounds();
    }

    public void amountOfRounds (){ //Method that will check how many rounds the user picked, and check it with the if else statement.
        System.out.println("How many rounds do you wanna play? 5-30 rounds");
        int input = console.nextInt();
        if (input < 5 || input > 30){
            System.out.println("Invalid amount of rounds, you must choose between 5-30 rounds");
            amountOfRounds();
        } else{
            System.out.println("You picked " + input + " rounds.");
            amountOfStartMoney();
        }
    }
    //Method that will check how much money the user picked, and check it with the if else statement.
    public void amountOfStartMoney(){ //Create start amount later when all the animal have prices
        System.out.println("How much money do you wanna start with? Lowest amount is 0 and max 2000000000 Swedish Krona");
        setInputMoney(console.nextInt());
        if (getInputMoney() < 0 || getInputMoney() > 2000000000) {
            System.out.println("Invalid amount of money, try again. 0-2000000000 Swedish Krona");
            amountOfStartMoney();
        }
            else{
                newScreen();
                System.out.println("You have picked " + getInputMoney() + " Swedish Krona");
                animalSelect();
            }
        }

        public void animalSelect(){ //1. for cat, 2. for dog, 3. for hamster, 4. for goldfish, 5. for bird
        //1 animal, 2 for food
            System.out.println("1. For animal 2. For food");
            int inputForMenu = console.nextInt();
            if (inputForMenu == 2){
                foodSelect();
            } else if (inputForMenu == 1) {
                System.out.println("1. For Cat, 2. For Dog, 3. For Hamster, 4. For Goldfish, 5. For Bird, 6. End Game");
                int input = console.nextInt();
                switch (input) {
                    case 1: {
                        System.out.println("You picked Cat");
                        System.out.println("Cat price 100.. age... ");
                        System.out.println("1. Buy 2. Back to menu");
                        int choice = console.nextInt();
                        if (choice == 1) {
                            System.out.println("Bought one cat");
                        } else if (choice == 2) {
                            animalSelect();
                        } else
                            System.out.println("Invalid choice, back to main menu you go");
                        animalSelect();
                        break;
                    }
                    case 2: {
                        System.out.println("You picked Dog");


                        int choice = console.nextInt();
                        if (choice == 1) {
                            System.out.println("Bought one Dog");
                        } else if (choice == 2) {
                            animalSelect();
                        } else
                            System.out.println("Invalid choice, back to main menu you go");
                        animalSelect();

                    }
                    case 3: {
                        System.out.println("You picked Hamster");
                        System.out.println("Hamster price 100.. age... ");
                        System.out.println("1. Buy 2. Back to menu");
                        animalSelect();
                    }
                    case 4: {
                        System.out.println("You picked Goldfish");
                        System.out.println("Goldfish price 100.. age... ");
                        System.out.println("1. Buy 2. Back to menu");
                        animalSelect();
                    }
                    case 5: {
                        System.out.println("You picked Bird");
                        System.out.println("Bird price 100.. age... ");
                        System.out.println("1. Buy 2. Back to menu");
                        animalSelect();
                    }
                    case 6: {
                        System.exit(1);
                    }
                    default:
                        System.out.println("Please pick a number between 1 and 5");
                        animalSelect();


                }
            } else {
                System.out.println("Invalid input");
                animalSelect();
            }
        }

        */
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


/*    public int getPlayerPick() {
        return playerPick;
    }

    public void setPlayerPick(int playerPick) {
        this.playerPick = playerPick;
    }



    public void setInputMoney(int inputMoney) {
        this.inputMoney = inputMoney;
    }

    public int getInputMoney() {
        return inputMoney;
    }
    //Game Class

 */
}
