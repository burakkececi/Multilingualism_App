package entities;

import java.util.ArrayList;
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
        List<Unit> copyUnits = new ArrayList<>();
        for (Unit unit : units) {
            copyUnits.add(new Unit(unit));
        }
        return copyUnits;
    }

    public int getNumberOfQuizzes() {
        int totalNumberOfQuizzes = 0;
        for (Unit unit : units) {
            totalNumberOfQuizzes += unit.getNumberOfQuizzes();
        }
        return totalNumberOfQuizzes;
    }
}
