package dataAccess.abstracts;


import java.util.List;

public interface IFileReader<T> {

    List<T> getAll();

}
