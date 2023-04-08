package entities;

public abstract class Question {

    private int points;

    public Question() {
    }

    public Question(int pointValue) {
        this.points = pointValue;
    }

    public int getPoints() {
        return points;
    }

}
