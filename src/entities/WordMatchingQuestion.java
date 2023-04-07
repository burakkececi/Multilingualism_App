package entities;

import java.util.Map;

public class WordMatchingQuestion extends Question {

    private final Map<String, String> wordMap;

    public WordMatchingQuestion(Map<String, String> wordMap) {
        super(5);
        this.wordMap = wordMap;
    }

    public WordMatchingQuestion(WordMatchingQuestion oldWordMatchingQuestion) {
        super();
        if (oldWordMatchingQuestion == null) {
            System.out.println("Fatal Error!");
            System.exit(0);
        }
        this.wordMap = oldWordMatchingQuestion.wordMap; // there is not an object inside.
    }

    @Override
    public WordMatchingQuestion clone() {
        return new WordMatchingQuestion(this);
    }

}
