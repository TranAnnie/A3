package ClientServer.Shared;

import java.io.Serializable;
import java.util.ArrayList;

public class BookList implements Serializable {

    private ArrayList<Book> bookList;

    public BookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }
}
