package entities;

public class SpeakingQuestion extends Question {

    private final Audio audio1;
    private final Audio audio2;

    public SpeakingQuestion(Audio audio1, Audio audio2) {
        super(8);
        this.audio1 = audio1;
        this.audio2 = audio2;
    }

    public SpeakingQuestion(SpeakingQuestion oldSpeakingQuestion) {
        super();
        if (oldSpeakingQuestion == null) {
            System.out.println("Fatal Error!");
            System.exit(0);
        }
        this.audio1 = oldSpeakingQuestion.audio1;
        this.audio2 = oldSpeakingQuestion.audio2;
    }

    @Override
    public SpeakingQuestion clone() {
        return new SpeakingQuestion(this);
    }

}
