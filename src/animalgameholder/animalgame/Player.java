package animalgameholder.animalgame;

import animalgameholder.animalgame.animals.Animal;
import animalgameholder.animalgame.foods.Food;

import java.util.ArrayList;


public class Player{
    private final String name; // final

    ArrayList<Animal> animals = new ArrayList<>();
    ArrayList<Food> food = new ArrayList<>();

    public Player(String name) {
        this.name = name;
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
        int pick1 = 0;
        int pick2 = 0;
    }


} //Player Class
