package animalgameholder.animalgame;

import animalgameholder.animalgame.animals.Cat;

public class Store{


    public Store() {

    }


    public void buyAnimal(){





    }

    public void sellAnimal(){

    }

    public void catInfo(Cat cat){
        System.out.println("1. Information 2. Back to menu");
        //Cat class
        cat.name("test");
        //Cat info upp
        System.out.println("1. Buy 2. Back to menu");
        //if the player is buying, go to buyAnimal

    }


}
