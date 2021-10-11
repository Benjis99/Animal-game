package animalgameholder.animalgame.animals;

import animalgameholder.animalgame.foods.Pellets;
import animalgameholder.animalgame.foods.Food;


public class Hamster extends Animal{


    public Hamster() {
        maxAge = 10;
        animalBreed = "Hamster";
        startPrice = 30;

    }

    @Override
    public boolean canEat (Food food) {
        return food instanceof Pellets;
    }

    @Override
    public void eatFood (Food food){
        if (canEat(food)) {
            if (this.getHealth() >= 100) {
                this.getHealth() = 100;
                System.out.println();
            }
        }
    }




} //Hamster Class
