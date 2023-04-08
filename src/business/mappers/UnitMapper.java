package business.mappers;

import entities.Quiz;
import entities.Unit;

import java.util.ArrayList;
import java.util.List;

public class UnitMapper implements CSVMapper{

    private int index;
    public UnitMapper(int index) {
        this.index = index;
    }

    @Override
    public Unit map(String[] data) {
        List<Quiz> quizzes = new ArrayList<>();
        for (int i = index; i < data.length; i++) {
            if(data[i].strip().startsWith("Quiz")){
                quizzes.add(new QuizMapper(i+1).map(data));
            }
            else if(data[i].startsWith("Unit")){
                break;
            }
        }
        return new Unit(quizzes);
    }
}
