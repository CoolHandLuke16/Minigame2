package mini2.view;

import mini2.controller.GameController;
import mini2.gameExceptions.GameException;

import java.util.Scanner;

public class Adventure {
    private GameController gc;
    private Scanner input;

    public Adventure() throws GameException {
        gc = new GameController();
        input = new Scanner(System.in);
    }

    private String getCommand() {
        System.out.print("What would you like to do? ");
        return input.nextLine().trim().toLowerCase();
    }

    private void playGame() {
        System.out.println("Welcome to my adventure game.");
        try {
            System.out.println(gc.displayFirstRoom());
            while (true) {
                String command = getCommand();
                if (command.equals("x")) {
                    System.out.println("Thank you for playing my game.");
                    break;
                }
                try {
                    System.out.println(gc.executeCommand(command));
                } catch (GameException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (GameException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            input.close();
        }
    }

    public static void main(String[] args) {
        try {
            Adventure adventure = new Adventure();
            adventure.playGame();
        } catch (GameException e) {
            System.out.println("Failed to start the game: " + e.getMessage());
        }
    }
}
