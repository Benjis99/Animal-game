package animalgame;

abstract class Food extends Animal{

    public Food() {
    super();
    }

    public void foodType(String foodType){
        System.out.println("What type of brand and food: " + foodType);
    }

    public void pricePerKg(String price){
        System.out.println("The price is: " + price + " per kg.");

    }

} //Food Class
