package business.abstracts;

import entities.Language;
import entities.LanguageName;

public interface ILanguageService {

    /**
     * Create a language that given name.
     * @param languageName is an enum type value to specify which language name will be created.
     * @return Language object is created named by @param languageName.
     */
    Language createLanguage(LanguageName languageName);

}
