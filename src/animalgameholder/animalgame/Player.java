package animalgameholder.animalgame;

import animalgameholder.animalgame.animals.Animal;

import java.util.ArrayList;

public class Player extends Game {

    private ArrayList<Animal> playerAnimals = new ArrayList<>();
    public Player() {

    }



    public void removeAnimal(Animal animal){
        this.playerAnimals.add(animal);
    }

    public ArrayList<Animal> getAnimals(){
        return getAnimals();
    }




} //Player Class
