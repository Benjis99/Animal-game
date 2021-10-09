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
    ArrayList<Food> food = new ArrayList<>();

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
    if (food.size() > 0) {
        System.out.println("This is your food");
        int count = 1;
        for (Food food: food) {
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
        Scanner console = new Scanner(System.in);
        int pick1 = 0;
        int pick2 = 0;
        if (checkWithPlayer(ableToFeed)) {

            //Delete when we use the Scanner from game class
            if ((player.animals.size() > 0 && player.food.size() > 0)) { //if statement with try-catch inside.
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


} //Player Class
