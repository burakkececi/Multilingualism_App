package business.mappers;

public interface CSVMapper<T> {

    /**
     * Handles generic mapping process.
     * @param data is read.
     * @return Generic object.
     */
    T map(String[] data);

}
