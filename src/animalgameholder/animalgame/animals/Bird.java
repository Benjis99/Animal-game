package animalgameholder.animalgame.animals;

import animalgameholder.animalgame.animals.models.Animal;
import animalgameholder.animalgame.enums.MaleFemale;
import animalgameholder.animalgame.foods.models.Food;
import animalgameholder.animalgame.foods.Seeds;

/**
 * This is our Bird class
 *
 * Author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class Bird extends Animal {
    public static final int maxAge = 10;
    public static final String animalBreed = "Bird";
    public static final int fullPrice = 800;

    public Bird(String name, MaleFemale mf) {
        super( name, mf);
    }

    //@Overrides the super class, in this case Animal
    @Override
    public boolean canEat (Food food) {
        return food instanceof Seeds;
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
    //Bird Class yes/no
}
