package libraryuml.interfaces;

public interface Reservable {
    void reserve();
    void cancelReservation();
    boolean isReserved();
}
