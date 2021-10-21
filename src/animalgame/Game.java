package animalgame;

import gameSave.SaveGame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is our Game class where we have all the menus right now
 * <p>
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class Game implements Serializable { // sss

private int amountOfTurns;
private int currentTurn;
private Player currentPlayer;
private int playerIndex;
private int numberOfPlayers = 0;
boolean exit = false;
SaveGame saveGame = new SaveGame();

private final GameLogic logic = new GameLogic();
ArrayList<Player> players = new ArrayList<>();
ArrayList<Player> loss = new ArrayList<>();



    public Game() {
        saveGame.setGame(this);

        boolean start = true;
        while (start) {

            players.clear();
            //newScreen();
            System.out.println("Welcome to our animal game!");

            int first = Dialog.dialog("1. Start game 2. Information 3. Load Game 4. Exit Game");

            switch (first) {
                case 1 -> startMenu();
                case 2 -> gameRules();
                case 3 -> saveGame.loadGame(); //Printar ut när man går in på information
                case 4 -> {
                    boolean exit = true;
                    while (exit) {
                        int input2 = Dialog.dialog("1.Start game \n2.Turn off game");
                        if (input2 == 1) {
                            start = false;
                            exit = false;
                        }
                        if (input2 == 2) {
                            System.out.println("Turning off game");
                            System.exit(1);
                            exit = false;
                        }
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
        playerName();
        information();
        gameMenu();

    }

    public void gameBrain(){
        Store store = new Store();
        Breeding breeding = new Breeding();
        boolean game = true;

        while (game) {
            int pick = 0;

            while (pick < 1 || pick > 7){
                currentPlayer.playerInv();
                System.out.println("It is round " + currentTurn);
                System.out.println(currentPlayer.getName() + " turn");
                pick = menuStore();

            }

            switch (pick) {
                case 1 -> store.buyMenu(currentPlayer);
                case 2 -> breeding.animalBreed(currentPlayer);
                case 3 -> currentPlayer.animalFeeding(currentPlayer);
                case 4 -> game = false;
                case 5 -> information();
                case 6 -> saveGame.saveGame(this);
                case 7 -> {
                    game = false;
                    exit = true;
                }
            }
        }
    }

    public int menuStore(){
        int input = Dialog.dialog("[1.Store]" + " [2.Breed]" + " " +
                "[3.Feed Animal]" + " [4.Next Player]"+ " [5.Game Info]"+ " " + "[6. Save game]" + " [7.Exit to main menu]" );
        return input;
    }

    public void playerName(){
        newScreen();
        System.out.println("You picked " + numberOfPlayers + " amount of players");
        for (int i = 1; i < numberOfPlayers + 1; i++ ) {
            System.out.println("Player " + i + " pick your name: ");
            String name = Dialog.stringReturn();
            players.add(new Player(name));
        }
    }

    public void gameMenu(){ //comment

        for (int pick = currentTurn; pick < amountOfTurns + 1; pick++) {

            for (int pick1 = playerIndex; pick1 < players.size(); pick1++){
                currentPlayer = players.get(pick1);
                currentPlayer.trueStatistics();

                logic.startRound(currentPlayer);
                gameBrain();
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
            pick = Dialog.intReturn();
        } catch (Exception e) {
            Dialog.intReturn();
        }
        return pick < min || pick > max ?
                gameSettings(text, min, max) : pick;
    }



    public void gameRules(){
        System.out.println("All the game rules");
    }

    public void information (){
        System.out.println("Current statistics: ");
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
            System.out.println("\n");
            System.out.println("Press enter to continue");
            Dialog.stringReturn();
        }
      public static void newScreen(){ //Static so we can reach outside Game class
        System.out.println("\n".repeat(50));
      }

        public void foodSelect(){
            System.out.println("food");
        }





}
