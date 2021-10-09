package animalgameholder.animalgame.animals;

import animalgameholder.animalgame.foods.DryFoodDog;

import java.util.Locale;

public abstract class Animal  {

        private String name;
        private int startPrice;
        private int health = 100;
        private String animalBreed;
        private MaleFemale gender;
        private int age = 0;
        private int maxAge;


    public Animal() {
    super();
    }


        public enum MaleFemale{
        FEMALE, MALE;

        public static MaleFemale getRandomSelectGender(){  //Random select for male or female
            return values()[(int) (Math.random() * values().length)];
        }
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
