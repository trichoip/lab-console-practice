package repo;

import java.util.ArrayList;

public interface ICrud<K, V> {

    boolean create(V newItem);

    ArrayList<V> read();

    V details(K id);

    boolean update(V editItem);

    boolean delete(K id);

    void saveToFile();

    ArrayList<V> readFromFile();

}
