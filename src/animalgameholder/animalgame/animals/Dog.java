package animalgameholder.animalgame.animals;


import animalgameholder.animalgame.foods.Food;
import animalgameholder.animalgame.foods.DryFoodDog;

/**
 * This is our Dog class
 *
 * Author Lukas L, Isabella S, Benjamin E, Carl M
 */


public class Dog extends Animal {



    public Dog() {
        maxAge = 10;
        animalBreed = "Dog";
        startPrice = 9000;
    }

    //@Overrides the super class, in this case Animal
    @Override
    public boolean canEat (Food food) {
        return food instanceof DryFoodDog;
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

    //Dog Class
}
