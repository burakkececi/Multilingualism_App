package business.concretes;

import business.abstracts.*;
import business.mappers.LanguageMapper;
import dataAccess.abstracts.IFileReader;
import dataAccess.abstracts.IFileWriter;
import dataAccess.concretes.CSVFileReader;
import dataAccess.concretes.CSVFileWriter;
import entities.Language;
import entities.LanguageName;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LanguageValidator implements ILanguageValidate {

    private final String dataPath;
    private List<Language> languages;

    public LanguageValidator(String dataPath) {
        this.dataPath = dataPath;
        this.languages = new ArrayList<>();
    }

    private ILanguageService createLanguageCreator() {
        setLanguageList(this.languages);
        IQuestionService iQuestionService = new QuestionCreator();
        IQuizService iQuizService = new QuizCreator(iQuestionService);
        IUnitService iUnitService = new UnitCreator(iQuizService);
        return new LanguageCreator(iUnitService);
    }

    public List<Language> getLanguages() {
        if (isFileExist(dataPath)) {
            return getLanguagesFromFile(dataPath);
        } else {
            createLanguages();
            saveLanguagesToFile(dataPath);
            return getLanguageList();
        }
    }

    private void createLanguages() {
        ILanguageService iLanguageService = createLanguageCreator();
        for (LanguageName language : LanguageName.values()) {
            addLanguage(iLanguageService.createLanguage(language));
        }
    }

    private void saveLanguagesToFile(String filename) {
        IFileWriter iFileWriter = new CSVFileWriter();
        iFileWriter.writeLanguageDetails(getLanguageList(), filename);
    }

    private void addLanguage(Language language) {
        this.languages.add(language);
    }

    private List<Language> getLanguageList() {
        return this.languages;
    }

    private void setLanguageList(List<Language> languages) {
        this.languages = languages;
    }

    private List<Language> getLanguagesFromFile(String dataPath) {
        IFileReader<Language> iFileReader = new CSVFileReader<>(dataPath, ",", new LanguageMapper());
        setLanguageList(iFileReader.getAll());
        return getLanguageList();
    }

    private boolean isFileExist(String dataPath) {
        return new File(dataPath).exists();
    }

}
