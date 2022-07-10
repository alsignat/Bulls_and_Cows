package bullscows;

import java.util.Scanner;

public class Game {

    private boolean finished;
    private int turn;

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int codeLength = toInt(scanner.next());
        System.out.println("Input the number of possible symbols in the code:");
        int codeSymbols = toInt(scanner.next());
        if (codeLength < 0 || codeSymbols < 0) return;
        if (!verifyInput(codeLength, codeSymbols)) return;
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

    private boolean verifyInput(int length, int symbols) {
        boolean verified = false;
        if (symbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        } else if (symbols < length || length == 0) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", length, symbols);
        } else verified = true;
        return verified;
    }

    private int toInt(String input) {
        int output = -1;
        try {
            output = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.printf("Error: %s isn't a valid number.%n", input);
        }
        return output;
    }
}
