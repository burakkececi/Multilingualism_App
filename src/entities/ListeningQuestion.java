package entities;

public class ListeningQuestion extends Question {

    private final Audio audioQuestion;
    private final String stringQuestion;

    public ListeningQuestion(String stringQuestion, Audio audioQuestion) {
        super(7);
        this.stringQuestion = stringQuestion;
        this.audioQuestion = audioQuestion;
    }

    public ListeningQuestion(ListeningQuestion oldListeningQuestion) {
        super();
        if (oldListeningQuestion == null) {
            System.out.println("Fatal Error!");
            System.exit(0);
        }
        this.stringQuestion = oldListeningQuestion.stringQuestion;
        this.audioQuestion = oldListeningQuestion.audioQuestion;
    }

    @Override
    public ListeningQuestion clone() {
        return new ListeningQuestion(this);
    }

}
