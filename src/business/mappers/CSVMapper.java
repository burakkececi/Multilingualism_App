package business.mappers;

public interface CSVMapper<T> {

    T map(String[] data);

}
