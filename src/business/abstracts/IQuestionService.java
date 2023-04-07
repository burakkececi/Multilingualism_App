package business.abstracts;

import entities.Question;
import errors.BusinessException;

public interface IQuestionService {

    Question createQuestion() throws BusinessException;

}
