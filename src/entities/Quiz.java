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

    @Override
    public String toString() {
        int numR = 0, numL = 0, numS = 0, numW = 0;
        for (Question question: getQuestions()) {
            if(question.getClass() == ReadingQuestion.class) numR++;
            else if(question.getClass() == ListeningQuestion.class) numL++;
            else if(question.getClass() == SpeakingQuestion.class) numS++;
            else if(question.getClass() == WordMatchingQuestion.class) numW++;
        }
        return "Quiz " + getQuizNo() + ", "
                + numR + "R:"
                + numL + "L:"
                + numS + "S:"
                + numW + "W ";
    }
}
