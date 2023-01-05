package it.ispw.booknook.logic.database.dao;

import it.ispw.booknook.logic.database.BookNookDB;
import it.ispw.booknook.logic.database.queries.BookQueries;
import it.ispw.booknook.logic.entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDao {

    private BookDao() {}

    public static List<Book>  getRequestedBooks(String title) {
        List<Book> list = new ArrayList<Book>();
        Connection conn = null;
        Book book;

        BookNookDB db = BookNookDB.getInstance();
        conn = db.getConn();
        try {
            ResultSet rs = BookQueries.getBooks(conn, title);

            if (!rs.first()){ // rs empty
                throw new Exception("No Books found matching with title or author");
            }
           book = new Book(rs.getString("titolo"), rs.getString("autore"));
           list.add(book);

            //altrimenti libri presenti
            while (rs.next()) {
                book = new Book(rs.getString("titolo"), rs.getString("autore"));
                list.add(book);
            }

            rs.close();

        } catch(SQLException e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.log(Level.INFO, "This is message 1", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public static Book getSelectedBookDetails(String title, String author) {
        Book book = new Book();
        Connection conn = null;

        BookNookDB db = BookNookDB.getInstance();
        conn = db.getConn();
        try {
            ResultSet rs = BookQueries.getABook(conn, title, author);

            if (!rs.first()){ // rs empty
                throw new Exception("No Books found matching with title or author");
            }

            book.setISBN(rs.getString("ISBN"));
            book.setTitle(rs.getString("titolo"));
            book.setAuthor(rs.getString("autore"));
            book.setPublisher(rs.getString("editore"));
            book.setPublishingYear(rs.getInt("anno"));

            rs.close();

        } catch(SQLException e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.log(Level.INFO, "This is message 1", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //recupera i tag associati a quel libro
        try {
            ResultSet rs = BookQueries.getTags(conn, book.getISBN());

            if (!rs.first()){ // rs empty
                throw new Exception("No Books found matching with title or author");
            }

            book.setTag(rs.getString("descrizione"));

            while (rs.next()) {
                book.setTag(rs.getString("descrizione"));
            }

            rs.close();
        } catch(SQLException e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.log(Level.INFO, "This is message 1", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    public static List<String> getTagsByISBN(String ISBN) {
        //recupera i tag associati a quel libro
        Connection conn = null;
        List<String> tags = new ArrayList<>();

        BookNookDB db = BookNookDB.getInstance();
        conn = db.getConn();

        try {
            ResultSet rs = BookQueries.getTags(conn, ISBN);


            if (!rs.first()){ // rs empty
                throw new Exception("No Books found matching with title or author");
            }

            tags.add(rs.getString("descrizione"));

            while (rs.next()) {
                tags.add(rs.getString("descrizione"));
            }

            rs.close();
        } catch(SQLException e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.log(Level.INFO, "This is message 1", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tags;
    }

    public static List<Book> getRelatedBooks(List<String> tags, String isbn){
        List<Book> bookList = new ArrayList<>();
        Connection conn = null;
        BookNookDB db = BookNookDB.getInstance();
        conn = db.getConn();
        try {
            for (int i= 0; i< tags.size(); i++) {
                ResultSet rs = BookQueries.getBooksByTags(conn, isbn, tags.get(i));
                while(rs.next()){
                    Book book = new Book();
                    book.setISBN(rs.getString("libri.ISBN"));
                    book.setTitle(rs.getString("titolo"));
                    book.setAuthor(rs.getString("autore"));
                    book.setPublisher(rs.getString("editore"));
                    book.setPublishingYear(rs.getInt("anno"));
                    book.setTag(tags.get(i));
                    bookList.add(book);
                }
                rs.close();
            }

        } catch(SQLException e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.log(Level.INFO, "This is message 1", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
