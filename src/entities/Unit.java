package entities;

import java.util.List;

public class Unit {

    private int unitNo;
    private final List<Quiz> quizzes;

    public Unit(List<Quiz> quizzes){
        this.quizzes = quizzes;
    }

    public Unit(List<Quiz> quizzes, int unitNo) {
        this.quizzes = quizzes;
        this.unitNo = unitNo;
    }

    public Unit(Unit oldUnit) {
        if (oldUnit == null) {
            System.out.println("Fatal Error!");
            System.exit(0);
        }
        this.quizzes = oldUnit.quizzes;
        this.unitNo = oldUnit.unitNo;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public int getNumberOfQuizzes() {
        return quizzes.size();
    }

    @Override
    public String toString() {
        return "Unit " + getUnitNo() + ", ";
    }

    private int getUnitNo() {
        return unitNo;
    }
}
