package business.abstracts;

import entities.Quiz;

public interface IQuizService {

    /**
     * Creates quiz object.
     * @param index examine the quiz number.
     * @return Quiz object.
     */
    Quiz createQuiz(int index);

}
