package nl.shizleshizle.hediumcore.utils;

import java.util.Random;

public class Numbers {

    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException nf) {
                return false;
            }
        }
    }

    public static int getInt(String str) {
        int returned;
        try {
            returned = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            returned = 0;
        }
        return returned;
    }

    public static int getRandom(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
