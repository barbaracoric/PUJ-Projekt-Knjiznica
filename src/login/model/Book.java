package login.model;

import java.io.Serializable;

public class Book implements Serializable {
    private long book_id;
    private String title;
    private String author;
    private String category;

    public Book() {
        this.book_id = 0;
        this.title = "";
        this.author = "";
        this.category = "";
    }

    public Book(long book_id, String title, String author, String category) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
