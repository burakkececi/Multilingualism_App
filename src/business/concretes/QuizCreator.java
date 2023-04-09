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

    private void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    private void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public Quiz createQuiz(int index) {
        int numberOfQuestions = IRandomNumber.generateRandomNumber(MIN_QUESTION, MAX_QUESTION);
        setQuestions(new ArrayList<>());
        for (int i = 0; i < numberOfQuestions; i++) {
            try {
               addQuestion(iQuestionService.createQuestion());
            } catch (BusinessException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return new Quiz(questions, index);
    }

}
