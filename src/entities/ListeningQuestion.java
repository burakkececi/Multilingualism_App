package entities;

public class ListeningQuestion extends Question {

    private final Audio audioQuestion;
    private final String stringQuestion;

    public ListeningQuestion(String stringQuestion, Audio audioQuestion) {
        super(7);
        this.stringQuestion = stringQuestion;
        this.audioQuestion = audioQuestion;
    }

}
