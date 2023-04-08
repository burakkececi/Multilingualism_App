package dataAccess.concretes;

import dataAccess.abstracts.IFileWriter;
import entities.Language;
import entities.Quiz;
import entities.Unit;
import entities.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class CSVFileWriter implements IFileWriter {

    @Override
    public void writeLanguageDetails(List<Language> languages, String filename) {

        File file = new File(filename);

        if (file.exists()) {
            System.out.println(filename + " file exists!");
        } else {
            try (PrintWriter writer = new PrintWriter(file)) {
                for (Language language : languages) {
                    writer.append(language.getLanguageName().name()).append(", ");
                    for (Unit unit : language.getUnits()) {
                        writer.append(unit.toString());
                        for (Quiz quiz : unit.getQuizzes()) {
                            writer.append(quiz.toString()).append(", ");
                        }
                    }
                    writer.append("\n");
                }
                System.out.println(filename + " is successfully created.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void writeUserDetails(List<User> users, String filename) {

        File file = new File(filename);
        try (PrintWriter writer = new PrintWriter(file)) {
            users.forEach(user -> {
                writer.append(user.getUsername())
                        .append(", ")
                        .append(user.getPassword())
                        .append(", ")
                        .append(user.getChosenLanguage().name())
                        .append(", ")
                        .append(String.valueOf(user.getNumberOfSolvedUnits()))
                        .append(", ")
                        .append(String.valueOf(user.getNumberOfSolvedQuizzes()))
                        .append(", ")
                        .append(String.valueOf(user.getTotalPoints()))
                        .append("\n");
            });
            System.out.println(filename + " is successfully created.");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        }
    }
}
