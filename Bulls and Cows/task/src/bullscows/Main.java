package bullscows;

import java.util.Random;
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

//    public static void stage2() {
//        int[] predefinedCode = createCode();
//        Scanner scanner = new Scanner(System.in);
//        String s = scanner.nextLine();
//        int[] grading = grader(s, predefinedCode);
//
//        int bulls = grading[0];
//        int cows = grading[1];
//
//        if (bulls == 0 && cows == 0) {
//            System.out.printf("\nGrade: None. The secret code is %s.", codeToString(predefinedCode));
//        } else if (bulls == 0) {
//            System.out.printf("\nGrade: %s cow(s). The secret code is %s.", cows, codeToString(predefinedCode));
//        } else {
//            System.out.printf("\nGrade: %s bull(s) and %s cow(s). The secret code is %s.", bulls, cows,
//                    codeToString(predefinedCode));
//        }
//    }

    public static void gameGradeFeedback(int[] bullsCows) {
        System.out.printf("\nGrade: %d bull and %d cow\n", bullsCows[0], bullsCows[1]);
    }

//    public static int[] stringToCode(String str) {
//        int[] code = new int[str.length()];
//        for (int i = 0; i < str.length(); i++) {
//            code[i] = Integer.parseInt("" + str.charAt(i));
//        }
//        return code;
//    }
//    public static String codeToString(int[] code) {
//        String s = "";
//        for (int i = 0; i < code.length; i++) {
//            s+= code[i];
//        }
//
//        return s;
//    }

//    public static int[] createCode() {
//        // for future improvement: predefined code numbers should not repeat
//        Random random = new Random();
//        int[] code = new int[4];
//        for (int i = 0; i < code.length; i++) {
//            code[i] = random.nextInt(10);
//        }
//        return code;
//    }

//    public static int[] grader(String answerStr, int[] code) {
//        int bulls = 0;
//        int cows = 0;
//        int[] answer = new int[answerStr.length()];
//        for (int i = 0; i < answerStr.length(); i++) {
//            answer[i] = Integer.parseInt("" + answerStr.charAt(i));
//        }
//
//        for (int i = 0; i < code.length; i++) {
//            if (answer[i] == code[i]) {
//                bulls++;
//            } else if (contains(answer[i], code)) {
//                cows++;
//            }
//        }
//        return new int[]{bulls, cows};
//    }

//    public static int[] grader(String answerStr, String secretCode) {
//        int bulls = 0;
//        int cows = 0;
//        int[] answer = new int[answerStr.length()];
//        int[] code = new int[secretCode.length()];
//        for (int i = 0; i < answerStr.length(); i++) {
//            answer[i] = Integer.parseInt("" + answerStr.charAt(i));
//            code[i] = Integer.parseInt("" + secretCode.charAt(i));
//        }
//
//        for (int i = 0; i < code.length; i++) {
//            if (answer[i] == code[i]) {
//                bulls++;
//            } else if (contains(answer[i], code)) {
//                cows++;
//            }
//        }
//        return new int[]{bulls, cows};
//    }



}
