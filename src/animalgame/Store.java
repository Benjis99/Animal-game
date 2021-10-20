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


    public void buyMenu(Player player){
        boolean menuCheck = true;
        while (menuCheck){
            player.playerInv();
            int pick = decisionMenu();

            switch (pick){
                case 1 -> animalShop(player);
                case 2 -> foodShop(player);
                case 3 -> sellAnimalsShop(player);
                case 4 -> menuCheck = false;

            }
        }
    }
    public int decisionMenu(){
        int answer = Dialog.dialog("1.Animal store 2.Food store 3.Sell animal 4.Exit store");
        return answer;
    }

    public void sellAnimalsShop(Player player){
        boolean menuCheck = true;
        if (player.animals.size() > 0) {
            while (menuCheck) {
                player.getMoney();
                System.out.println("What animal do you want to sell?");
                int count = 1;
                for (Animal animal : player.animals) {
                    System.out.println(count + " : " + animal.getName() + " : " + animal.getGender() + " : " + animal.getAnimalBreed()
                    + " : " + animal.getHealth() + "% health" + " Price: " +animal.currentPriceAnimal());
                    count++;
                }
                int index = leaveStore();
                if (index <= player.animals.size() && index == 0) {
                    menuCheck = false;
                } else {

                    //If-statement does not work correctly
                    if(player.checkWithPlayer(player.ableToSellAnimals)) {
                        sellAnimal(player.animals.get(index - 1), player);
                    }
                }
            }
        }
    }

    public int leaveStore(){
        int answer = Dialog.dialog("0 - Leave store");
        return answer;
    }



    public void foodShop(Player player){

        boolean menuCheck = true;
        while (menuCheck) {
        player.playerInv();
        System.out.println("You are now in the Food shop! Your options: ");
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

    public int foodSelect(){
        int answer = Dialog.dialog("1.DryFood for cats 2.DryFood for dogs 3.FishFeed for fish " +
                "4.Pellets for Hamster 5.Seeds for Bird 6.Exit food shop");
        return answer;
    }

    public void animalShop(Player player){

        boolean checkMenu = true;
        while (checkMenu) {
            player.playerInv();
            System.out.println("You are now in the Animal store, this is your options: " );
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

    public int animalSelect(){
        int answer = Dialog.dialog("1.Bird \n2.Cat \n3.Dog \n4.Goldfish \n5.Hamster \n6.Exit Animal shop");
        return answer;
    }



    public void sellAnimal(Animal animal, Player player){
        System.out.println("Do you want to sell animal " + animal.getName() + " for " + animal.currentPriceAnimal());
        int pick = yesno();
        if (pick == 1){
            player.addMoney(animal.currentPriceAnimal());
            player.animals.remove(animal);
            player.falseStatistics();
            player.setAbleToSellAnimals(true);
        }
    }
    public int yesno(){
        int answer = Dialog.dialog("1.Yes 2.No");
        return answer;
    }

    public void addAnimals(Player player, Animal animal){
        if (player.checkWithPlayer(player.ableToBuyAnimals)) {
            if (player.getMoney() < animal.getStartPrice()){
                System.out.println("Not enough money for the animal");
            } else {
                System.out.println("Do you want to buy " + animal.getAnimalBreed() + " for " + animal.getStartPrice());
                int choice = decision();

                if (choice == 1){
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

    public int decision(){
        int answer = Dialog.dialog("1. Yes 2. No");
        return answer;
    }
    public int maleFemale (){
        int answer = Dialog.dialog("Pick gender: 1. Male 2. Female");
        return answer;
    }

    private void afterPurchaseAnimal(Player player, Animal animal) {
        player.animals.add(animal);
        player.removeMoney(animal.getStartPrice());
        player.falseStatistics();
        player.setAbleToBuyAnimals(true);
    }


    public void addFood(Food food, Player player){
        if (player.checkWithPlayer(player.ableToBuyFoods)){
            if (player.getMoney() < food.getPrice()){
                System.out.println("Not enough money");
            } else {
                int foodCount = 0;
                System.out.println("1kg of " + food.getName() + " for " + food.getPrice());
                int yesNo = decisionFood();
                if (yesNo == 1){
                    if (player.foods.size() > 0){
                        for (Food food1: player.foods){
                            if (food1.getName().equals(food.getName())) {
                                food1.setKg(1);
                                player.removeMoney(food.getPrice());
                                player.falseStatistics();
                                player.setAbleToBuyFoods(true);
                                foodCount++;
                            }
                        }
                    }
                    if (foodCount == 0){
                        player.foods.add(food);
                        player.removeMoney(food.getPrice());
                        player.falseStatistics();
                        player.setAbleToFeed(true);
                    }
                }
            }
        }
    }

    public int decisionFood(){
        int answer = Dialog.dialog("1. Yes 2. No");
        return answer;
    }




}
