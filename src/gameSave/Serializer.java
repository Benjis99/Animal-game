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
     * This is our method serialize that deserialize our data
     * this makes our code converted into a linear data format.
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
     * This is our deserialize method.
     * The deserialization process from the linear data is the reverse,
     * and causes the Address object to be instantiated in memory.
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


