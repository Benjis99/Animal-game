package gameSave;


import animalgame.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * This is our SaveGame Class that saves the game,
 * gives the game a name or you can choose to play an existing
 * game.
 *
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class SaveGame implements Serializable {
    Game game;

    /**
     * Here we make a new game.
     * @param game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Here we name our game that we want to save
     * using the SaveGameName method.
     * @return
     */
    public String saveGameName() {
        String answer = Dialog.dialogString("Name your game save: ");
        return answer;
    }

    /**
     * This method checks if there is an existing name of a game
     * and asks the user to either save the game with a new name or
     * overwrite an existing file.
     * @return
     */
    public int inputSaveGame() {
        int inputAnswer = Dialog.dialog("\"1. Overwrite existing file 2. Enter a new name");
        return inputAnswer;
    }

    /**
     * This method shows a list of played games you can choose
     * between.
     * @return
     */
    public int inputLoadGame() {
        int inputLoad = Dialog.dialog("|0| - back");
        return inputLoad;
    }

    /**
     * This method Is the actual game this is where we save the game.
     * @param game
     */
    public void saveGame(Game game) {

        File gameFile = new File("SavedGames/");
        boolean running = true;
        while (running) {
            String savedGameFileName = saveGameName() + ".ser";

            if (!Files.exists(Paths.get("SavedGames/" + savedGameFileName))) {
                if (!gameFile.exists()) {
                    gameFile.mkdir(); // checks if it can create a directory / package where the pathname is specified ("SavedGames/")
                }
                Serializer.serialize("SavedGames/" + savedGameFileName, game);
                running = false;
            } else {
                System.out.println("Filename already exists.");
                int choiceSave = inputSaveGame();
                if (choiceSave == 1) { // Try catch metod
                    Serializer.serialize("SavedGames/" + savedGameFileName, game);
                    running = false;
                }
            }
        }
    }

    /**
     * The method loadGame loads either a new game or
     * a saved game. If there isn't any saved game the
     * game will show that there isn't any games in the history.
     */
    public void loadGame() {
        File[] savedGames;
        File f = new File("SavedGames/");
        FilenameFilter filter = (dir, name) -> name.endsWith(".ser");

        savedGames = f.listFiles(filter);

        if (savedGames == null) {
            System.out.println("You have no saved games");
        } else {
            System.out.println("Choose a game to load!\n");
            int counter = 1;
            for (File file : savedGames) {
                System.out.println("|" + counter + "| - " + file.getName());
                counter++;
            }
            int choice = inputLoadGame();
            if (choice == 0) {
                return;
            }
            String gameFile = savedGames[choice - 1].toString();
            try {
                this.game = (Game) Serializer.deserialize(gameFile);
                game.gameMenu();
            } catch (Exception error) {
                System.out.println(error);
            }
        }
    }
}