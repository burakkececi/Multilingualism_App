import business.mappers.UserMapper;
import dataAccess.abstracts.IFileReader;
import dataAccess.concretes.CSVFileReader;
import entities.Language;
import entities.MultilingualismApp;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // create a file reader
        // read the data.txt
        String path = "src/data/users.csv";

        // read all users from csv file.
        IFileReader<User> fileReader = new CSVFileReader<>(path, ";", new UserMapper());
        List<User> users = fileReader.getAll();

        MultilingualismApp multilingualismApp = new MultilingualismApp(users);
        multilingualismApp.start();
    }
}