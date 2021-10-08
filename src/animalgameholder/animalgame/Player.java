package animalgameholder.animalgame;

import animalgameholder.animalgame.animals.Animal;
import animalgameholder.animalgame.foods.Food;

import java.util.ArrayList;


public class Player{
    private final String name;

    private ArrayList<Animal> playerAnimals = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void removeAnimal(Animal animal){
        this.playerAnimals.add(animal);
    }

    public ArrayList<Animal> getAnimals(){
        return getAnimals();
    }




} //Player Class
