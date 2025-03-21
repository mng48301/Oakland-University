package libraryuml.interfaces;

public interface Borrowable {
    void checkOut();
    void checkIn();
    boolean isAvailable();
}
