package business.concretes;

import business.abstracts.IQuizService;
import business.abstracts.IRandomNumber;
import business.abstracts.IUnitService;
import entities.Quiz;
import entities.Unit;

import java.util.ArrayList;
import java.util.List;


public class UnitCreator implements IUnitService {

    private final IQuizService iQuizService;
    private final int MIN_QUIZ = 1;
    private final int MAX_QUIZ = 10;
    private List<Quiz> quizzes;

    public UnitCreator(IQuizService iQuizService) {
        this.iQuizService = iQuizService;
    }

    private void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    private void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    @Override
    public Unit createUnit(int index) {
        setQuizzes(new ArrayList<>());
        int numberOfQuizzes = IRandomNumber.generateRandomNumber(MIN_QUIZ, MAX_QUIZ);
        for (int i = 0; i < numberOfQuizzes; i++) {
            addQuiz(iQuizService.createQuiz(i + 1));
        }
        return new Unit(quizzes, index);
    }

}
