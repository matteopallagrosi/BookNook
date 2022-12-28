package it.ispw.booknook.logic.entity;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    private List<String> tags = new ArrayList<String>();
    public String expireData;
    private String publisher;
    private int publishingYear;
    private String cover;
    private List<BookCopy> copies;


    public Book() {

    }
    public Book(String ISBN){
        this.ISBN = ISBN;

    }

    public Book(String title, String author, String tag1, String tag2) {
        this(title, author);
        this.tags.add(tag1);
        this.tags.add(tag2);
    }

    public Book(String title, String author, String expireData) {
        this(title, author);
        this.expireData = expireData;
    }
    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public void setTag(String tag) {
        this.tags.add(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<BookCopy> getCopies() {
        return copies;
    }

    public void setCopies(List<BookCopy> copies) {
        this.copies = copies;
    }

    public void addCopy(BookCopy copy) {
        if (copies == null) {
            copies = new ArrayList<BookCopy>();
        }
        copies.add(copy);
    }
}
