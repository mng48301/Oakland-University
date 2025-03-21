package libraryuml.model;
import libraryuml.interfaces.Borrowable;
import libraryuml.interfaces.Reservable;

public abstract class LibraryItem implements Borrowable, Reservable {
    protected String id;
    protected String title;
    private boolean available;
    private boolean reserved;

    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.available = true;
        this.reserved = false;
    }

    public LibraryItem() {
        this("", "");
    }

    public abstract String getItemType();

    @Override
    public void checkOut() {
        available = false;
    }

    @Override
    public void checkIn() {
        available = true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void reserve() {
        reserved = true;
    }

    @Override
    public void cancelReservation() {
        reserved = false;
    }

    @Override
    public boolean isReserved() {
        return reserved;
    }
}