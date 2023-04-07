package business.concretes;

import business.abstracts.IQuestionService;
import business.abstracts.IQuizService;
import business.abstracts.IRandomNumber;
import entities.Question;
import entities.Quiz;
import errors.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class QuizCreator implements IQuizService {

    private final IQuestionService iQuestionService;
    private final int MIN_QUESTION = 8;
    private final int MAX_QUESTION = 15;
    private List<Question> questions;

    public QuizCreator(IQuestionService iQuestionService) {
        this.iQuestionService = iQuestionService;

    }

    @Override
    public Quiz createQuiz(int index) {
        int numberOfQuestions = IRandomNumber.generateRandomNumber(MIN_QUESTION, MAX_QUESTION);
        questions = new ArrayList<>();
        for (int i = 0; i < numberOfQuestions; i++) {
            try {
                questions.add(iQuestionService.createQuestion());
            } catch (BusinessException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return new Quiz(questions, index);
    }

    public List<Question> getQuestion() {
        return questions;
    }

}
