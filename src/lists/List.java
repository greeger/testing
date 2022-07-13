package lists;

import myExceptions.PositionOutOfBoundsException;

import java.io.PrintStream;

public interface List<T> {
    int getSize();
    boolean insert(T value, int position);
    boolean delete(int position);
    T getElem(int position) throws PositionOutOfBoundsException;
    void print(PrintStream out);
}
