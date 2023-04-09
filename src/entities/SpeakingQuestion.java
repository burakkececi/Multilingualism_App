package entities;

public class SpeakingQuestion extends Question {

    private final Audio audio1;
    private final Audio audio2;

    public SpeakingQuestion(Audio audio1, Audio audio2) {
        super(8);
        this.audio1 = audio1;
        this.audio2 = audio2;
    }

}
