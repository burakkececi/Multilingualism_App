package dataAccess.abstracts;

import entities.Language;

import java.util.List;

public interface IFileWriter {

    void writeLanguageDetails(List<Language> language, String filename);

}
