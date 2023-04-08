package entities;

import java.util.Map;

public class WordMatchingQuestion extends Question {

    private final Map<String, String> wordMap;

    public WordMatchingQuestion(Map<String, String> wordMap) {
        super(5);
        this.wordMap = wordMap;
    }

}
