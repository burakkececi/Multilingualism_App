package business.abstracts;

import entities.Question;

public interface IQuestionService {

    Question createQuestion();
    Question createQuestion(String questionType);

}
