package animalgameholder.animalgame;

import animalgameholder.animalgame.animals.Animal;
import animalgameholder.animalgame.foods.Food;

import java.util.ArrayList;
import java.util.Scanner;


public class Player{
    private final String name; // final
    public boolean ableToFeed;
    public boolean ableToSellAnimals;
    public boolean ableToBuyAnimals;
    public boolean ableToBuyFoods;
    public boolean ableToBreed;

    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Food> foods = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void trueBooleans(){
        ableToFeed = true;
        ableToSellAnimals = true;
        ableToBuyAnimals = true;
        ableToBuyFoods = true;
        ableToBreed = true;
    }
    public void falseBooleans(){
        ableToFeed = false;
        ableToSellAnimals = false;
        ableToBuyAnimals = false;
        ableToBuyFoods = false;
        ableToBreed = false;
    }

    public String getName() {
        return name;
    }

    public void getPlayerFood(){
    if (foods.size() > 0) {
        System.out.println("This is your food");
        int count = 1;
        for (Food food: foods) {
            System.out.println(count + " "+ food.getName() + " " + food.getKg() + "kg");
        }
    }
    }

    public void getPlayerAnimal(){ //Method that prints out the players animals with information
        if (animals.size() > 0){
            System.out.println("This is your animals");
            int counter = 1;
            for (Animal animal: animals) {
                System.out.println(counter + animal.getName() + animal.getAnimalBreed() + animal.getGender() +
                        animal.getHealth() + "% health." + "Age: " + animal.getAge() );
                counter++;
            }
        }
    }

    public void animalFeeding(Player player){
        Scanner console = new Scanner(System.in); //Delete when we use the Scanner from game class
        int pick1 = 0;
        int pick2 = 0;
        if (checkWithPlayer(ableToFeed)) {


            if ((player.animals.size() > 0 && player.foods.size() > 0)) { //if statement with try-catch inside.
                while (pick1 < 1 || pick1 > animals.size()){
                try {
                    Game.newScreen();
                    getPlayerAnimal();
                    System.out.println("Write the name of the animal you wanna feed: ");
                    pick1 = Integer.parseInt(console.nextLine()); //We need a scanner from game class here
                } catch (Exception e) {
                    System.out.println("You need to enter a number");
                    Game.continueButtom();
                 }
                }
                while (pick2 < 1 || pick2 > foods.size()) {  //While loop with try-catch inside.
                    try {
                        Game.newScreen();
                        getPlayerFood();
                        System.out.println("\n Pick the food you wanna use");
                        pick2 = Integer.parseInt(console.nextLine());
                    } catch (Exception e) {
                        System.out.println("You need to enter a number");

                    }
                }
                Food food = foods.get(pick2 - 1);
                Animal animal = animals.get(pick1 - 1);

                if (animal.eatTrue(food)) {
                    animal.eatFood(food);
                    food.setKg(-1);
                    player.falseBooleans();

                }
            }
        }
    }

    public boolean checkWithPlayer(boolean lean){
        if (!lean){
            System.out.println("To many choices this turn");
            return false;
        }
        return true;
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

    public void setAbleToBreed(boolean ableToBreed) {
        this.ableToBreed = ableToBreed;
    }
} //Player Class
