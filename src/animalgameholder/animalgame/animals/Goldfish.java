package animalgameholder.animalgame.animals;

import animalgameholder.animalgame.foods.Food;
import animalgameholder.animalgame.foods.FishFeed;

/**
 * This is our Goldfish class
 *
 * Author Lukas L, Isabella S, Benjamin E, Carl M
 */

public class Goldfish extends Animal {

    public Goldfish() {
        maxAge = 10;
        animalBreed = "Goldfish";
        startPrice = 300;
    }

    //@Overrides the super class, in this case Animal
    @Override
    public boolean canEat (Food food) {
        return food instanceof FishFeed;
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
} //Goldfish Class
