package animalgame;

import animals.models.Animal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is our Gamelogic class where the "brain" of the game is.
 *
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class GameLogic implements Serializable {

/*    ArrayList<Animal> deadBcusHealth = new ArrayList<>();
    ArrayList<Animal> deadBcusAge = new ArrayList<>();*/


    public GameLogic() {
    }

    public void winnerPick(Game game){
    int score = 0;
    int index = 0;
    if (game.players.size() == 0){
        System.out.println("No winners");
    } else {
    autoSellAnimals(game);
    for (int i = 0; i < game.players.size(); i++){
        if (game.players.get(i).getMoney() > score ) {
            score = game.players.get(i).getMoney();
            index = i;
        }
     }
        System.out.println("Winner is: " + game.players.get(index).getName() + " with: "
                + game.players.get(index).getMoney() + " KR");
        for (Player player : game.players){
            System.out.println(player.getName() + ": " + player.getMoney() + " KR");
        }
        for (Player player : game.loss) {
            System.out.println(player.getName() + ": " + player.getMoney() + " KR");
        }
      }
    }

    public void autoSellAnimals(Game game){
        Game.newScreen();
        System.out.println("Game is ending, all animals have been sold.");
        for (Player player : game.players) {

            for (int i = 0; i < player.animals.size(); i++) {
                player.addMoney(player.animals.get(i).currentPriceAnimal());
                player.animals.remove(player.animals.get(i));
                i--;
            }
        }
    }


    public void animalsHealth(Player player){
        Random random = new Random();
        for (Animal animal: player.animals){
            int dmg = 10 + random.nextInt(20); //Lowest health 10, random.nextInt randomize 1-20.
            animal.setHealth(animal.getHealth() - dmg);
        }
    }

    public void animalAge (Player player){
        for (Animal animal: player.animals)
            animal.setAge(1);
    }

    public void playerLoss (Player player, Game game){
        if (player.animals.size() <= 0 && player.getMoney() < 10){
            System.out.println(player.getName() + " no money or animals food, you lost");
            game.players.remove(player);
            game.loss.add(player);
        }
    }

    public void startRound(Player player){
        animalAge(player);
        checkDeadAnimals(player);
    }
    public void endRound(Player player, Game game){
        playerLoss(player, game);
        animalsHealth(player);
        checkDeadAnimals(player);
    }


    public void checkDeadAnimals (Player player){
        ArrayList<Animal> deadBcusHealth = new ArrayList<>();
        ArrayList<Animal> deadBcusAge = new ArrayList<>();

        for (int i = 0; i < player.animals.size(); i++){
            if (player.animals.get(i).getHealth() < 1){
                deadBcusHealth.add(player.animals.get(i));
                player.animals.remove(player.animals.get(i));
                i--;
            }
            else if (player.animals.get(i).getAge() > player.animals.get(i).getMaxAge()){
                deadBcusAge.add(player.animals.get(i));
                player.animals.remove(player.animals.get(i));
                i--;
            }
        }
        if (deadBcusAge.size() > 0 || deadBcusHealth.size() > 0){
            System.out.println("---");
        }
        if (deadBcusHealth.size() > 0){
            System.out.println("Player [" +player.getName()+ "] you have animals that died because low health");
            for (Animal animal : deadBcusHealth){
                System.out.println("Animal [" +animal.getName()+ "] died because of low health");
            }
        }
        if (deadBcusAge.size() > 0){
            System.out.println("Player [" +player.getName()+ "] you have animals that died because old age");
            for (Animal animal : deadBcusAge){
                System.out.println("Animal [" +animal.getName()+ "] died because of old age");
            }
        }
    }




}
