package animalgame;

import food.models.Food;
import animals.models.*;


import java.util.ArrayList;


/**
 * This is our Player class where we have all the information about the players.
 *
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class Player{

    private final String name; // final
    public boolean ableToFeed;
    public boolean ableToSellAnimals;
    public boolean ableToBuyAnimals;
    public boolean ableToBuyFoods;
    public boolean ableToBreed;
    private int money = 400;

    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Food> foods = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void trueStatistics(){
        ableToFeed = true;
        ableToSellAnimals = true;
        ableToBuyAnimals = true;
        ableToBuyFoods = true;
        ableToBreed = true;
    }
    public void falseStatistics(){
        ableToFeed = false;
        ableToSellAnimals = false;
        ableToBuyAnimals = false;
        ableToBuyFoods = false;
        ableToBreed = false;
    }



    public void removeMoney(int money){
        this.money = this.money - money;
    }
    public void addMoney(int money){
        this.money = this.money + money;
    }

    public String getName() {
        return name;
    }

    public void getAnimalFood(){
    if (foods.size() > 0) {
        System.out.println("This is your food");
        int count = 1;
        for (Food food: foods) {
            System.out.println(count + " "+ food.getName() + " " + food.getKg() + "kg");
            count++;
        }
        System.out.println("--------");
    }
    }

    public void getPlayerAnimal(){ //Method that prints out the players animals with information
        if (animals.size() > 0){
            System.out.println("This is your animals");
            int counter = 1;
            for (Animal animal : animals) {
                System.out.println("|"+counter+"|" +" "+ animal.getName() +" ---- "+ animal.getAnimalBreed() + " " + animal.getGender() +
                        " " +animal.getHealth() + " % health." + " Age: " + animal.getAge() );
                counter++;
            }
            System.out.println("-------");
        }
    }

    public void animalFeeding(Player player){ //Pick one animal to feed in the first try-catch

        int pick1 = 0;
        int pick2 = 0;
        if (checkWithPlayer(ableToFeed)) {


            if (player.animals.size() > 0 && player.foods.size() > 0) { //if statement with try-catch inside.
                while (pick1 < 1 || pick1 > animals.size()){
                try {
                    Game.newScreen(); //New screen, easy to see the information
                    getPlayerAnimal(); //This will get the players animal
                    System.out.println("Which animal do you want to feed: ");
                    pick1 = Integer.parseInt(Dialog.stringReturn()); //We need a scanner from game class here
                } catch (Exception e) {
                    System.out.println("You need to enter a number");
                    Game.continueButton();
                 }
                }
                while (pick2 < 1 || pick2 > foods.size()) {  //While loop with try-catch inside.
                    try {
                        Game.newScreen();
                        getAnimalFood();
                        System.out.println("\n Pick the food you wanna use");
                        pick2 = Integer.parseInt(Dialog.stringReturn());
                    } catch (Exception e) {
                        System.out.println("You need to enter a number");

                    }
                }
                Food food = foods.get(pick2 - 1);
                Animal animal = animals.get(pick1 - 1);

                if (animal.eatTrue(food)) {
                    animal.eatFood(food);
                    food.setKg(-1);
                    player.falseStatistics();
                    player.setAbleToFeed(true);
                    if (food.getKg() <= 0){
                        foods.remove(food);
                    }
                } else {
                    System.out.println("Wrong food for the animal");
                }
            } else {
                System.out.println("All the animals are full");
            }
        }
    }

    public void playerInv(){
       getPlayerAnimal();
       getAnimalFood();
       getBalance();
    }
    public void getBalance(){
        System.out.println("Current balance: " + this.money + " Swedish kronor");
    }

    public boolean checkWithPlayer(boolean checkPlayer){
        if (!checkPlayer){
            System.out.println("To many choices this turn");
            return false;
        }
        return true;
    }

    public int getMoney() {
        return this.money;
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

} //Player Class
