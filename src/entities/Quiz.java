package entities;

import java.util.List;

public class Quiz {

    private int quizNo;
    private final List<Question> questions;

    public Quiz(List<Question> questions) {
        this.questions = questions;
    }

    public Quiz(List<Question> questions, int quizNo) {
        this.questions = questions;
        this.quizNo = quizNo;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getQuizNo() {
        return quizNo;
    }

    private <T> int getNumberOfQuestion(Class<T> c){
        return (int) questions.stream()
                .filter(question -> question.getClass().getSimpleName().equals(c.getSimpleName()))
                .count();
    }


    @Override
    public String toString() {
        int numR = getNumberOfQuestion(ReadingQuestion.class),
                numL = getNumberOfQuestion(ListeningQuestion.class),
                numS = getNumberOfQuestion(SpeakingQuestion.class),
                numW = getNumberOfQuestion(WordMatchingQuestion.class);

        return "Quiz " + getQuizNo() + ", "
                + numR + "R:"
                + numL + "L:"
                + numS + "S:"
                + numW + "W ";
    }

}
