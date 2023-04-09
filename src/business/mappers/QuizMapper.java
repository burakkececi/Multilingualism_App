package business.mappers;

import entities.Quiz;

public class QuizMapper implements CSVMapper{

    private final int index;

    public QuizMapper(int index) {
        this.index = index;
    }

    @Override
    public Quiz map(String[] data) {
        return new Quiz(new QuestionMapper(index).map(data));
    }

}
