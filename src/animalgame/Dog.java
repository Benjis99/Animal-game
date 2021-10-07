package animalgame;

public class Dog extends Animal{

    public Dog() {
        System.out.println("------------------");
        System.out.println("Dog: ");
        new DryFoodDog();
        name("Henny");
        health(100);
        gender("Male");
        startingPrice(9500);
        System.out.println("------------------");
    }

    //Dog Class
}
