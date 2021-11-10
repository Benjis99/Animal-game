package animalgame;

import food.models.Food;
import animals.models.*;


import java.io.Serializable;
import java.util.ArrayList;


/**
 * This is our Player class where we have all the information about the players.
 *
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class Player implements Serializable {

    private final String name;
    private int coins = 4000;
    private boolean ableToFeed;
    public boolean ableToSellAnimals;
    public boolean ableToBuyAnimals;
    public boolean ableToBuyFoods;
    public boolean ableToBreed;

    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Food> foods = new ArrayList<>();

    /**
     * Our constructor have String name inside it, which lets us
     * create players with different names.
     * @param name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * We have all our true booleans together which lets us open them with one method.
     * If a player have made a decision to buy food, it will not be able to
     * sell animals etc.
     */
    public void trueStatistics() {
        ableToFeed = true;
        ableToSellAnimals = true;
        ableToBuyAnimals = true;
        ableToBuyFoods = true;
        ableToBreed = true;
    }

    /**
     * We have all our false booleans together which lets us close them with one method.
     * If a player have made a decision to buy food, it will not be able to
     * sell animals etc.
     */
    public void falseStatistics() {
        ableToFeed = false;
        ableToSellAnimals = false;
        ableToBuyAnimals = false;
        ableToBuyFoods = false;
        ableToBreed = false;
    }

    /**
     * This is our method to find out how much food the current player have.
     * It will show you the amount of food, the name of the food and the amount of kg.
     */
    public void getAnimalFood() {
        if (foods.size() > 0) {
            System.out.println("This is your food");
            int count = 1;
            for (Food food : foods) {
                System.out.println("[" + count + "]" + " " + food.getName() + " " + food.getKg() + "kg");
                count++;
            }
            System.out.println("─────────────────────");
        }
    }

    /**
     * Method that prints out the players animals and the information.
     * It will show the name, type of animal, gender, health, age and how much health the animal has lost.
     */
    public void getPlayerAnimal() { //Method that prints out the players animals with information
        if (animals.size() > 0) {
            System.out.println("─────────────────────");
            System.out.println("These are your animals");
            int counter = 1;
            for (Animal animal : animals) {
                System.out.println("[" + counter + "]" + " " + animal.getName() + " ─ " + animal.getAnimalBreed() + " " + animal.getGender() +
                        " " + animal.getHealth() + " % health." + " Age: " + animal.getAge());
                System.out.println(animal.getName() + " lost " + animal.getHealthDifference() + " health this turn.");
                counter++;
            }
            System.out.println("─────────────────────");
        }
    }

    /**
     * AnimalFeeding method will let the current player feed the animals.
     * It will show your inventory of animals and let you pick which food you want to feed your animal.
     * If the current player have no animals or food, then it won't be able to feed.
     * @param player allows us to get the current players inventory
     */
    public void animalFeeding(Player player) { //Pick one animal to feed in the first try-catch

        int pick1 = 0;
        int pick2 = 0;
        if (checkWithPlayer(ableToFeed)) {

            if (player.animals.size() > 0 && player.foods.size() > 0) { //if statement with try-catch inside.
                while (pick1 < 1 || pick1 > animals.size()) {
                    try {
                        getPlayerAnimal(); //This will get the players animal
                        System.out.println("Which animal do you want to feed: ");
                        pick1 = Integer.parseInt(Dialog.stringReturn()); //We need a scanner from game class here
                    } catch (Exception e) {
                        System.out.println("You need to enter a number!");
                    }
                }
                while (pick2 < 1 || pick2 > foods.size()) {  //While loop with try-catch inside.
                    try {
                        getAnimalFood();
                        System.out.println("\nPick the food you wanna use");
                        pick2 = Integer.parseInt(Dialog.stringReturn());
                    } catch (Exception e) {
                        System.out.println("You need to enter a number!");

                    }
                }
                Food food = foods.get(pick2 - 1);
                Animal animal = animals.get(pick1 - 1);

                if (animal.eatTrue(food)) {
                    animal.eatFood(food);
                    food.setKg(-1);
                    player.falseStatistics();
                    player.setAbleToFeed(true);
                    if (food.getKg() <= 0) {
                        foods.remove(food);
                    }
                } else {
                    System.out.println("Wrong food for the animal");
                }
            }
            if (player.animals.size() < 1 || player.foods.size() < 1) { //If the player don't have one animal and one sorts of food, it won't be able to eat
                System.out.println("You need one animal and one sorts of food to be able to feed!");
            }
        }
    }

    /**
     * PlayerInv lets us check what the current player owns and current balance.
     * We are using getters to get animals, food and balance.
     */
    public void playerInv() {
        getPlayerAnimal();
        getAnimalFood();
        getBalance();
    }

    /**
     * If the current player made the decision to buy animals, he must buy
     * animals for all his money or end his turn.
     * This method will check if the player is trying to buy something else.
     * @param checkPlayer will check if the players options are closed
     * @return too many choices this turn - false
     */
    public boolean checkWithPlayer(boolean checkPlayer) {
        if (!checkPlayer) {
            System.out.println("Too many choices this turn");
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void removeCoins(int coins) {
        this.coins = this.coins - coins;
    }

    public void addCoins(int coins) {
        this.coins = this.coins + coins;
    }


    public void getBalance() {
        System.out.println("Current balance: " + this.coins + " Coins");
    }


    public int getCoins() {
        return this.coins;
    }

    public void setAbleToFeed(boolean ableToFeed) {
        this.ableToFeed = ableToFeed;
    }

    public void setAbleToSellAnimals(boolean ableToSellAnimals) {
        this.ableToSellAnimals = ableToSellAnimals;
    }

    public void setAbleToBuyAnimals(boolean ableToBuyAnimals) {
        this.ableToBuyAnimals = ableToBuyAnimals;
    }

    public void setAbleToBuyFoods(boolean ableToBuyFoods) {
        this.ableToBuyFoods = ableToBuyFoods;
    }

}
