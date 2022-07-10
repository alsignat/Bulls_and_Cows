package bullscows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Code {

    private final char[] code;
    private final int length;
    private final HashSet<Character> uniqueSymbols;

    public Code(String input) {
        this.code = input.toCharArray();
        this.length = code.length;
        this.uniqueSymbols = getUnique(code);
    }

    public Code(int digits, int symbols) {
        this(getRandom(digits, symbols));
    }

    public static String getRandom(int size, int symbols) {
        Random generator = new Random(System.nanoTime());
        StringBuilder number = new StringBuilder();
        HashSet<Character> uniques = new HashSet<>(size);

        StringBuilder possibleCharacters = new StringBuilder("0123456789");
        int letterCount = 0;
        while (symbols > 10 && possibleCharacters.length() < symbols) {
            possibleCharacters.append((char) (97 + letterCount));
            letterCount++;
        }
        while (number.length() < size) {
            char r = possibleCharacters.charAt(generator.nextInt(symbols));
            if (!uniques.contains(r)) {
                number.append(r);
                uniques.add(r);
            }
        }
        return number.toString();
    }

    public boolean evaluate(String input) {
        char[] inputArray = input.toCharArray();
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < length; i++) {
            char digit = inputArray[i];
            if (this.code[i] == digit) bulls++;
            else if (uniqueSymbols.contains(digit)) cows++;
        }
        String grade = cows + bulls == 0 ? "None. " : String.format("%d bull(s) and %d cow(s). ", bulls, cows);
        System.out.println("Grade: " + grade + Arrays.toString(code));
        System.out.println(this.code);
        return bulls == this.length;
    }

    private static HashSet<Character> getUnique(char[] array) {
        return new HashSet<Character>(List.of(
                IntStream.range(0, array.length)
                        .mapToObj(i -> array[i])
                        .toArray(Character[]::new)));
    }

    public void printCode(int symbols) {
        String encoded = "*".repeat(length);
        String range = "0-9" + (symbols <= 10 ? "" :  ", a-" + (char) (97 + symbols - 11));
        System.out.printf("The secret code is prepared: %s (%s).%n", encoded, range);
    }
}
