package animalgameholder.animalgame.foods;

import animalgameholder.animalgame.Food;

public class DryFoodCat extends Food {

    public DryFoodCat() {
        foodType(royalCanin());
        pricePerKg(royalPrice());
    }
    public String royalCanin(){
        String brand = "Royal Canin Sensible Dry Food";
        return brand;
    }

    public String royalPrice(){
        String price = "149.50";
        return price;
    }
    //DryFoodCat Class
}
