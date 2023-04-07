package dataAccess.abstracts;

import entities.Language;
import entities.User;

import java.util.List;

public interface IFileWriter {

    void writeLanguageDetails(List<Language> language, String filename);

    void writeUserDetails(List<User> users, String filename);
}
