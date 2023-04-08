package business.abstracts;

import java.util.Random;

public interface IRandomNumber {

    /**
     * Generates random number in specified values.
     * @param min is the floor of the random number.
     * @param max is the ceil of the random number.
     * @return integer random value.
     */
    static int generateRandomNumber(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

}
