package animalgameholder.animalgame.animals;

import animalgameholder.animalgame.animals.models.Animal;
import animalgameholder.animalgame.enums.MaleFemale;
import animalgameholder.animalgame.foods.models.Food;
import animalgameholder.animalgame.foods.DryFoodCat;

/**
 * This is our Cat class
 *
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class Cat extends Animal {
    public static final int maxAge = 10;
    public static final String animalBreed = "Cat";
    public static final int fullPrice = 5000;

    public Cat(String name, MaleFemale gender) {
        super( name, gender);
    }

    //@Overrides the super class, in this case Animal
    @Override
    public boolean canEat (Food food) {
        return food instanceof DryFoodCat;
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
    //Cat Class
}
