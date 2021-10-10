package animalgameholder.animalgame;

import animalgameholder.animalgame.animals.Animal;
import animalgameholder.animalgame.foods.Food;

import java.util.Scanner;

public class Store{
    Scanner console = new Scanner(System.in); //Use the scanner from game, delete this one

    public Store() {
    }


    public void buyMenu(Player player){
        boolean menuCheck = true;
        while (menuCheck){
            player.playerInv();
            System.out.println("1. Store 2. Buy Animals 3. Buy Food 4. Sell Animals 5. Exit store");
            int pick = console.nextInt();

            switch (pick){
                case 1:

                case 2:

                case 3:

                case 4:
                    menuCheck = false;

            }
        }
    }



    public void foodShop(Player player){
        boolean menuCheck = true;
        player.playerInv();
        System.out.println("Food shop! 1. DryFood for cats 2. DryFood for dogs 3. FishFeed for fish " +
                "4. Pellets for Hamster 5. Seeds for Bird 6. Exit food shop");
        int pick = console.nextInt();
        switch (pick){
            case 1:

            case 2:

            case 3:

            case 4:

            case 5:

            case 6:
        }
    }

    public void animalShop(Player player){

        boolean checkMenu = true;
        while (checkMenu) {
            player.playerInv();
            System.out.println("Animal store 1. Bird 2. Cat 3. Dog 4. Goldfish 5. Hamster 6. Exit program" );
            int pick = console.nextInt();
            switch (pick) {

                case 1:

                case 2:

                case 3:

                case 4:

                case 5:

                case 6:

            }
        }

    }

    public void sellAnimalShop(Animal animal, Player player){
        System.out.println("Do you want to sell animal " + animal.getName() + " for " + animal.currentPriceAnimal());
        int pick = console.nextInt();
        if (pick == 1){
            player.addMoney(animal.currentPriceAnimal());
            player.animals.remove(animal);
            player.falseBooleans();
            player.setAbleToSellAnimals(true);
        }
    }

    public void addAnimals(Animal animal, Player player){

        if (player.checkWithPlayer(player.ableToFeed)) {
            if (player.getMoney() < animal.getStartPrice()){
                System.out.println("Not enough money for the animal");
            } else {
                System.out.println("Do you want to buy " + animal.getAnimalBreed() + " for " + animal.getStartPrice() + " 1. Yes 2. No");
                int choice = console.nextInt();

                if (choice == 1){
                    System.out.println("Enter the name for your " + animal.getAnimalBreed() + ": ");
                    animal.setName(console.nextLine());

                    System.out.println("Pick the gender for " + animal.getAnimalBreed() + " Pick gender: 1. Male 2. Female");
                    int input = console.nextInt();

                    if (input == 1) animal.setGender("MALE");
                    if (input == 2) animal.setGender("FEMALE");
                }
            }
        }
    }

    public void addFood(Food food, Player player){
        if (player.checkWithPlayer(player.ableToBuyFoods)){
            if (player.getMoney() < food.getPrice()){
                System.out.println("Not enough money");
            } else {
                int foodCount = 0;
                System.out.println("1k of " + food.getName() + " for " + food.getPrice() + " 1. Yes 2. No");
                int yesNo = console.nextInt();
                if (yesNo == 1){
                    if (player.foods.size() > 0){
                        for (Food food1: player.foods){
                            if (food1.getName().equals(food.getName())) {
                                food1.setKg(1);
                                player.removeMoney(food.getPrice());
                                player.falseBooleans();
                                player.setAbleToBuyFoods(true);
                                foodCount++;
                            }
                        }
                    }
                    if (foodCount == 0){
                        player.foods.add(food);
                        player.removeMoney(food.getPrice());
                        player.falseBooleans();
                        player.setAbleToFeed(true);
                    }
                }
            }
        }
    }




}
