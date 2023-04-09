package business.mappers;

import entities.Language;
import entities.LanguageName;
import entities.Unit;

import java.util.ArrayList;
import java.util.List;

public class LanguageMapper implements CSVMapper {

    @Override
    public Language map(String[] data) {
        List<Unit> units = new ArrayList<>();
        int index = 0;
        for (String token: data) {
            if(token.strip().startsWith("Unit")){
                units.add(new UnitMapper(index+1).map(data));
            }
            index++;
        }
        return new Language(LanguageName.valueOf(data[0]), units);
    }

}
