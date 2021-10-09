package animalgameholder.animalgame.foods;

import animalgameholder.animalgame.animals.Animal;

    public abstract class Food extends Animal {
    protected String name;
    protected double price;
    private int kg = 1;


    public Food() {
    super();
    }

        public String getName() {
            return name;
        }

        public int getKg() {
            return kg;
        }

        public double getPrice() {
            return price;
        }
    } //Food Class
