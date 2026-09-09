package utils;

import java.util.Random;

public class RandomUtils {

    private static Random random = new Random();

    public static int randomIndex(int size) {
        return random.nextInt(size);
    }
}