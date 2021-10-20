package gameSave;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SaveGame implements Serializable{

    Scanner input = new Scanner(System.in); // Use scanner from game class, TO DO
    Game game;

    public void setGame(Game game){
        this.game = game;
    }
    /**
     * Creates
     * @param game
     */
    public void saveGame (Game game){

        File gameFile = new File("SavedGames/");
        boolean running = true;
        while(running) {
            System.out.println("Name your game save: ");
            String savedGameFileName = input.nextLine() + ".ser";

            if (!Files.exists(Paths.get("SavedGames/" + savedGameFileName))){
                if (!gameFile.exists()) {
                    gameFile.mkdir(); // checks if it can create a directory / package where the pathname is specified ("SavedGames/")
                }
                Serializer.serialize("SavedGames/" + savedGameFileName, game);
                running = false;
            } else {
                System.out.println("Filename already exists.");
                System.out.println("1. Overwrite existing file\n"
                                 + "2. Enter a new name");
                if ( // Try catch metod )
            }
        }
    }


    public void loadGame(){
    File[] savedGames;
    File f = new File("SavedGames/");
    FilenameFilter filter = (dir, name) -> name.endsWith(".ser");

    savedGames = f.listFiles(filter);

    if (savedGames == null){
        System.out.println("You have no saved games");
    } else {
        System.out.println("Choose a game to load!\n");
        int counter = 1;
        for (File file : savedGames){
            System.out.println(counter + file.getName());
            counter++;
        }
        System.out.println("0 - back");
        System.out.println("\n");
        int choice = // Try catch
        if (choice == 0){
            return;
        }
    }
    }
}