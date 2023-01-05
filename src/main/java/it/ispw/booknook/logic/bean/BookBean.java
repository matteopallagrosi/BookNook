package it.ispw.booknook.logic.bean;

import it.ispw.booknook.logic.entity.Book;

import javafx.scene.image.Image;
import java.util.List;

public class BookBean {
    private String isbn;
    private String title;
    private String author;
    private List<String> tags;
    private String cover;
    private Image coverImage;


    public BookBean() {}

    public BookBean(String requestedTitle) {
        this.title = requestedTitle;
    }

    public BookBean(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.tags = book.getTags();
        this.cover = book.getCover();
        this.isbn = book.getIsbn();

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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Image coverImage) {
        this.coverImage = coverImage;
    }


    public void setBookDetails(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
    }

    public void splitDetails(String details){
        String[] strings = details.split(", ");
        this.title = strings[0];
        this.author = strings[1];
    }
}
