package animalgame;

import java.io.Serializable;
import java.util.Scanner;

/**
 * This is our Dialog class where we have our scanner.
 * @author Lukas L, Isabella S, Benjamin E, Carl M
 */
public class Dialog implements Serializable {
    private static Scanner console = new Scanner(System.in);

    /**
     * Dialog method is used all over the program instead of using new scanners.
     * The 'String text' lets us have an outprint where we use the method,
     * without it, we would not be able to use it with different texts.
     * This is used for ints.
     * @param text lets the program know you need to insert text
     * @return the input
     */
    public static int dialog(String text) {
        while (true) {
            System.out.println(text);
            try {
                return Integer.parseInt(console.nextLine());
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }
        }
    }

    /**
     * Dialog method is used all over the program instead of using new scanners.
     * The 'String text' lets us have an outprint where we use the method,
     * without we would not be able to use it with different texts.
     * This is used for Strings.
     * @param text lets the program know you need to insert text
     * @return the input
     */
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

    public static int intScannerNext() {
        return console.nextInt();
    }

    /*
    public static int tryCatch(int min, int max){
        Scanner input = new Scanner(System.in);
        int playerChoice = -1;
        while (playerChoice < min || playerChoice > max){
            try {
                System.out.println("Enter an option: ");
                playerChoice = input.nextInt();
            } catch (Exception e){
                System.out.println("Please enter a number in the menu. Try again");
                input.next();
            }
        } return playerChoice;
    }

     */


}
