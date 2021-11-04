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
     * @param game
     */
    public void winnerPick(Game game) {
        int score = 0;
        int index = 0;
        if (game.players.size() == 0) {
            System.out.println("No winners");
        } else {
            autoSellAnimals(game);
            for (int i = 0; i < game.players.size(); i++) {
                if (game.players.get(i).getMoney() > score) {
                    score = game.players.get(i).getMoney();
                    index = i;
                }
            }
            System.out.println("Winner is: " + game.players.get(index).getName() + " with: "
                    + game.players.get(index).getMoney() + " KR");
            for (Player player : game.players) {
                System.out.println(player.getName() + ": " + player.getMoney() + " KR");
            }
            for (Player player : game.loss) {
                System.out.println(player.getName() + ": " + player.getMoney() + " KR");
            }
        }
    }


    /**
     * When this method is called, the price of each animal is calculated and then sold.
     * @param game
     */
    public void autoSellAnimals(Game game) {
        // Game.newScreen();
        System.out.println("Game is ending, all animals have been sold.");
        for (Player player : game.players) {

            for (int i = 0; i < player.animals.size(); i++) {
                player.addMoney(player.animals.get(i).currentPriceAnimal());
                player.animals.remove(player.animals.get(i));
                i--;
            }
        }
    }


    /**
     * This method is for decreasing the health of an animal. The damage taken differs from 10 to 20
     * and is randomly generated.
     * @param player
     */
    public void animalsHealth(Player player) {
        Random random = new Random();
        for (Animal animal : player.animals) {
            int dmg = 10 + random.nextInt(20); //Lowest health 10, random.nextInt randomize 1-20.
            animal.setHealth(animal.getHealth() - dmg);
            animal.setHealthDifference(dmg);
        }
    }

    /**
     * animalAge declares the age of an animal to 1. Since every time an animal is breed, the age
     * is set to: 1.
     * @param player
     */
    public void animalAge(Player player) {
        for (Animal animal : player.animals)
            animal.setAge(1);
    }

    /**
     * The playerLoss method lets us check if the current player have lost the game or not.
     * It is using an if-statement to check the players animal inventory and the players money.
     * If the player has lost the game it will be removed from the game.
     * @param player Lets us get information from the Player class
     * @param game Lets us get information from the game class
     */
    public void playerLoss(Player player, Game game) {
        if (game.players.size() > 2) {
            if (player.animals.size() <= 0 && player.getMoney() <= 0) {
               // System.out.println("\n".repeat(30));
                figure();
                System.out.println(player.getName() + " have no money, animals or animal food, you have lost the game");
                game.players.remove(player);
                game.loss.add(player);
            }
        }
        else {
            if (player.animals.size() <= 0 && player.getMoney() <= 0) {
              //  System.out.println("\n".repeat(30));
                figure();
                System.out.println(player.getName() + " have no money, animals or animal food, you have lost the game");

            game.players.remove(player);
            game.loss.add(player);
            //autoSellAnimals(game);
            winnerPick(game);
            System.out.println("\n".repeat(1));
            new Game();
            }
        }
    }

    /**
     * Method that is in the start of every round.
     * This method lets us check the age of the current players animals and if any animals are dead
     * @param player Lets us get information from the Player class
     */
    public void startRound(Player player) {
        animalAge(player);
        checkDeadAnimals(player);
    }

    /**
     * Method that checks if the current player will be able to play more games.
     * If the player have no food, animals or money the player will lose the game.
     * @param player Lets us get information from the Player class
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
                ██║  ███╗███████║██╔████╔██║█████╗ \s
                ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝ \s
                ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗
                 ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝
                                                   \s
                 ██████╗ ██╗   ██╗███████╗██████╗  \s
                ██╔═══██╗██║   ██║██╔════╝██╔══██╗ \s
                ██║   ██║██║   ██║█████╗  ██████╔╝ \s
                ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗ \s
                ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║ \s
                 ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝ \s
                                                   \s""");
        System.out.println("\n".repeat(1));
    }

    /**
     * Method that is using an if-statement to check whether the animal is dead or not.
     * If the animal died because of the age, then it will be added to an array list
     * If it died because the health, it will be added to an array List aswell
     * @param player Lets us get information from the Player class
     */
    public void checkDeadAnimals(Player player) {
        ArrayList<Animal> deadBcusHealth = new ArrayList<>();
        ArrayList<Animal> deadBcusAge = new ArrayList<>();

        for (int i = 0; i < player.animals.size(); i++) {
            if (player.animals.get(i).getHealth() < 1) {
                deadBcusHealth.add(player.animals.get(i));
                player.animals.remove(player.animals.get(i));
                i--;
            } else if (player.animals.get(i).getAge() > player.animals.get(i).getMaxAge()) {
                deadBcusAge.add(player.animals.get(i));
                player.animals.remove(player.animals.get(i));
                i--;
            }
        }
        if (deadBcusAge.size() > 0 || deadBcusHealth.size() > 0) {
            System.out.println("---");
        }
        if (deadBcusHealth.size() > 0) {
            System.out.println("Player [" + player.getName() + "] you have animals that died because low health");
            for (Animal animal : deadBcusHealth) {
                System.out.println("Animal [" + animal.getName() + "] died because of low health");
            }
        }
        if (deadBcusAge.size() > 0) {
            System.out.println("Player [" + player.getName() + "] you have animals that died because old age");
            for (Animal animal : deadBcusAge) {
                System.out.println("Animal [" + animal.getName() + "] died because of old age");
            }
        }
    }


}
