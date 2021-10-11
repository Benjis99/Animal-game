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
            if (this.health >= 100) {
                this.health = 100;
                System.out.println("This animal have full health");
            }
            if (this.health < 100) {
                if (this.health >= 50)
                    this.health = this.getHealth() + (int) (this.getHealth() * 0.10);
                    if (this.health < 50)
                        this.health = this.getHealth() + 10;
                    if (this.health > 100){
                        this.health = 100;
                        System.out.println(getName() + " is at full health: " + getHealth());
                }
            }
        }
    }




} //Hamster Class
