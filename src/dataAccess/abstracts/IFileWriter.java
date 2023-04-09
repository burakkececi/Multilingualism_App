package dataAccess.abstracts;

import entities.Language;
import entities.User;

import java.util.List;

public interface IFileWriter {

    /**
     * Writes language details that has units, quizzes and questions to file.
     * @param language is the given to write to file.
     * @param filename is the name of the file.
     */
    void writeLanguageDetails(List<Language> language, String filename);

    /**
     * Writes user details that has username, password, total points etc. to file.
     * @param users is the given to write to file.
     * @param filename is the name of the file.
     */
    void writeUserDetails(List<User> users, String filename);

}
