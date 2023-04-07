package business.abstracts;

import entities.Audio;

public interface IQuestionGenerator {

    static Audio generateRandomAudio(int minDuration, int maxDuration) {
        int durationInSeconds = IRandomNumber.generateRandomNumber(minDuration, maxDuration);
        return new Audio(durationInSeconds);
    }

    static String generateRandomString() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        // Generate the random string
        StringBuilder sb = new StringBuilder();
        int length = IRandomNumber.generateRandomNumber(0, 10);
        for (int i = 0; i < length; i++) {
            int index = IRandomNumber.generateRandomNumber(0, chars.length() - 1);
            char randomChar = chars.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

}
