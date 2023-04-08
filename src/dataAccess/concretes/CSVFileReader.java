package dataAccess.concretes;

import business.mappers.CSVMapper;
import business.mappers.LanguageMapper;
import dataAccess.abstracts.IFileReader;
import entities.Language;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileReader<T> implements IFileReader<T> {

    private final String path;
    private final String delimiter;
    private CSVMapper<T> mapper;

    public CSVFileReader(String path, String delimiter, CSVMapper<T> mapper) {
        this.path = path;
        this.delimiter = delimiter;
        this.mapper = mapper;
    }

    @Override
    public List<T> getAll() {
        List<T> elements = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            String[] data;
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                data = line.split(delimiter);
                elements.add(mapper.map(data));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fatal Error: " + e.getMessage());
        }
        return elements;
    }

    // TODO : çalışma şekli bu şekilde.
    /*public static void main(String[] args){
        CSVFileReader<Language> csvFileReader = new CSVFileReader<>("languages.csv", ",", new LanguageMapper());
        List<Language> languages = csvFileReader.getAll();
    }*/

}

