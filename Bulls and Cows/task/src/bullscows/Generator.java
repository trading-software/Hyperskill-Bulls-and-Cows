package bullscows;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    public String generateNumber(int length) throws InvalidNumberLength{
        if (length > 10) {
            throw new InvalidNumberLength();
        }
        String output = "";
        ArrayList<Integer>  usedNumbers = new ArrayList<>();
        Random random = new Random();
        while (output.length() != length) {
            Integer num = random.nextInt(10);
            if (!usedNumbers.contains(num)) {
                output += num.toString();
                usedNumbers.add(num);
            }
        }
        return output;
    }

    public String generateNumberOld(int length) throws InvalidNumberLength{
        if (length > 10) {
            throw new InvalidNumberLength();
            //return "Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.";

        }
        String output = "";
        ArrayList<Integer>  usedNumbers = new ArrayList<>();

        long pseudoRandomNumber = System.nanoTime();

        StringBuilder s = new StringBuilder("" + pseudoRandomNumber);
        s.reverse();

        while (output.length() != length) {
            pseudoRandomNumber = System.nanoTime();
            s = new StringBuilder("" + pseudoRandomNumber);
            s.reverse();
            for (int i = 0; i < s.length(); i++) {
                Integer integer = Integer.parseInt("" + s.charAt(i));

                if (!usedNumbers.contains(integer)) {
                    usedNumbers.add(integer);
                    output += integer;
                    if (output.length() == length)
                        break;
                }
            }
        }
        return output;
    }
}
