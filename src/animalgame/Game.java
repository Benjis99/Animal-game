package animalgame;

import java.util.Scanner;

public class Game {
Scanner console = new Scanner(System.in);

private int playerPick;
private String playerName;
private String[] createdPlayers = new String[5];



    Bird bird;
    Cat cat;
    DryFoodDog dryFood;
    Dog dog;
    Player player;
    public Game() {
      //  this.dog = new Dog();
      //  this.cat = new Cat();
      //  this.dryFood = new DryFoodDog();
        amountOfPlayers();
    }


    public void amountOfPlayers(){ //Method that will check how many players the user picked, and check it with the if else statement.
        System.out.println("How many players do you want? 2-4");
         setPlayerPick(console.nextInt());
        if (getPlayerPick() < 2 || getPlayerPick() > 4){
            System.out.println("Invalid amount of players, you must choose between 2-4");
            amountOfPlayers();
        } else{
            System.out.println("You picked " + getPlayerPick() + " players.");
            createPlayer();

        }
    }
    public void createPlayer(){
        System.out.println("What your usernames?"); // Enter all names of players and loops through amount of players chosen

        for (int i = 0; i <= getPlayerPick(); i++) {
            //setPlayerNames(console.nextLine());
            createdPlayers[i] = console.nextLine();
            //playerName = console.nextLine();
        }
        System.out.println("\nPlayer selected: ");
        for (int k = 1; k <= getPlayerPick(); k++){
            System.out.println(createdPlayers[k]); // Loops through the array and prints all the selected players
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
        int input = console.nextInt();
        if (input < 0 || input > 2000000000) {
            System.out.println("Invalid amount of money, try again. 0-2000000000 Swedish Krona");
            amountOfStartMoney();
        }
            else{
                System.out.println("You have picked " + input + " Swedish Krona");
                animalSelect();
            }
        }

        public void animalSelect(){ //1. for cat, 2. for dog, 3. for hamster, 4. for goldfish, 5. for bird
            System.out.println("1. For Cat, 2. For Dog, 3. For Hamster, 4. For Goldfish, 5. For Bird");
            int input = console.nextInt();
            if (input == 1){
                System.out.println("You picked Cat");
            }
            else if (input == 2){
                System.out.println("You picked Dog");
            }
            else if (input == 3){
                System.out.println("You picked Hamster");
            }
            else if (input == 4){
                System.out.println("You picked Goldfish");
            }
            else if (input == 5){
                System.out.println("You picked Bird");
            }
            else
            {
                System.out.println("Please pick a number between 1 and 5");
                animalSelect();
            }
        }


/*    public void setCreatedPlayers(String[] createdPlayers) {
        this.createdPlayers = createdPlayers;
    }*/

    public int getPlayerPick() {
        return playerPick;
    }

    public void setPlayerPick(int playerPick) {
        this.playerPick = playerPick;
    }

    public void setPlayerNames(String playerName) {
        this.playerName = playerName;
    }

    //Game Class
}
