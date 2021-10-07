package animalgame;

    abstract class Animal {

    public Animal() {
    super();
    }

    public void name(String name){
        System.out.println("Name: " + name);
    }

    public void health(int health){
        System.out.println("What is the current hp: " + health + "hp");
    }

    public void gender(String gender){
        System.out.println("The gender is: " + gender);
    }

    public  void startingPrice(int price){
        System.out.println("The starting price is: " + price + " Swedish krona.");
    }


    //Animal Class
}
