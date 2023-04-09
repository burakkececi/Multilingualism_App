package entities;

public class ReadingQuestion extends Question {

    private final String stringQuestion1;
    private final String stringQuestion2;

    public ReadingQuestion(String stringQuestion1, String stringQuestion2) {
        super(10);
        this.stringQuestion1 = stringQuestion1;
        this.stringQuestion2 = stringQuestion2;
    }

}
