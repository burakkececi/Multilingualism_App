package business.abstracts;

import entities.Audio;

public interface IQuestionGenerator {

    /**
     * Generates random audio question.
     * @param minDuration defines the audio min duration.
     * @param maxDuration defines the audio max duration.
     * @return Audio object that has random duration in seconds.
     */
    static Audio generateRandomAudio(int minDuration, int maxDuration) {
        int durationInSeconds = IRandomNumber.generateRandomNumber(minDuration, maxDuration);
        return new Audio(durationInSeconds);
    }

    /**
     * Generates random string question.
     * @return String object that has random strings.
     */
    static String generateRandomString() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
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
