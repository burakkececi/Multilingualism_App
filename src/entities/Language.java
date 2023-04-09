package entities;

import java.util.List;

public class Language {

    private final LanguageName languageName;
    private final List<Unit> units;

    public Language(LanguageName name, List<Unit> units) {
        this.languageName = name;
        this.units = units;
    }

    public LanguageName getLanguageName() {
        return languageName;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public int getNumberOfQuizzes() {
        int totalNumberOfQuizzes = 0;
        for (Unit unit : units) {
            totalNumberOfQuizzes += unit.getNumberOfQuizzes();
        }
        return totalNumberOfQuizzes;
    }

    public int getNumberOfUnits() {
        return units.size();
    }

}
