package bullscows;

import java.util.Scanner;

public class Game {

    private boolean finished;
    private int turn;

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int codeLength = scanner.nextInt();
        System.out.println("Input the number of possible symbols in the code:");
        int codeSymbols = scanner.nextInt();
        Code code = new Code(codeLength, codeSymbols);
        code.printCode(codeSymbols);
        System.out.println("Okay, let's start a game!");
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
