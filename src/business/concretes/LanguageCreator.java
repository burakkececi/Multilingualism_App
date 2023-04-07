package business.concretes;

import business.abstracts.ILanguageService;
import business.abstracts.IRandomNumber;
import business.abstracts.IUnitService;
import entities.Language;
import entities.LanguageName;
import entities.Unit;

import java.util.ArrayList;
import java.util.List;

public class LanguageCreator implements ILanguageService {

    private final IUnitService iUnitService;
    private final int MIN_UNIT = 60;
    private final int MAX_UNIT = 100;

    public LanguageCreator(IUnitService iUnitService) {
        this.iUnitService = iUnitService;
    }

    public Language createLanguage(LanguageName languageName) {
        List<Unit> units = new ArrayList<>();
        int numberOfUnits = IRandomNumber.generateRandomNumber(MIN_UNIT, MAX_UNIT);
        for (int i = 0; i < numberOfUnits; i++) {
            units.add(iUnitService.createUnit(i+1));
        }
        return new Language(languageName, units);
    }

}
