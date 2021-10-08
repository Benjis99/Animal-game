package animalgameholder.animalgame.animals;

import animalgameholder.animalgame.foods.DryFoodCat;

public class Cat extends Animal {

    public Cat() {
        System.out.println("------------------");
        System.out.println("Cat: ");
        new DryFoodCat();
        name("Esmeralda");
        health(100);
        gender("Male");
        startingPrice(500);
        System.out.println("------------------");

    }

    //Cat Class
}
