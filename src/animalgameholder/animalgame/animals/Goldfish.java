package animalgameholder.animalgame.animals;

import animalgameholder.animalgame.animals.models.Animal;
import animalgameholder.animalgame.enums.MaleFemale;
import animalgameholder.animalgame.foods.models.Food;
import animalgameholder.animalgame.foods.FishFeed;

/**
 * This is our Goldfish class
 *
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class Goldfish extends Animal {
    /**
     * Our field variables will never change value during the start of the game,
     * that is why all field variables are static.
     */
    public static final int maxAge = 10;
    public static final String animalBreed = "Goldfish";
    public static final int fullPrice = 300;

    /**
     * The constructor contains String name, MaleFemale gender and that is declared in the super.
     * @param name lets us set a name for the animal
     * @param gender lets us set a gender for the animal
     */
    public Goldfish(String name, MaleFemale gender) {
        super( name, gender);
    }

    /**
     * This overrides the Animal class
     * @param food this is in the method, so we can get information from the Food class
     * @return this returns the instance of seeds
     */
    //@Overrides the super class, in this case Animal
    @Override
    public boolean canEat (Food food) {
        return food instanceof FishFeed;
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
} //Goldfish Class
