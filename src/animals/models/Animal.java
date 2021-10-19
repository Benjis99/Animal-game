package animals.models;

import food.models.Food;
/**
 * This is our Animal class where we check with the animal if they can,
 * eat the food or not.
 * We also have all the methods for the Animal class here
 *
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public abstract class Animal  {

    /**
     * This is our field variables
     */
        protected String name;
        protected int startPrice;
        protected int health = 100;
        protected String animalBreed;
        protected MaleFemale gender;
        protected int age = 0;
        protected int maxAge;



    public Animal() {
    super();
    }

    public enum MaleFemale{
        FEMALE, MALE;

        public static MaleFemale getRandomSelectGender(){  //Random select for male or female
            return values()[(int) (Math.random() * values().length)];
        }
    }

        public boolean eatTrue(Food food){
            return true;
         }

    /**
     * eatFood class will check with the animal if it can eat the food we are giving to it.
     * If the animal is at 100% health then it won't be able to eat anything.
     *
     * @param food this is in the method, so we can get information from the Food class
     */
         public void eatFood(Food food){ //Method that will check if the animal can eat the food or not
                                        //If the animal is at 100 health, then it will not be able to eat
        if (eatTrue(food)) {
            if (this.health >= 100){
                this.health = 100;
                System.out.println("Animal is at full health");
            }
            if (this.health < 100){
                this.health = this.health + (int) (this.health * 0.10);

                    if (this.health > 100){
                        this.health = 100;
                        System.out.println(getName() + "animal is at full health " + getHealth() + "%");
                    }
                }
            }
        }

        public int currentPriceAnimal(){
        double currentPrice = ((this.health / 100.0) * this.startPrice);
        currentPrice = currentPrice - (this.age * 2);
        return (int) currentPrice;
        }

        public boolean canEat(Food food){
        return true;
        }
        public MaleFemale getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = MaleFemale.valueOf(gender.toUpperCase());
        }

        public String getAnimalBreed() {
            return animalBreed;
        }

        public int getStartPrice() {
            return startPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }


        public int getAge() {
        return age;
        }

        public void setAge(int age) {
        this.age = this.age + age;
        }

        public int getMaxAge() {
        return maxAge;
        }

    //Animal Class
}
