package bullscows;

public class SecretCode {
    private String secretCode;
    private int[] code;

//    public int getBulls() {
//        return bulls;
//    }
//
//    private int bulls;
//
//    public int getCows() {
//        return cows;
//    }
//
//    private int cows;


    public SecretCode(String secretCode) {
        this.secretCode = secretCode;
        code = new int[secretCode.length()];
        for (int i = 0; i < secretCode.length(); i++) {
            code[i] = Integer.parseInt("" + secretCode.charAt(i));
        }
    }
    public int length() {
        return secretCode.length();
    }

    public  int[] grade(String answerStr) {
        int bulls = 0;
        int cows = 0;
        int[] answer = new int[answerStr.length()];

        for (int i = 0; i < answerStr.length(); i++) {
            answer[i] = Integer.parseInt("" + answerStr.charAt(i));
        }

        for (int i = 0; i < code.length; i++) {
            if (answer[i] == code[i]) {
                bulls++;
            } else if (contains(answer[i])) {
                cows++;
            }
        }
        return new int[] {bulls, cows};
    }

    private boolean contains(int x) {
        for (int i = 0; i < secretCode.length(); i++) {
            if (code[i] == x)
                return true;
        }
        return false;
    }
}
