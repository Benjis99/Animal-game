package animalgameholder.animalgame.foods;

import animalgameholder.animalgame.animals.Animal;

    public abstract class Food extends Animal {
    protected String name;
    protected int price;
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

        public void setKg(int kg) {
            this.kg = this.kg + kg;
        }

        public int getPrice() {
            return price;
        }
    } //Food Class
