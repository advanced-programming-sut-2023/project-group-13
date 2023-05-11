package model;

import java.util.ArrayList;
import java.util.Collections;

public class RandomsAndCaptcha {
    private static String  realNumber = "";

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
        int numberOfCapitalLetters = (int) (Math.random() * 5 + 2);
        int numberOfLowercaseLetters = (int) (Math.random() * 5 + 2);
        int numberOfSymbols = (int) (Math.random() * 4 + 1);
        int numberOfNumbers = (int) (Math.random() * 4 + 1);
        char[] validSymbols = "@#$%!&*_.".toCharArray();
        char[] numbers = "0123456789".toCharArray();
        char[] lowercaseLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        ArrayList<Character> passwordLetters = new ArrayList<>();
        for (int i = 0; i < numberOfCapitalLetters; i++) passwordLetters.add(capitalLetters[(int)(Math.random() * 26)]);
        for (int i = 0; i < numberOfSymbols; i++) passwordLetters.add(validSymbols[(int)(Math.random() * 9)]);
        for (int i = 0; i < numberOfNumbers; i++) passwordLetters.add(numbers[(int)(Math.random() * 10)]);
        for (int i = 0; i < numberOfLowercaseLetters; i++) passwordLetters.add(lowercaseLetters[(int)(Math.random() * 26)]);
        Collections.shuffle(passwordLetters);
        String randomPassword = "";
        for (int i = 0; i < passwordLetters.size(); i++) {
            randomPassword += passwordLetters.get(i);
        }
        return randomPassword;
    }
    public static String[] captchaGenerator() {
        int captchaLength =(int)(Math.random() * 5 + 4);
        String[] selectedNumbers = new String[captchaLength];

        for (int i = 0; i < captchaLength; i++) {
            int x = (int)(Math.random()*10);
            selectedNumbers[i] = captchaNumbers[x];
            realNumber += x;
        }
        return selectedNumbers;
    }
    public static String randomSloganGenerator() {
        return randomSlogansText[(int)(Math.random() * 10)];
    }
}