package bullscows;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Code {

    private final char[] code;
    private final int length;
    private final HashSet<Character> uniqueDigits;

    public Code(String input) {
        this.code = input.toCharArray();
        this.length = code.length;
        this.uniqueDigits = getUnique(code);
    }

    public Code(int digits) {
        this(getRandom(digits));
    }

    public static String getRandom(int digits) {
        Random generator = new Random(System.nanoTime());
        StringBuilder number = new StringBuilder();
        HashSet<Integer> uniques = new HashSet<>(digits);
        int first = generator.nextInt(9) + 1;
        number.append(first);
        uniques.add(first);
        while (number.length() < digits) {
            int r = generator.nextInt(10);
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
            else if (uniqueDigits.contains(digit)) cows++;
        }
        String grade = cows + bulls == 0 ? "None. " : String.format("%d bull(s) and %d cow(s). ", bulls, cows);
        System.out.println("Grade: " + grade);
        System.out.println(this.code);
        return bulls == this.length;
    }

    private static HashSet<Character> getUnique(char[] array) {
        return new HashSet<Character>(List.of(
                IntStream.range(0, array.length)
                        .mapToObj(i -> array[i])
                        .toArray(Character[]::new)));
    }

}
