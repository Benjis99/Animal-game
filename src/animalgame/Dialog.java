package animalgame;

import java.io.Serializable;
import java.util.Scanner;

public class Dialog implements Serializable { // s
    private static Scanner console = new Scanner(System.in);

    public static int dialog(String text) {
        System.out.println(text);
        return Integer.parseInt(console.nextLine());
    }

    public static String dialogString(String text) {
        System.out.println(text);
        return console.nextLine();
    }


    public static int intReturn() {
        return Integer.parseInt(console.nextLine());
    }

    public static String stringReturn() {
        return console.nextLine();
    }

    public static String stringReturnNext() {
        return console.next();
    }


}
