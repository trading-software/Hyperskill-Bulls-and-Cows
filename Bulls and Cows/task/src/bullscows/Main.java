package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Generator generator = new Generator();
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        System.out.println(generator.generateNumber(len));


    }

    public static void stage2() {
        int[] predefinedCode = createCode();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int[] grading = grader(s, predefinedCode);

        int bulls = grading[0];
        int cows = grading[1];

        if (bulls == 0 && cows == 0) {
            System.out.printf("\nGrade: None. The secret code is %s.", codeToString(predefinedCode));
        } else if (bulls == 0) {
            System.out.printf("\nGrade: %s cow(s). The secret code is %s.", cows, codeToString(predefinedCode));
        } else {
            System.out.printf("\nGrade: %s bull(s) and %s cow(s). The secret code is %s.", bulls, cows,
                    codeToString(predefinedCode));
        }
    }

    public static int[] stringToCode(String str) {
        int[] code = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            code[i] = Integer.parseInt("" + str.charAt(i));
        }
        return code;
    }
    public static String codeToString(int[] code) {
        String s = "";
        for (int i = 0; i < code.length; i++) {
            s+= code[i];
        }

        return s;
    }

    public static int[] createCode() {
        // for future improvement: predefined code numbers should not repeat
        Random random = new Random();
        int[] code = new int[4];
        for (int i = 0; i < code.length; i++) {
            code[i] = random.nextInt(10);
        }
        return code;
    }

    public static int[] grader(String answerStr, int[] code) {
        int bulls = 0;
        int cows = 0;
        int[] answer = new int[answerStr.length()];
        for (int i = 0; i < answerStr.length(); i++) {
            answer[i] = Integer.parseInt("" + answerStr.charAt(i));
        }

        for (int i = 0; i < code.length; i++) {
            if (answer[i] == code[i]) {
                bulls++;
            } else if (contains(answer[i], code)) {
                cows++;
            }
        }
        return new int[]{bulls, cows};
    }

    public static boolean contains(int x, int[] code) {
        for (int i = 0; i < code.length; i++) {
            if (code[i] == x)
                return true;
        }
        return false;
    }
}
