package animalgameholder.animalgame.foods;

public class DryFoodDog extends Food {

    public DryFoodDog() {
        foodType(smaakFish());
        pricePerKg(smaakPrice());

    }

    public String smaakFish(){
       String brand = "Smaak Fish Dry Food";
       return brand;
    }
    public String smaakPrice() {
        String price = "69.50";
        return price;
    }


    //DryFoodDog Class
}
