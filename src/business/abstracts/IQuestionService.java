package business.abstracts;

import entities.Question;

public interface IQuestionService {

    /**
     * Creates question object that is random question type.
     * @return Question object.
     */
    Question createQuestion();

    /**
     * Creates question object by given question type.
     * @param questionType specifies the exact question type.
     * @return Question object.
     */
    Question createQuestion(String questionType);

}
