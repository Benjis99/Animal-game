package animalgameholder.animalgame;

import animalgameholder.animalgame.animals.Animal;

public class GameLogic {

    public GameLogic() {
    }

    public void winnerChoice(Game game){
    int score = 0;
    int index = 0;
    if (game.players.size() == 0){
        System.out.println("No winners");
    } else {

    }
    }

    public void sellAnimals(Game game){
        System.out.println("Game is ending, all animals have been sold.");
        for (Player player : game.players) {

            for (int pick = 0; pick < player.animals.size(); pick++);
        }
    }
}
