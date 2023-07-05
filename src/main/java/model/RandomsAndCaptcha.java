package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RandomsAndCaptcha {
    private static String realNumber = "";

    public static String getRealNumber() {
        return realNumber;
    }

    public static void setRealNumber(String realNumber) {
        RandomsAndCaptcha.realNumber = realNumber;
    }


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


    public static String randomSloganGenerator() {
        return randomSlogansText[(int) (Math.random() * 10)];
    }

    public static String randomPasswordGenerator() {
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


    public static String captchaGenerator() {
        Random random = new Random();

        int indexOfRandomCaptcha = (int) random.nextInt(21);
        String number = images[indexOfRandomCaptcha];
        realNumber = number;
        return number;
    }
    public static String[] images = {"1181" , "1381", "1491", "1722", "1959", "2163", "2177", "2723", "2785", "3541",
            "3847", "3855", "3876", "3967", "4185", "4310", "4487", "4578", "4602", "5326", "5771"};
}