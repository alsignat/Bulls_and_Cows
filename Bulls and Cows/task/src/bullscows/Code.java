package bullscows;

import java.util.HashSet;
import java.util.List;

public class Code {

    private final Integer[] code;

    private final HashSet<Integer> uniqueDigits;

    public Code(String input) {
        this.code = toIntArray(Integer.parseInt(input));
        this.uniqueDigits = new HashSet<Integer>(List.of(code));
    }

    public Code(int input) {
        this.code = toIntArray(input);
        this.uniqueDigits = new HashSet<Integer>(List.of(code));
    }

    public String printCode() {
        return "" + code[0] + code[1] + code[2] + code[3];
    }

    public void evaluate(int input) {
        Integer[] inputArray = toIntArray(input);
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < 4; i++) {
            int digit = inputArray[i];
            if (this.code[i] == digit) bulls++;
            else if (uniqueDigits.contains(digit)) cows++;
        }
        String grade = cows + bulls == 0 ? "None. " : String.format("%d bull(s) and %d cow(s). ", bulls, cows);
        System.out.printf("Grade: " + grade + "The secret code is %s.%n", printCode());
    }

    private Integer[] toIntArray(int input) {
        Integer[] array = new Integer[4];
        int code = input;
        for (int i = 0; i < 4; i++) {
            array[3 - i] = code % 10;
            code /= 10;
        }
        return array;
    }

}
