package ClientServer.Shared;

import java.io.Serializable;

public class Book implements Serializable {

    private int storeId;
    private String name;
    private String firstAuthor;
    private int publishingYear;

    public Book(int storeId, String name, String firstAuthor, int publishingYear) {
        this.storeId = storeId;
        this.name = name;
        this.firstAuthor = firstAuthor;
        this.publishingYear = publishingYear;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }

    public String getFirstAuthor() {
        return firstAuthor;
    }

    public int getPublishingYear() {
        return publishingYear;
    }
}
