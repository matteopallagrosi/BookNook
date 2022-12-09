package it.ispw.booknook.logic.bean;

import it.ispw.booknook.logic.entity.Book;

public class BookBean {
    private String title;
    private String author;

    public BookBean(String requestedTitle) {
        this.title = requestedTitle;
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
}
