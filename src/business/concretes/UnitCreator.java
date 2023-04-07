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

    public UnitCreator(IQuizService iQuizService) {
        this.iQuizService = iQuizService;
    }

    @Override
    public Unit createUnit(int index) {
        List<Quiz> quizzes = new ArrayList<>();
        int numberOfQuizzes = IRandomNumber.generateRandomNumber(MIN_QUIZ, MAX_QUIZ);
        for (int i = 0; i < numberOfQuizzes; i++) {
            quizzes.add(iQuizService.createQuiz(i + 1));
        }
        return new Unit(quizzes, index);
    }

}
