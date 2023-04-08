package business.concretes;

import business.abstracts.ILanguageService;
import business.abstracts.IQuestionService;
import business.abstracts.IQuizService;
import business.abstracts.IUnitService;
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

public class LanguageValidator {

    private final String datapath;
    private List<Language> languages;

    public LanguageValidator(String datapath) {
        this.datapath = datapath;
    }

    private ILanguageService createLanguageCreator() {
        IQuestionService iQuestionService = new QuestionCreator();
        IQuizService iQuizService = new QuizCreator(iQuestionService);
        IUnitService iUnitService = new UnitCreator(iQuizService);
        return new LanguageCreator(iUnitService);
    }

    public List<Language> getLanguages() {
        if (isFileExist(datapath)) {
            return getLanguagesFromFile(datapath);

        } else {
            this.languages = createLanguages();
            saveLanguagesToFile(datapath);
            return this.languages;
        }
    }

    private List<Language> createLanguages() {
        this.languages = new ArrayList<>();
        ILanguageService iLanguageService = createLanguageCreator();
        for (LanguageName language : LanguageName.values()) {
            languages.add(iLanguageService.createLanguage(language));
        }
        return languages;
    }

    private void saveLanguagesToFile(String filename) {
        IFileWriter iFileWriter = new CSVFileWriter();
        iFileWriter.writeLanguageDetails(getLanguageList(), filename);
    }

    private List<Language> getLanguageList() {
        return this.languages;
    }

    private List<Language> getLanguagesFromFile(String datapath) {
        this.languages = new ArrayList<>();
        IFileReader<Language> iFileReader = new CSVFileReader<>(datapath, ",", new LanguageMapper());
        this.languages = iFileReader.getAll();
        return this.languages;
    }

    private boolean isFileExist(String datapath) {
        File file = new File(datapath);
        return file.exists();
    }


}
