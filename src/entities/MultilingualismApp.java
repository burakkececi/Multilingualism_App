package entities;

import business.abstracts.ILanguageValidate;
import business.abstracts.IRandomNumber;
import business.concretes.LanguageValidator;
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

    private final String DATAPATH_LANGUAGE = "languages.csv";
    private final String DATAPATH_USERS = "users.csv";

    public MultilingualismApp(List<User> users) {
        this.users = users;
    }

    private Language findTheLanguage(LanguageName languageName) {
        for (Language language : languages) {
            if (language.getLanguageName().equals(languageName)) return language;
        }
        throw new BusinessException(ErrorType.LANGUAGE_NOT_FOUND, "Language not found: " + languageName);
    }

    private void createLanguages(String dataPath){
        ILanguageValidate languageValidator = new LanguageValidator(dataPath);
        this.languages = languageValidator.getLanguages();
    }

    private List<Language> getLanguages() {
        return languages;
    }

    private List<User> getUsers() {
        return users;
    }

    private void saveUsersToFile(String filename) {
        IFileWriter iFileWriter = new CSVFileWriter();
        iFileWriter.writeUserDetails(getUsers(), filename);
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
        user.setNumberOfSolvedQuizzes(numberOfQuizzesToSolve);

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

    private User findTheUserWithMaxPoints() {
        User userWithMaxPoints = users.get(0);
        for (User user : users) {
            if (user.getTotalPoints() > userWithMaxPoints.getTotalPoints()) {
                userWithMaxPoints = user;
            }
        }
        return userWithMaxPoints;
    }

    private LanguageName findTheLanguageWithMaxNumberOfUnits() {
        LanguageName languageName = LanguageName.values()[0];
        int maxNumberOfUnits = 0;
        for (Language language : languages) {
            if (language.getNumberOfUnits() > maxNumberOfUnits) {
                maxNumberOfUnits = language.getNumberOfUnits();
                languageName = language.getLanguageName();
            }
        }
        return languageName;
    }

    private LanguageName findTheLanguageWithMaxNumberOfQuizzes() {
        LanguageName languageName = LanguageName.values()[0];
        int maxNumberOfQuizzes = 0;
        for (Language language : languages) {
            if (language.getNumberOfQuizzes() > maxNumberOfQuizzes) {
                maxNumberOfQuizzes = language.getNumberOfQuizzes();
                languageName = language.getLanguageName();
            }
        }
        return languageName;
    }

    private List<User> findTheTopNUsersInGivenLeagueForGivenLanguage(LanguageName languageName, String leagueName, int n) {
        List<User> topThreeUsers = new ArrayList<>();
        for (League league : leagues) {
            if (league.getLanguageName().equals(languageName)) {
                topThreeUsers = league.getTheBestUsersInLeague(leagueName, n);
            }
        }
        return topThreeUsers;
    }

    private User findTheUserWhoSolvedTheMostUnits(LanguageName languageName) {
        User userWhoSolvedTheMostUnits = users.get(0);
        for (User user : users) {
            if (user.getChosenLanguage().equals(languageName)) {
                if (user.getNumberOfSolvedUnits() > userWhoSolvedTheMostUnits.getNumberOfSolvedUnits()) {
                    userWhoSolvedTheMostUnits = user;
                }
            }
        }
        return userWhoSolvedTheMostUnits;
    }

    private Language findTheLanguageFromLanguageName(LanguageName languageName) {
        for (Language language : languages) {
            if (language.getLanguageName().equals(languageName)) {
                return language;
            }
        }
        throw new BusinessException(ErrorType.LANGUAGE_NOT_FOUND, "Language not found: " + languageName);
    }


    private String formatLanguageName(LanguageName language) {
        String languageName = language.name();
        return Character.toUpperCase(languageName.charAt(0)) + languageName.substring(1).toLowerCase();
    }

    private void display() {
        System.out.println("1- " + findTheUserWithMaxPoints().getUsername() + " " + findTheUserWithMaxPoints().getTotalPoints() + " points");
        System.out.println("2- " + findTheUserWhoSolvedTheMostUnits(LanguageName.GERMAN).getUsername() + " Unit " + findTheUserWhoSolvedTheMostUnits(LanguageName.GERMAN).getNumberOfSolvedUnits());
        System.out.println("3- " + formatLanguageName(findTheLanguageWithMaxNumberOfUnits()) + " " + findTheLanguageFromLanguageName(findTheLanguageWithMaxNumberOfUnits()).getNumberOfUnits() + " Units");
        System.out.println("4- " + formatLanguageName(findTheLanguageWithMaxNumberOfQuizzes()) + " " + findTheLanguageFromLanguageName(findTheLanguageWithMaxNumberOfQuizzes()).getNumberOfQuizzes() + " Quizzes");
        System.out.println("5- Italian Silver League Top 3: " + "1." + findTheTopNUsersInGivenLeagueForGivenLanguage(LanguageName.ITALIAN, "Silver", 3).get(0).getUsername()
                + " 2." + findTheTopNUsersInGivenLeagueForGivenLanguage(LanguageName.ITALIAN, "Silver", 3).get(1).getUsername()
                + " 3." + findTheTopNUsersInGivenLeagueForGivenLanguage(LanguageName.ITALIAN, "Silver", 3).get(2).getUsername());
    }

    public void start() {
        createLanguages(DATAPATH_LANGUAGE);
        createLeagues();
        usersChooseLanguages();
        usersTakesQuizzes();
        fillTheLeagues();
        distributeUsersToLeagues();
        saveUsersToFile(DATAPATH_USERS);
        display();
    }

}


