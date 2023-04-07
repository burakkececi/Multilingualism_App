package business.abstracts;

import java.util.Random;

public interface IRandomNumber {

    static int generateRandomNumber(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

}
