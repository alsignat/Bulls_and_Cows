package bullscows;

import java.util.Scanner;

public class Game {

    private boolean finished;
    private int turn;

    public void play() {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.println("Please, enter the secret code's length:");
            input = scanner.nextInt();
            if (input > 10) System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.%n", input);
        } while (input > 10);
        System.out.println("Okay, let's start a game!");
        Code code = new Code(input);
        while(!finished) {
            turn++;
            System.out.printf("Turn %d:%n", turn);
            String guess = scanner.next();
            System.out.println(guess);
            finished = code.evaluate(guess);
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
}
