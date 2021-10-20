package food.models;

/**
 * This is our Food class that extends the Animal class.
 * In this class we give the food value.
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
    public abstract class Food {


    protected String name;
    protected int price;
    private int kg = 1;

    /**
     * Here we have the constructor with a super inside.
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
