package animalgameholder.animalgame.foods.models;

import animalgameholder.animalgame.animals.models.Animal;

/**
 * This is our Food class that extends the Animal class.
 * In this class we give the food value.
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
    public abstract class Food extends Animal {
    public String name; //Change to private or protected
    public int price;   //Change to private or protected
    private int kg = 1;


    /**
     * Here we have the constructor that gives "Food" a Name, kg and price.
     */
    public Food() {
    super();
    }
        public String getName() {
            return name;
        }

        public int getKg() {
            return kg;
        }

        public void setKg(int kg) {
            this.kg = this.kg + kg;
        }

        public int getPrice() {
            return price;
        }
    } //Food Class
