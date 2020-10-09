package bullscows;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    private final int length;
    private final int symbols;
    public Generator(int length, int symbols) {
        this.length = length;
        this.symbols = symbols;
    }

    public String getCodeDescription() {
        StringBuilder sb = new StringBuilder("The secret is prepared: ");
        for (int i = 0; i < length; i++) {
            sb.append('*');
        }
        sb.append(symbols < 10 ?
                String.format(" (0-%d).", symbols) :
                String.format(" (0-9, a-%c).", (char)(symbols + 86)));
        return sb.toString();
    }

    public String generateNumber(int length, int symbols) throws InvalidNumberLength{
        if (length > 36) {
            throw new InvalidNumberLength();
        }
        String output = "";
        ArrayList<Character>  usedCharacters = new ArrayList<>();
        Random random = new Random();
        while (output.length() != length) {
            int seed = random.nextInt(symbols);
            Character ch = mapSeedToChar(seed);
            if (!usedCharacters.contains(ch)) {
                output += ch.toString();
                usedCharacters.add(ch);
            }
        }
        return output;
    }

    public Character mapSeedToChar(int seed) {
        if (seed < 10)
            return Character.valueOf((char) (seed + 48));
        else
            return Character.valueOf((char) (seed + 87));
    }

    public String generateNumber10(int length) throws InvalidNumberLength{
        if (length > 36) {
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
}
