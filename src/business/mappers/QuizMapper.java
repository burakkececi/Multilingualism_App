package business.mappers;

import entities.Question;
import entities.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizMapper implements CSVMapper{

    private int index;
    public QuizMapper(int index) {
        this.index = index;
    }

    @Override
    public Quiz map(String[] data) {
        List<Question> questions = new QuestionMapper(index).map(data);
        return new Quiz(questions);
    }
}
