package model;

import view.ScannerMatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RandomsAndCaptcha {
    private static String realNumber = "";

    public static String getRealNumber() {
        return realNumber;
    }

    public static void setRealNumber(String realNumber) {
        RandomsAndCaptcha.realNumber = realNumber;
    }

    public static String[] captchaNumbers = {
            "               \n" +
                    "               \n" +
                    "    ,----..    \n" +
                    "   /   /   \\   \n" +
                    "  /   .     :  \n" +
                    " .   /   ;.  \\ \n" +
                    ".   ;   /  ` ; \n" +
                    ";   |  ; \\ ; | \n" +
                    "|   :  | ; | ' \n" +
                    ".   |  ' ' ' : \n" +
                    "'   ;  \\; /  | \n" +
                    " \\   \\  ',  /  \n" +
                    "  ;   :    /   \n" +
                    "   \\   \\ .'    \n" +
                    "    `---`      \n" +
                    "               ",
            "           \n" +
                    "           \n" +
                    "     ,---, \n" +
                    "  ,`--.' | \n" +
                    " /    /  : \n" +
                    ":    |.' ' \n" +
                    "`----':  | \n" +
                    "   '   ' ; \n" +
                    "   |   | | \n" +
                    "   '   : ; \n" +
                    "   |   | ' \n" +
                    "   '   : | \n" +
                    "   ;   |.' \n" +
                    "   '---'   \n" +
                    "           \n" +
                    "           ",
            "               \n" +
                    "               \n" +
                    "      ,----,   \n" +
                    "    .'   .' \\  \n" +
                    "  ,----,'    | \n" +
                    "  |    :  .  ; \n" +
                    "  ;    |.'  /  \n" +
                    "  `----'/  ;   \n" +
                    "    /  ;  /    \n" +
                    "   ;  /  /-,   \n" +
                    "  /  /  /.`|   \n" +
                    "./__;      :   \n" +
                    "|   :    .'    \n" +
                    ";   | .'       \n" +
                    "`---'          \n" +
                    "               ",
            "                \n" +
                    "  .--,-``-.     \n" +
                    " /   /     '.   \n" +
                    "/ ../        ;  \n" +
                    "\\ ``\\  .`-    ' \n" +
                    " \\___\\/   \\   : \n" +
                    "      \\   :   | \n" +
                    "      /  /   /  \n" +
                    "      \\  \\   \\  \n" +
                    "  ___ /   :   | \n" +
                    " /   /\\   /   : \n" +
                    "/ ,,/  ',-    . \n" +
                    "\\ ''\\        ;  \n" +
                    " \\   \\     .'   \n" +
                    "  `--`-,,-'     \n" +
                    "                ",
            "             \n" +
                    "        ,--, \n" +
                    "      ,--.'| \n" +
                    "   ,--,  | : \n" +
                    ",---.'|  : ' \n" +
                    ";   : |  | ; \n" +
                    "|   | : _' | \n" +
                    ":   : |.'  | \n" +
                    "|   ' '  ; : \n" +
                    "\\   \\  .'. | \n" +
                    " `---`:  | ' \n" +
                    "      '  ; | \n" +
                    "      |  : ; \n" +
                    "      '  ,/  \n" +
                    "      '--'   \n" +
                    "             ",
            "       ,----,. \n" +
                    "     ,'   ,' | \n" +
                    "   ,'   .'   | \n" +
                    " ,----.'    .' \n" +
                    " |    |   .'   \n" +
                    " :    :  |--,  \n" +
                    " :    |  ;.' \\ \n" +
                    " |    |      | \n" +
                    " `----'.'\\   ; \n" +
                    "   __  \\  .  | \n" +
                    " /   /\\/  /  : \n" +
                    "/ ,,/  ',-   . \n" +
                    "\\ ''\\       ;  \n" +
                    " \\   \\    .'   \n" +
                    "  `--`-,-'     \n" +
                    "               ",
            "             \n" +
                    "             \n" +
                    "             \n" +
                    "    ,---.    \n" +
                    "   /     \\   \n" +
                    "  /    / '   \n" +
                    " .    ' /    \n" +
                    "'    / ;     \n" +
                    "|   :  \\     \n" +
                    ";   |   ``.  \n" +
                    "'   ;      \\ \n" +
                    "'   |  .\\  | \n" +
                    "|   :  ';  : \n" +
                    " \\   \\    /  \n" +
                    "  `---`--`   \n" +
                    "             ",
            "         ,----, \n" +
                    "       .'   .`| \n" +
                    "    .'   .'   ; \n" +
                    "  ,---, '    .' \n" +
                    "  |   :     ./  \n" +
                    "  ;   | .'  /   \n" +
                    "  `---' /  ;    \n" +
                    "    /  ;  /     \n" +
                    "   ;  /  /      \n" +
                    "  /  /  /       \n" +
                    "./__;  /        \n" +
                    "|   : /         \n" +
                    ";   |/          \n" +
                    "`---'           \n" +
                    "                \n" +
                    "                ",
            "   ,---.-,    \n" +
                    "  '   ,'  '.  \n" +
                    " /   /      \\ \n" +
                    ".   ;  ,/.  : \n" +
                    "'   |  | :  ; \n" +
                    "'   |  ./   : \n" +
                    "|   :       , \n" +
                    " \\   \\     /  \n" +
                    "  ;   ,   '\\  \n" +
                    " /   /      \\ \n" +
                    ".   ;  ,/.  : \n" +
                    "'   |  | :  ; \n" +
                    "'   |  ./   : \n" +
                    "|   :      /  \n" +
                    " \\   \\   .'   \n" +
                    "  `---`-'     ",
            "              \n" +
                    "   ,---.-,    \n" +
                    "  '   ,'  '.  \n" +
                    " /   /      \\ \n" +
                    ".   ;  ,/.  : \n" +
                    "'   |  | :  ; \n" +
                    "'   |  ./   : \n" +
                    "|   :       , \n" +
                    " \\   \\      | \n" +
                    "  `---`---  ; \n" +
                    "     |   |  | \n" +
                    "     '   :  ; \n" +
                    "     |   |  ' \n" +
                    "     ;   |.'  \n" +
                    "     '---'    \n" +
                    "              "};
    public static String[] randomSlogansText = {
            "you are cockroach",
            "i always win",
            "i feel sorry for you you are going to die",
            "i'm stronger",
            "i think you are going to lose",
            "live the life before life the live",
            "i'm king not you",
            "very small >=]",
            "the fighter",
            "angel of death"
    };

    //  source origin from: https://patorjk.com/software/taag/#p=display&f=3D%20Diagonal&t=9
    public static String passwordGenerator() {
        int numberOfLLetters = (int) (Math.random() * 3 + 1);
        int numberOfCLetters = (int) (Math.random() * 3 + 1);
        int numberOfNumbers = (int) (Math.random() * 2 + 2);
        int numberOfSymbols = (int) (Math.random() * 2 + 2);
        char[] numbers = "0123456789".toCharArray();
        char[] lLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] cLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        char[] symbols = "@#$%!&*_.".toCharArray();
        ArrayList<Character> password = new ArrayList<>();
        for (int i = 0; i < numberOfNumbers; i++)
            password.add(numbers[(int) (Math.random() * 10)]);

        for (int i = 0; i < numberOfLLetters; i++)
            password.add(lLetters[(int) (Math.random() * 26)]);

        for (int i = 0; i < numberOfCLetters; i++)
            password.add(cLetters[(int) (Math.random() * 26)]);

        for (int i = 0; i < numberOfSymbols; i++)
            password.add(symbols[(int) (Math.random() * 9)]);

        Collections.shuffle(password);
        String randomPassword = "";
        for (Character character : password) {
            randomPassword += character;
        }
        return randomPassword;
    }

    public static String[] captchaGenerator() {
        int captchaLength = (int) (Math.random() * 5 + 4);
        String[] selectedNumbers = new String[captchaLength];

        for (int i = 0; i < captchaLength; i++) {
            int x = (int) (Math.random() * 10);
            selectedNumbers[i] = captchaNumbers[x];
            realNumber += x;
        }
        return selectedNumbers;
    }

    public static String randomSloganGenerator() {
        return randomSlogansText[(int) (Math.random() * 10)];
    }
    public static String randomCaptcha() {
        String[] a = RandomsAndCaptcha.captchaGenerator();
        for (int i = 0; i < a.length; i++) {             // print captcha
            System.out.print(a[i]);
        }
        System.out.println("\nplease enter the captcha numbers.");
        int answerNumber = ScannerMatcher.getScanner().nextInt();
        String temp = ScannerMatcher.getScanner().nextLine();
        if (answerNumber != (Integer.parseInt(RandomsAndCaptcha.getRealNumber()))) {
            RandomsAndCaptcha.setRealNumber("");
            return "answer doesn't match with captcha";
        }
        RandomsAndCaptcha.setRealNumber("");
        return "successfully done.";
    }
}