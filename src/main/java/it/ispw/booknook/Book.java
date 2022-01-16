package it.ispw.booknook;

public class Book {
    private String title;
    private String author;
    private String tag1;
    private String tag2;
    public String expireData;

    public Book(String title, String author, String tag1, String tag2) {
        this.title = title;
        this.author = author;
        this.tag1 = tag1;
        this.tag2 = tag2;
    }

    public Book(String title, String author, String expireData) {
        this.title = title;
        this.author = author;
        this.expireData = expireData;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
