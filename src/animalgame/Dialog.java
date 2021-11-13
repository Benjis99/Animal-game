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
     * The 'String text' lets us have a system out print where we use the method.
     * Without it, we would not be able to use it with different texts.
     * This is used for ints.
     *
     * @param text lets the program know that you need to insert a text
     * @param min  this is the minimum value
     * @param max  this is the maximum value
     * @return the input
     */
    public static int dialog(String text, int min, int max) {
        while (true) {
            System.out.println(text);
            try {
                int number = Integer.parseInt(console.nextLine());
                if (number >= min && number <= max)
                    return number;
                else {
                    System.out.println("Wrong input!");
                }
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }
        }
    }

    /**
     * Dialog method is used all over the program instead of using new scanners.
     * The 'String text' lets us have a system out print where we use the method.
     * Without it, we would not be able to use it with different texts.
     * This is used for ints, and it does not have a max value.
     *
     * @param text lets the program know that you need to insert a text
     * @return the input
     */
    public static int dialogWithoutMax(String text) {
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
     * The 'String text' lets us have a system out print where we use the method.
     * Without it, we would not be able to use it with different texts.
     * This is used for Strings.
     *
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
        while (true){
            String test = console.nextLine();
            if (test.matches("^[ ]*$")){
                System.out.println("Animal name can't be empty");
                System.out.println("Enter the name again");
                continue;
            }
            if (!test.matches("^[a-öA-Ö!@#$&()\\-`.+\\s,/\" ]*$")){
                System.out.println("Please use only letters");
                System.out.println("Enter the name again");

            }
            else{
                return test;
            }
        }
    }
    public static String playerName() {
        while (true){
            String test = console.nextLine();
            if (test.matches("^[ ]*$")){
                System.out.println("Username can't be empty");
                System.out.println("Enter the name again");
                continue;
            }
            if (!test.matches("^[a-öA-Ö!@#$&()\\-`.+\\s,/\" ]*$")){
                System.out.println("Please use only letters");
                System.out.println("Enter the name again");

            }
            else{
                return test;
            }
        }
    }

    public static String enterButton() {
        String enterToContinue = console.nextLine();
        return enterToContinue;
    }


}