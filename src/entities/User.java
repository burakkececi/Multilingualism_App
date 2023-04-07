package entities;

import business.abstracts.IRandomNumber;

import java.io.Serializable;

public class User implements Serializable {

    private final String username;
    private final String password;
    private final int streakDays;
    private int numberOfSolvedUnits;
    private int numberOfSolvedQuizzes;
    private int totalPoints;
    private LanguageName chosenLanguage;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.streakDays = generateStreakDays();
    }

    public int getNumberOfSolvedUnits() {
        return numberOfSolvedUnits;
    }

    public void setNumberOfSolvedUnits(int numberOfSolvedUnits) {
        this.numberOfSolvedUnits = numberOfSolvedUnits;
    }

    public int getStreakDays() {
        return streakDays;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void addPoints(int points) {
        this.totalPoints += points;
    }
    public int generateStreakDays() {
        // randomly generated streak between 0 and 365 days
        return (int) (Math.random() * 366);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setChosenLanguage(LanguageName chosenLanguage) {
        this.chosenLanguage = chosenLanguage;
    }

    public LanguageName getChosenLanguage() {
        return chosenLanguage;
    }

    public boolean solveQuestion() {
        int random = IRandomNumber.generateRandomNumber(0, 1);
        return random == 1;
    }

    public int decideTheNumberOfQuizzes(int min, int max) {
        return IRandomNumber.generateRandomNumber(min, max);
    }

    public int getNumberOfSolvedQuizzes() {
        return numberOfSolvedQuizzes;
    }

    public void setNumberOfSolvedQuizzes(int numberOfQuizzesToSolve) {
        this.numberOfSolvedQuizzes = numberOfQuizzesToSolve;
    }
}
