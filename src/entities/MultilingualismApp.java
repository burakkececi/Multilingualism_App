package entities;

import business.abstracts.*;
import business.concretes.LanguageCreator;
import business.concretes.QuestionCreator;
import business.concretes.QuizCreator;
import business.concretes.UnitCreator;
import dataAccess.abstracts.IFileWriter;
import dataAccess.concretes.CSVFileWriter;
import errors.BusinessException;
import errors.ErrorType;

import java.util.ArrayList;
import java.util.List;

public class MultilingualismApp {

    private final List<User> users;
    private List<Language> languages;
    private List<League> leagues;

    public MultilingualismApp(List<User> users) {
        this.users = users;
    }

    private ILanguageService createLanguageCreator() {
        IQuestionService iQuestionService = new QuestionCreator();
        IQuizService iQuizService = new QuizCreator(iQuestionService);
        IUnitService iUnitService = new UnitCreator(iQuizService);
        return new LanguageCreator(iUnitService);
    }

    private void createLanguages() {
        this.languages = new ArrayList<>();
        ILanguageService iLanguageService = createLanguageCreator();
        for (LanguageName language : LanguageName.values()) {
            languages.add(iLanguageService.createLanguage(language));
        }
    }

    private Language findTheLanguage(LanguageName languageName) {
        for (Language language : languages) {
            if (language.getLanguageName().equals(languageName)) return language;
        }
        throw new BusinessException(ErrorType.LANGUAGE_NOT_FOUND, "Language not found: " + languageName);
    }
    private List<Language> getLanguages() {
        return languages;
    }

    private void saveLanguagesToFile() {
        IFileWriter iFileWriter = new CSVFileWriter();
        iFileWriter.writeLanguageDetails(getLanguages(), "languages.csv");
    }

    private LanguageName getRandomLanguage() {
        int index = IRandomNumber.generateRandomNumber(0, LanguageName.values().length - 1);
        return LanguageName.values()[index];
    }

    private void usersChooseLanguages() {
        for (User user : users) {
            user.setChosenLanguage(getRandomLanguage());
        }
    }

    private void solveQuiz(User user, Quiz quiz) {
        for (Question question : quiz.getQuestions()) {
            if (user.solveQuestion()) {
                user.addPoints(question.getPoints());
            }
        }
    }

    private int userTakeQuizzes(User user) {
        int numberOfQuizzes = findTheLanguage(user.getChosenLanguage()).getNumberOfQuizzes();
        int numberOfQuizzesToSolve = user.decideTheNumberOfQuizzes(1, numberOfQuizzes);
        int numberOfUnits = 0;
        int solvedQuizzes = 0;
        for (Unit unit : findTheLanguage(user.getChosenLanguage()).getUnits()) {
            numberOfUnits++;
            for (Quiz quiz : unit.getQuizzes()) {
                if (solvedQuizzes == numberOfQuizzesToSolve) {
                    break;
                } else {
                    solveQuiz(user, quiz);
                }
                solvedQuizzes++;
            }
        }
        return numberOfUnits;
    }

    private void usersTakesQuizzes() {
        for (User user : users) {
            user.setNumberOfSolvedUnits(userTakeQuizzes(user));
        }
    }

    private void createLeagues() {
        leagues = new ArrayList<>();
        for (LanguageName languageName : LanguageName.values()) {
            leagues.add(new League(languageName));
        }
    }

    private void fillTheLeagues() {
        for (User user : users) {
            for (League league : leagues) {
                if (user.getChosenLanguage().equals(league.getLanguageName())) {
                    System.out.println("User added to league " + league.getLanguageName());
                    league.addUser(user);
                }
            }
        }
    }

    private void distributeUsersToLeagues() {
        for (League league : leagues) {
            league.distributeUsersToLeagues();
        }
    }

    public void start() {
        createLanguages();
        saveLanguagesToFile();
        createLeagues();
        usersChooseLanguages();
        usersTakesQuizzes();
        fillTheLeagues();
        distributeUsersToLeagues();
    }

}


