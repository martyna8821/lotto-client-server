package pl.martyna.client.view;

import java.util.Scanner;
import java.util.Set;

/**
 * Represents the view of pl.martyna.client simulator
 * @author Martyna Drabinska
 * @version 2.0
 */
public class LottoView {

    /** Displays all menu options */
    public void displayMenu(){
        System.out.println("Choose one menu option:");
        for(MenuOption option : MenuOption.values()){
            System.out.println(option.getMessage());
        }
   }

    /**
     * Returns menu option chosen by user
     * @return chosen menu option
     */
   public MenuOption retrieveMenuOption(){
      Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if(option > 3){
            System.out.println("Incorrect value ");
            return MenuOption.QUIT;
        }
        else{
            return MenuOption.values()[option];
        }
   }

    /**
     * Displays set with results on screen
     *  @param results set of integers displayed on screen
     */
    public void displayResults(Set<Integer> results){
        System.out.println("Drawn results:");
        for(Integer i: results) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * Returns entered quantity of results
     * @return quantity of results
     */
    public int retrieveResultsQuantity(){
        System.out.println("Enter results quantity:");
        Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
    }

    /**
     * Returns entered minimal value of result
     * @return minimal value of a result
     */
    public int retrieveMin(){
        System.out.println("Enter minimum result value: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * Returns entered maximum value of result
     * @return maximum value of a result
     */
    public int retrieveMax(){
        System.out.println("Enter maximum result value:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * Displays message
     * @param message message to display
     */
    public void displayMessage(String message){
        System.out.println(message);
    }

}
