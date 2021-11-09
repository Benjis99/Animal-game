package animalgame;

import food.models.*;
import animals.models.*;
import food.*;
import animals.*;

import java.io.Serializable;


/**
 * This is our Store class where we have all the shops etc.
 *
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class Store implements Serializable {

    public Store() {
    }


    /**
     * The buy menu is used to open our stores.
     * This method has a switch case that is simplified.
     * Menu check is true in the beginning, when you are exiting the menus it changes to false.
     * The player gets to choose what menu to enter with the decisionMenu method.
     * @param player the current player
     */
    public void buyMenu(Player player) {
        boolean menuCheck = true;
        while (menuCheck) {
            player.playerInv();
            int pick = decisionMenu();

            switch (pick) {
                case 1 -> animalShop(player);
                case 2 -> foodShop(player);
                case 3 -> sellAnimalsShop(player);
                case 4 -> menuCheck = false;

            }
        }
    }

    /**
     * The different options a player can enter while being in the store class.
     * @return the input chosen by the player
     */
    public int decisionMenu() {
        int answer = Dialog.dialog(
                "─────────────────────" +
                        "\n    Stores:" +
                        "\n[1] Animal store" +
                        "\n[2] Food store" +
                        "\n[3] Sell animal" +
                        "\n[4] Exit store" +
                        "\n─────────────────────", 1, 4);
        return answer;
    }

    /**
     * The menu is used for players to sell their animals. The method checks if a player has any animals to sell
     * and then allows the player to sell any animals the user wants to. The method will also print some
     * basic information about the animal they want to sell, such as name, age, health and price.
     * @param player the current player
     */
    public void sellAnimalsShop(Player player) {
        boolean menuCheck = true;
        if (player.animals.size() > 0) {
            while (menuCheck) {
                player.getCoins();
                System.out.println("What animal do you want to sell?");
                int count = 1;
                for (Animal animal : player.animals) {
                    System.out.println("[ " +count + " ] --" + " | " + animal.getName() + " | " + animal.getGender() + " | " + animal.getAnimalBreed()
                            + " | " +animal.getHealth() + " % health |" + " Price: " + animal.currentPriceAnimal()+ " coins |");
                    count++;
                }
                int index = leaveStore();
                if (index <= player.animals.size() && index == 0) {
                    menuCheck = false;
                } else {

                    if (player.checkWithPlayer(player.ableToSellAnimals)) {
                        sellAnimal(player.animals.get(index - 1), player);
                    }
                }
            }
        } else {
            System.out.println("You have no animals to sell");
        }
    }

    /**
     * A simple method to leave the sellAnimalsShop.
     * @return 0 if the player wants to leave the store
     */
    public int leaveStore() {
        int answer = Dialog.dialogWithoutMax("[ 0 ] -- Leave store");
        return answer;
    }

    /**
     * A method for the player to buy food for their animal(s).
     * Depending on the players input from the foodSelect method, the player can buy food for their animals.
     * The player will also get a print of what food the player currently owns.
     * @param player the current player
     */
    public void foodShop(Player player) {

        boolean menuCheck = true;
        while (menuCheck) {
            player.playerInv();
            int pick = foodSelect();
            switch (pick) {
                case 1 -> addFood(new DryFoodCat(), player);
                case 2 -> addFood(new DryFoodDog(), player);
                case 3 -> addFood(new FishFeed(), player);
                case 4 -> addFood(new Pellets(), player);
                case 5 -> addFood(new Seeds(), player);
                case 6 -> menuCheck = false;
            }
        }
    }

    /**
     * A menu for the player to select which food the player wants to buy.
     * @return the type of food the player wants to buy
     */
    public int foodSelect() {
        int answer = Dialog.dialog(
                "─────────────────────" +
                        "\n   Food store" +
                        "\n[1] DryFood for cats" +
                        "\n[2] DryFood for dogs" +
                        "\n[3] FishFeed for fish" +
                        "\n[4] Pellets for hamster" +
                        "\n[5] Seeds for bird" +
                        "\n[6] Exit food shop" +
                        "\n─────────────────────", 1, 6);

        return answer;
    }

    /**
     * A method for the players to buy animal(s).
     * Depending on the players input from the animalSelect method, the player can buy an animal.
     * The player will also get a print of what animal the player currently owns.
     * @param player the current player
     */
    public void animalShop(Player player) {

        boolean checkMenu = true;
        while (checkMenu) {
            player.playerInv();
            int pick = animalSelect();

            switch (pick) {

                case 1 -> addAnimals(player, new Bird());
                case 2 -> addAnimals(player, new Cat());
                case 3 -> addAnimals(player, new Dog());
                case 4 -> addAnimals(player, new Goldfish());
                case 5 -> addAnimals(player, new Hamster());
                case 6 -> checkMenu = false;
            }
        }

    }

    /**
     * A menu for the player to select which animal the player wants to buy.
     * @return the type of animal the player wants to buy
     */
    public int animalSelect() {
        int answer = Dialog.dialog(
                "─────────────────────" +
                        "\n   Animal store" +
                        "\n[1] Bird" +
                        "\n[2] Cat" +
                        "\n[3] Dog" +
                        "\n[4] Goldfish" +
                        "\n[5] Hamster" +
                        "\n[6] Exit Animal shop" +
                        "\n─────────────────────", 1, 6);
        return answer;
    }

    /**
     * A method for the player to sell animal(s).
     * Depending on the players input from the sellAnimalConfirmation method, the player can sell an animal.
     * The player will also get a print of what animal the player currently owns.
     * @param animal the selected animal from the sellAnimalsShop method
     * @param player the current player
     */
    public void sellAnimal(Animal animal, Player player) {
            System.out.println("Do you want to sell animal " + animal.getName() + " for " + animal.currentPriceAnimal() + " Coins? ");
        int pick = decisionMaker();
        if (pick == 1) {
            player.addMoney(animal.currentPriceAnimal());
            player.animals.remove(animal);
            player.falseStatistics();
            player.setAbleToSellAnimals(true);
        }
    }

    /**
     * A menu which confirms that the player wants to buy an animal and give the animal a name and gender.
     * If the player doesn't have enough money for the selected animal, the player will get a message saying
     * that they don't have enough money for it.
     * After an animal is bought, the said animal will be added to the players arraylist of owned animals.
     * @param player the current player
     * @param animal the type of animal selected from the animalShop method
     */
    public void addAnimals(Player player, Animal animal) {
        if (player.checkWithPlayer(player.ableToBuyAnimals)) {
            if (player.getCoins() < animal.getStartPrice()) {
                System.out.println("Not enough coins for the animal");
            } else {
                System.out.println("Do you want to buy a " + animal.getAnimalBreed() + " for " + animal.getStartPrice() + " Coins?");
                int choice = decisionMaker();

                if (choice == 1) {
                    System.out.println("Enter the name for your " + animal.getAnimalBreed() + ": ");
                    animal.setName(Dialog.stringReturn());

                    System.out.println("Pick the gender for " + animal.getAnimalBreed());
                    int input = maleFemale();

                    if (input == 1) animal.setGender("MALE");
                    if (input == 2) animal.setGender("FEMALE");
                    afterPurchaseAnimal(player, animal);
                }
            }
        }
    }

    /**
     * Allows the player to select if they want to buy a male or female animal in the addAnimals method
     * @return male if input is 1, female if input is 2
     */
    public int maleFemale() {
        int answer = Dialog.dialog("Pick gender: 1. Male 2. Female", 1, 2);
        return answer;
    }

    /**
     * This method will add the animals that was bought in the addAnimals to the players ArrayList of animals as well
     * as removing the cost of the animals from the players current balance.
     * @param player the current player
     * @param animal the selected animal from the animalShop method
     */
    private void afterPurchaseAnimal(Player player, Animal animal) {
        player.animals.add(animal);
        player.removeMoney(animal.getStartPrice());
        player.falseStatistics();
        player.setAbleToBuyAnimals(true);
    }


    /**
     * A method for the player to buy food for their animal(s). The method will inform the player with
     * information about food price in kilos.
     * If the player bought food, it will be added to an Arraylist with foods.
     *
     * @param food lets us get information from the Food class
     * @param player lets us get information from the Player class
     */
    public void addFood(Food food, Player player) {
        if (player.checkWithPlayer(player.ableToBuyFoods)) {
            if (player.getCoins() < food.getPrice()) {
                System.out.println("Not enough coins");
            } else {
                int foodCount = 0;
                System.out.println("1kg of " + food.getName() + " for " + food.getPrice() + " coins");
                int yesNo = decisionMaker();
                if (yesNo == 1) {
                    if (player.foods.size() > 0) {
                        for (Food food1 : player.foods) {
                            if (food1.getName().equals(food.getName())) {
                                food1.setKg(1);
                                player.removeMoney(food.getPrice());
                                player.falseStatistics();
                                player.setAbleToBuyFoods(true);
                                foodCount++;
                            }
                        }
                    }
                    if (foodCount == 0) {
                        player.foods.add(food);
                        player.removeMoney(food.getPrice());
                        player.falseStatistics();
                        player.setAbleToBuyFoods(true);
                    }
                }
            }
        }
    }

    /**
     * A simple confirmation message asking the user if they want to sell the selected animal.
     * @return Yes if input is 1, no if input is 2
     */
    public int decisionMaker() {
        int answer = Dialog.dialog("1.Yes 2.No", 1, 2);
        return answer;
    }



}
