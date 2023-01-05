package it.ispw.booknook.logic.database.dao;

import it.ispw.booknook.logic.database.BookNookDB;
import it.ispw.booknook.logic.database.queries.LibraryQueries;
import it.ispw.booknook.logic.entity.Book;
import it.ispw.booknook.logic.entity.BookCopy;
import it.ispw.booknook.logic.entity.Library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibraryDao {

    private LibraryDao(){}

    public static HashMap<String, Library> getLibrariesByISBN(String ISBN){
        HashMap<String, Library> libraries = new HashMap<>();
        Connection conn = null;
        Book book = new Book(ISBN);


        BookNookDB db = BookNookDB.getInstance();
        conn = db.getConn();
        try {
            ResultSet rs = LibraryQueries.getLibraries(conn, ISBN);

            while(rs.next()){ // rs empty
            String name = rs.getString("nome");
            Library library = libraries.get(name);
            if (library == null) {
                library = new Library();
                library.setName(rs.getString("nome"));
                library.setAddress(rs.getString("indirizzo"));
                library.setOpeningTime(rs.getTime("ora_apertura"));
                library.setClosingTime(rs.getTime("ora_chiusura"));
                library.setLatitude(rs.getBigDecimal("latitudine"));
                library.setLongitude(rs.getBigDecimal("longitudine"));
                library.setCity(rs.getString("città"));
                libraries.put(name, library);
                library.addBook(book);
            }

            BookCopy copy = new BookCopy();
            copy.setId(rs.getInt("id"));
            copy.setBook(book);
            copy.setLibrary(library);
            copy.setState(rs.getInt("stato"));
            book.addCopy(copy);
            library.addCopy(copy);
            }

            rs.close();

        } catch(SQLException e) {
            Logger logger = Logger.getLogger("MyLog");
            logger.log(Level.INFO, "This is message 1", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return libraries;
    }
}
