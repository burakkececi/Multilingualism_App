package entities;

public abstract class Question implements Cloneable {

    private int points;

    public Question() {
    }

    public Question(int pointValue) {
        this.points = pointValue;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public abstract Question clone();

}
