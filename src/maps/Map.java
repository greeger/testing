package maps;

import java.io.PrintStream;

public interface Map<K,V> {
    boolean add(K key, V value);
    boolean delete(K key);
    V getValue(K key);
    void print(PrintStream out);
}
