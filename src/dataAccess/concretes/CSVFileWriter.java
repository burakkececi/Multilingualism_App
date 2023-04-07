package dataAccess.concretes;

import business.abstracts.ILanguageService;
import business.abstracts.IQuestionService;
import business.abstracts.IQuizService;
import business.abstracts.IUnitService;
import business.concretes.LanguageCreator;
import business.concretes.QuestionCreator;
import business.concretes.QuizCreator;
import business.concretes.UnitCreator;
import dataAccess.abstracts.IFileWriter;
import entities.Language;
import entities.MultilingualismApp;
import entities.Quiz;
import entities.Unit;

import java.io.File;
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
}
