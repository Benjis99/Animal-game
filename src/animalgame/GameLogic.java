package animalgame;

import animals.models.Animal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is our GameLogic class where the "brain" of the game is.
 *
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class GameLogic implements Serializable {

    public GameLogic() {
    }


    /**
     * When the winnerPick method is called, we declare the winner of the game by automatically
     * selling all animals that each player owns. Depending on health and age,
     * each animal is given a value in coins, the player with the most coins wins.
     * @param game grabs information such as: player and coins
     */
    public void winnerPick(Game game) {
        int score = 0;
        int index = 0;
        if (game.players.size() == 0) {
            System.out.println("No winners");
        } else {
            autoSellAnimals(game);
            for (int i = 0; i < game.players.size(); i++) {
                if (game.players.get(i).getCoins() > score) {
                    score = game.players.get(i).getCoins();
                    index = i;
                }
            }
            System.out.println("Winner is: " + TEXT_YELLOW + game.players.get(index).getName() + TEXT_RESET + " with: "
                    + game.players.get(index).getCoins() + " C");
            for (Player player : game.players) {
                System.out.println(player.getName() + ": " + player.getCoins() + " C");
            }
            for (Player player : game.loss) {
                System.out.println(player.getName() + ": " + player.getCoins() + " C");
            }
        }
    }


    /**
     * When this method is called, the price of each animal is calculated and then sold,
     * the coins are added to the current players balance.
     * @param game grabs information such as: players
     */
    public void autoSellAnimals(Game game) {
        System.out.println("Game is ending, all animals have been sold.");
        for (Player player : game.players) {

            for (int i = 0; i < player.animals.size(); i++) {
                player.addCoins(player.animals.get(i).currentPriceAnimal());
                player.animals.remove(player.animals.get(i));
                i--;
            }
        }
    }


    /**
     * This method is for decreasing the health of an animal. The damage taken differs from 10 to 30
     * and is randomly generated.
     * @param player grabs information such as: the current players animals
     */
    public void animalsHealth(Player player) {
        Random random = new Random();
        for (Animal animal : player.animals) {
            int dmg = 11 + random.nextInt(20); //Lowest health 10, random.nextInt randomize 1-20.
            animal.setHealth(animal.getHealth() - dmg);
            animal.setHealthDifference(dmg);
        }
    }

    /**
     * The method animalAge will add 1 age to the animals in the beginning of every round.
     * @param player grabs information such as: players list of animals.
     */
    public void animalAge(Player player) {
        for (Animal animal : player.animals)
            animal.setAge(1);
    }

    /**
     * The playerLoss method lets us check if the current player has lost the game or not.
     * It is using an if-statement to check the players animal inventory and the players money.
     * If the player has lost the game it will be removed from the game.
     * @param player grabs information such as: money and player
     * @param game remove information about the player who lost
     */
    public void playerLoss(Player player, Game game) {
        if (game.players.size() > 2) {
            if (player.animals.size() <= 0 && player.getCoins() <= 0) {
                figure();
                System.out.println(player.getName() + " has no money, animals or animal food, you have lost the game!");
                game.players.remove(player);
                game.loss.add(player);
                System.out.println("Press ENTER to continue. ");
                Dialog.stringReturn();
                Game.newScreen();
            }
        }
        else {
            if (player.animals.size() <= 0 && player.getCoins() <= 0) {
                figure();
                System.out.println(player.getName() + " has no money, animals or animal food, you have lost the game!");


            game.players.remove(player);
            game.loss.add(player);
            winnerPick(game);
            System.out.println("\n".repeat(1));
            new Game();
            }
        }
    }

    /**
     * Method that is in the start of every round.
     * This method lets us check the age of the current players animals and if any animals are dead.
     * @param player grabs information such as: age of animal and dead animals
     */
    public void startRound(Player player) {
        animalAge(player);
        checkDeadAnimals(player);
    }

    /**
     * Method that checks if the current player will be able to play more games.
     * If the player have no food, animals or money the player will lose the game.
     * @param player Lets us get information from the player class
     * @param game Lets us get information from the game class
     */
    public void endRound(Player player, Game game) {
        playerLoss(player, game);
        animalsHealth(player);
        checkDeadAnimals(player);
    }


    public void figure() {
        System.out.println("""
                 ██████╗  █████╗ ███╗   ███╗███████╗
                ██╔════╝ ██╔══██╗████╗ ████║██╔════╝
                ██║  ███╗███████║██╔████╔██║█████╗ 
                ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝ 
                ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗
                 ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝
                                                   
                 ██████╗ ██╗   ██╗███████╗██████╗  
                ██╔═══██╗██║   ██║██╔════╝██╔══██╗ 
                ██║   ██║██║   ██║█████╗  ██████╔╝ 
                ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗ 
                ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║ 
                 ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝ 
                                                   """);
        System.out.println("\n".repeat(1));
    }

    /**
     * Method that is using an if-statement to check whether the animal is dead or not.
     * If the animal died because of the age, then it will be added to an array list
     * If it died because the health, it will be added to an array List as well
     * @param player Lets us get information from the Player class
     */
    public void checkDeadAnimals(Player player) {
        ArrayList<Animal> deadBecauseHealth = new ArrayList<>();
        ArrayList<Animal> deadBecauseAge = new ArrayList<>();

        for (int i = 0; i < player.animals.size(); i++) {
            if (player.animals.get(i).getHealth() < 1) {
                deadBecauseHealth.add(player.animals.get(i));
                player.animals.remove(player.animals.get(i));
                i--;
            } else if (player.animals.get(i).getAge() > player.animals.get(i).getMaxAge()) {
                deadBecauseAge.add(player.animals.get(i));
                player.animals.remove(player.animals.get(i));
                i--;
            }
        }
        if (deadBecauseAge.size() > 0 || deadBecauseHealth.size() > 0) {
            System.out.println("---");
        }
        if (deadBecauseHealth.size() > 0) {
            System.out.println(TEXT_RED + "Player [" + player.getName() + "] you have animals that died because of low health"+TEXT_RESET);
            for (Animal animal : deadBecauseHealth) {
                System.out.println(TEXT_RED + "Animal [" + animal.getName() + "] died because of low health" + TEXT_RESET);
            }
        }
        if (deadBecauseAge.size() > 0) {
            System.out.println(TEXT_RED + "Player [" + player.getName() + "] you have animals that died from getting too old"+TEXT_RESET);
            for (Animal animal : deadBecauseAge) {
                System.out.println(TEXT_RED +"Animal [" + animal.getName() + "] died because of old age"+TEXT_RESET);
            }
        }
    }

    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    public static final String TEXT_RESET = "\u001B[0m";
}
