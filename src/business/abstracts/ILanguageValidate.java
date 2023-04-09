package business.abstracts;

import entities.Language;

import java.util.List;

public interface ILanguageValidate {

    /**
     * Function to get all languages that has been created.
     * @return List of languages that has been created.
     */
    List<Language> getLanguages();

}
