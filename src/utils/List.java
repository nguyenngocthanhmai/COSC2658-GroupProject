package utils;

public interface List<T> {
    boolean insert(T data);
    boolean remove(T data);
    T get(int index);
    int size();
}
