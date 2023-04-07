package entities;

public class ReadingQuestion extends Question {

    private final String stringQuestion1;
    private final String stringQuestion2;

    public ReadingQuestion(String stringQuestion1, String stringQuestion2) {
        super(10);
        this.stringQuestion1 = stringQuestion1;
        this.stringQuestion2 = stringQuestion2;
    }

    public ReadingQuestion(ReadingQuestion oldReadingQuestion) {
        super();
        if (oldReadingQuestion == null) {
            System.out.println("Fatal Error!");
            System.exit(0);
        }
        this.stringQuestion1 = oldReadingQuestion.stringQuestion1;
        this.stringQuestion2 = oldReadingQuestion.stringQuestion2;
    }

    @Override
    public ReadingQuestion clone() {
        return new ReadingQuestion(this);
    }

}
