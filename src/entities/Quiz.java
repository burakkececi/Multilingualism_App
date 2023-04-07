package entities;

import java.util.List;

public class Quiz {

    private final int quizNo;
    private final List<Question> questions;

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

    private int getNumberOfQuestion(Class c){
        return (int) questions.stream()
                .filter(question -> question.getClass().getSimpleName().equals(c.getSimpleName()))
                .count();
    }

// TODO: 7.04.2023 incelerken burayı unutma
//    private <T> int getNumberOfQuestion(T questionType){
//        return (int) questions.stream()
//                .filter(question -> question.getClass().getSimpleName().equals(questionType.getClass().getSimpleName()))
//                .count();
//    }

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
