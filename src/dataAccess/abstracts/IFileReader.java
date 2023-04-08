package dataAccess.abstracts;


import java.util.List;

public interface IFileReader<T> {

    /**
     * Get all data with generic option.
     * @return Generic list object.
     */
    List<T> getAll();

}
