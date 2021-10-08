package animalgameholder.animalgame.foods;

import animalgameholder.animalgame.animals.Animal;

    public abstract class Food extends Animal {
    protected String name;
    protected double price;


    public Food() {
    super();
    }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    } //Food Class
