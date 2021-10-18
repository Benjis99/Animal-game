package animalgameholder.animalgame.animals;

import animalgameholder.animalgame.animals.models.Animal;
import animalgameholder.animalgame.foods.models.Food;
import animalgameholder.animalgame.foods.Seeds;

public class Bird extends Animal {

    /**
     * Our constructor that contains maxAge, animalBreed and startPrice of the animal
     */
    public Bird() {
        maxAge = 10;
        animalBreed = "Bird";
        startPrice = 30;
    }

    /**
     * This overrides the Animal class
     * @param food this is in the method, so we can get information from the Food class
     * @return this returns the instance of seeds
     */
    @Override
    public boolean canEat (Food food) {
        return food instanceof Seeds;
    }

    /**
     * The eatFood method will override with new health for the animal.
     * @param food this is in the method, so we can get information from the Food class
     */
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
    //Bird Class yes/no
}
