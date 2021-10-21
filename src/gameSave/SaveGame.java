package gameSave;

import animalgame.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SaveGame implements Serializable {
    Game game;
    //Scanner input = new Scanner(System.in); // Use scanner from game class, TO DO


    public void setGame(Game game) {
        this.game = game;
    }


    public String saveGameName(){
        String answer = Dialog.dialogString("Name your game save: ");
        return answer;
    }

    public int inputSaveGame(){
        int inputAnswer = Dialog.dialog("\"1. Overwrite existing file 2. Enter a new name");
        return inputAnswer;
    }

    public int inputLoadGame(){
        int inputLoad = Dialog.dialog("|0| - back");
        return inputLoad;
    }

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