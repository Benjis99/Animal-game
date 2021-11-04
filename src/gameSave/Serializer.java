package gameSave;

import java.io.*;

/**
 * This is our Serializer class that convert the
 * code in your game to a long string of bytes that
 * saves where you are at the game at the moment.
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class Serializer implements Serializable {

    /**
     * This is the method that saves the game and turns the string onto
     * bytes and
     * give the player an error to show that the game
     * was not saved.
     * @param filePath
     * @param data
     */
    static public void serialize(String filePath, Object data) {
        try {
            var file = new FileOutputStream(filePath);
            var out = new ObjectOutputStream(file);
            out.writeObject(data);
            out.close();
            file.close();
            System.out.println("Game is saved!");
        } catch (Exception error) {
            System.out.println("Game was not saved.");
            System.out.println(error);
        }
    }

    /**
     * This is our method deserialize that converts bytes to
     *
     * @param filePath
     * @return
     */
    static public Object deserialize(String filePath) {
        try {
            var file = new FileInputStream(filePath);
            var in = new ObjectInputStream(file);
            var data = in.readObject();
            in.close();
            file.close();
            return data;
        } catch (Exception error) {
            System.out.println(error);
            return false;
        }
    }
}
