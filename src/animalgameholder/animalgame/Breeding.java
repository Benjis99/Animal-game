package animalgameholder.animalgame;

import animalgameholder.animalgame.animals.models.Animal;
import animalgameholder.animalgame.animals.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * This is our Breeding class
 *
 * Author Lukas L, Isabella S, Benjamin E, Carl M
 */

public class Breeding {

    public Breeding() {

    }


    /**
     * The method will try to breed the animals selected by the player. The method also compares the players selected
     * animal and the remaining available animals to see if the animals are compatible to breed. If the chosen animals are
     * compatible to breed, the program will randomise a value from 0-100, and >50 will successfully breed the animal and
     * have the player name the new animal.
     * @param player the instance of the current player
     */
    public void animalBreed(Player player) {
        Scanner input = new Scanner(System.in); // Use scanner from game class, TO DO
        Random random = new Random();
        int animal1 = 0;
        int animal2 = 0;
        int chanceOfBreed = random.nextInt(101);
        ArrayList<Animal> tempList = new ArrayList<>();
        if (player.checkWithPlayer(player.ableToBreed)) {
            if (player.animals.size() == 0) {
                System.out.println("No available animals to breed."); // Maybe needs fixing
                return;
            } else {
                while (animal1 < 1 || animal1 > player.animals.size()) {
                    Game.newScreen();
                    player.getPlayerAnimal();
                    System.out.println("Choose your first animal to breed! Enter a number: ");
                    // Try catch needed if player enters an invalid number -----------------
                    animal1 = input.nextInt();

                }
                if (animalsThatCanBreed(player, player.animals.get(animal1 - 1))) {
                    for (Animal animal : player.animals) {
                        if (!(checkAnimalsLeftForBreeding(player.animals.get(animal1 - 1), animal))) {
                            tempList.add(animal);
                        }
                    }
                } else {
                    System.out.println("There is no suitable animal to breed for " + player.animals.get(animal1 - 1).getName() + "!");
                    return;
                }
                while (animal2 < 1 || animal2 > tempList.size()) {
                    System.out.println("You can pair " + player.animals.get(animal1 - 1).getName() + " with: ");
                    int count = 1;
                    for (Animal animal : tempList) {
                        System.out.println("[" + count + "] " + animal.getName() + " > " + animal.getAnimalBreed() + " | " + animal.getGender()
                                + " | " + animal.getHealth() + "% health left.");
                        count++;
                    }
                    System.out.println("Choose your second animal to breed. Enter a number: ");
                    try {
                        animal2 = Integer.parseInt(input.nextLine());
                    } catch (Exception e) {
                        System.out.println("You must enter a number for an animal.");
                    }
                }
            }
            if (checkForBreed(player.animals.get(animal1 - 1), tempList.get(animal2 - 1))) {
                if (chanceOfBreed > 50) {
                    int counter;

                    if (player.animals.get(animal1 - 1).getAnimalBreed().equals("Bird")) {
                        counter = animalBirth(5);
                        for (int i = 0; i < counter; i++) newAnimal(player, new Bird());
                    }
                    if (player.animals.get(animal1 - 1).getAnimalBreed().equals("Cat")) {
                        counter = animalBirth(12);
                        for (int i = 0; i < counter; i++) newAnimal(player, new Cat());
                    }
                    if (player.animals.get(animal1 - 1).getAnimalBreed().equals("Dog")) {
                        counter = animalBirth(12);
                        for (int i = 0; i < counter; i++) newAnimal(player, new Dog());
                    }
                    if (player.animals.get(animal1 - 1).getAnimalBreed().equals("Goldfish")) {
                        counter = animalBirth(10);
                        for (int i = 0; i < counter; i++) newAnimal(player, new Goldfish());
                    }
                    if (player.animals.get(animal1 - 1).getAnimalBreed().equals("Hamster")) {
                        counter = animalBirth(3);
                        for (int i = 0; i < counter; i++) newAnimal(player, new Hamster());
                    }
                } else {
                    System.out.println("Breeding failed!");
                    player.falseBooleans();
                }
            } else {
                System.out.println("The animals you chose are unable to breed with each other. Please pick a compatible pair");
            }
        }
    }

    /**
     * This method allows the player to name their new animal once the parents have mated.
     * @param player
     * @param animal
     */

    public void newAnimal(Player player, Animal animal){
        Scanner input = new Scanner(System.in); // Use scanner from Game class
        String gender = Animal.MaleFemale.getRandomSelectGender().toString();

        System.out.println("You've got a " + animal.getAnimalBreed() + " that is " + gender + "!");
        System.out.println("Enter a name: ");

        animal.setName(input.nextLine());
        animal.setGender(gender);

        player.animals.add(animal);
        player.falseBooleans();
    }


/*    public boolean checkForBreed(Animal animal1, Animal animal2) {
        return animal1.getGender().equals(animal2.getGender()) || animal1.getName().equals(animal2.getName()) ||
                !animal1.getAnimalBreed().equals(animal2.getAnimalBreed());
    }*/

    public boolean checkForBreed(Animal animal1, Animal animal2) {
        return animal1.getAnimalBreed().equals(animal2.getAnimalBreed()) && animal1.getGender() != animal2.getGender();
    }

    public boolean checkAnimalsLeftForBreeding(Animal animal1, Animal animal2){
        return animal1.getGender().equals(animal2.getGender()) || animal1.getName().equals(animal2.getName()) ||
                !animal1.getAnimalBreed().equals(animal2.getAnimalBreed());
    }

    public boolean animalsThatCanBreed(Player player, Animal animal1) {
        for (Animal animal : player.animals) {
            if (animal.getAnimalBreed().equals(animal1.getAnimalBreed()) && !animal.getGender().equals(animal1.getGender())) {
                return true;
            }
        }
        return false;
    }

    public int animalBirth(int maxAmountOfBabies) {
        Random random = new Random();
        int counter = 1;
        System.out.println("Breeding successful! You've now got a baby");
        for (int i = 0; i < maxAmountOfBabies; i++) {
            int numberOfBabies = random.nextInt(101);
            if (numberOfBabies < 20) {
                counter++;
            }
        }
        System.out.println("You've got a total of " + counter + " babies!");
        return counter;
    }

}
