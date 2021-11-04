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
     * Decision menu have the question we are asking about, and a scanner from the dialog class.
     * @param player
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
     *Decision menu have our store options
     * @return
     */
    public int decisionMenu() {
        int answer = Dialog.dialog(
                "─────────────────────" +
                        "\n    Stores:" +
                        "\n[1] Animal store" +
                        "\n[2] Food store" +
                        "\n[3] Sell animal" +
                        "\n[4] Exit store" +
                        "\n─────────────────────");
        return answer;
    }

    public void sellAnimalsShop(Player player) {
        boolean menuCheck = true;
        if (player.animals.size() > 0) {
            while (menuCheck) {
                player.getMoney();
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

                    //If-statement does not work correctly
                    if (player.checkWithPlayer(player.ableToSellAnimals)) {
                        sellAnimal(player.animals.get(index - 1), player);
                    }
                }
            }
        } else {
            System.out.println("You have no animals to sell");
        }
    }

    public int leaveStore() {
        int answer = Dialog.dialog("[ 0 ] -- Leave store");
        return answer;
    }


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
                        "\n─────────────────────");

        return answer;
    }

    public void animalShop(Player player) {

        boolean checkMenu = true;
        while (checkMenu) {
            player.playerInv();
            int pick = animalSelect();

            switch (pick) {

                case 1 -> addAnimals(player, new Bird());     //Should be "addAnimals(player, new Bird());
                case 2 -> addAnimals(player, new Cat());
                case 3 -> addAnimals(player, new Dog());
                case 4 -> addAnimals(player, new Goldfish());
                case 5 -> addAnimals(player, new Hamster());
                case 6 -> checkMenu = false;
            }
        }

    }

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
                        "\n─────────────────────");
        return answer;
    }


    public void sellAnimal(Animal animal, Player player) {
            System.out.println("Do you want to sell animal " + animal.getName() + " for " + animal.currentPriceAnimal());
        int pick = yesno();
        if (pick == 1) {
            player.addMoney(animal.currentPriceAnimal());
            player.animals.remove(animal);
            player.falseStatistics();
            player.setAbleToSellAnimals(true);
        }
    }

    public int yesno() {
        int answer = Dialog.dialog("1.Yes 2.No");
        return answer;
    }

    public void addAnimals(Player player, Animal animal) {
        if (player.checkWithPlayer(player.ableToBuyAnimals)) {
            if (player.getMoney() < animal.getStartPrice()) {
                System.out.println("Not enough money for the animal");
            } else {
                System.out.println("Do you want to buy a " + animal.getAnimalBreed() + " for " + animal.getStartPrice() + " coins ?");
                int choice = decision();

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


    public int decision() {
        int answer = Dialog.dialog("1. Yes 2. No");
        return answer;
    }

    public int maleFemale() {
        int answer = Dialog.dialog("Pick gender: 1. Male 2. Female");
        return answer;
    }

    private void afterPurchaseAnimal(Player player, Animal animal) {
        player.animals.add(animal);
        player.removeMoney(animal.getStartPrice());
        player.falseStatistics();
        player.setAbleToBuyAnimals(true);
    }


    public void addFood(Food food, Player player) {
        if (player.checkWithPlayer(player.ableToBuyFoods)) {
            if (player.getMoney() < food.getPrice()) {
                System.out.println("Not enough money");
            } else {
                int foodCount = 0;
                System.out.println("1kg of " + food.getName() + " for " + food.getPrice() + "coins");
                int yesNo = decisionFood();
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

    public int decisionFood() {
        int answer = Dialog.dialog("1. Yes 2. No");
        return answer;
    }


}
