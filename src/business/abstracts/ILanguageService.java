package business.abstracts;

import entities.Language;
import entities.LanguageName;

public interface ILanguageService {

    Language createLanguage(LanguageName languageName);

}
