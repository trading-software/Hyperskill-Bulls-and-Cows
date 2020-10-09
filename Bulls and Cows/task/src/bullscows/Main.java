package bullscows;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Generator generator;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please, enter the secret code's length:");
            int len = Integer.parseInt(scanner.nextLine());
            if (len > 36 || len < 1) throw new GameException();

            System.out.println("Input the number of possible symbols in the code:");
            int symbols = Integer.parseInt(scanner.nextLine());
            if (symbols > 36 || symbols < 1) throw new GameException();

            if (symbols < len) throw new GameException();

            generator = new Generator(len, symbols);

            SecretCode secretCode = new SecretCode(generator.generateNumber(len, symbols));
            System.out.println(generator.getCodeDescription());
            System.out.println("Okay, let's start a game!");
            int turn = 1;
            int bulls = 0;
            while (bulls != len) {
                System.out.printf("\nTurn %d:", turn);
                String guess = scanner.nextLine();
                if (guess.length() == secretCode.length()) {
                    int[] bullCows = secretCode.grade(guess);
                    bulls = bullCows[0];
                    gameGradeFeedback(bullCows);
                } else {
                    System.out.println("Guess code should be the same length as given secret code length!");
                }
                turn++;
            }
            System.out.println("Congratulations! You guessed the secret code.");
        } catch (InvalidNumberLength e) {
            System.out.println("Error: can't generate a secret number with a length of more than 10 because there aren't enough unique digits.");
        } catch (GameException e) {
            System.out.println("error " + e);
        } catch (NumberFormatException nfe) {
            System.out.println("error " + nfe);
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public static void gameGradeFeedback(int[] bullsCows) {
        System.out.printf("\nGrade: %d bull and %d cow\n", bullsCows[0], bullsCows[1]);
    }
}