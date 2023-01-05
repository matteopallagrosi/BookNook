package it.ispw.booknook.logic.database.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookQueries {

    private BookQueries() {}

    public static ResultSet getBooks(Connection connection, String title) throws SQLException {
        String query = "SELECT titolo,autore FROM libri where titolo LIKE ? or autore LIKE ?";
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, "%" + title + "%");
        pstmt.setString( 2, "%" + title + "%");
        return pstmt.executeQuery();
    }

    //ritorna le informazioni di un possibile libro con un titolo e autore
    public static ResultSet getABook(Connection connection, String title, String author) throws SQLException {
        String query = "SELECT * FROM libri where titolo LIKE ? and autore LIKE ? LIMIT 1";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString( 1, "%" + title + "%");
        pstmt.setString( 2, "%" + author + "%");
        return pstmt.executeQuery();
    }

    //recupera tutti i tag di un certo libro
    public static ResultSet getTags(Connection connection, String isbn) throws SQLException {
        String query = "SELECT descrizione FROM libri JOIN tag_libri ON libri.ISBN = tag_libri.ISBN JOIN tag ON tag_libri.tag = tag.id  where libri.ISBN LIKE ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString( 1, isbn);
        return pstmt.executeQuery();
    }

    //recupera i libri che possiedono quei tag
    public static ResultSet getBooksByTags(Connection connection, String isbn, String tag) throws SQLException {
        String query = "SELECT * FROM libri JOIN tag_libri ON libri.ISBN = tag_libri.ISBN JOIN tag on tag_libri.tag = tag.id WHERE descrizione = ? AND libri.ISBN <> ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString( 1, tag);
        pstmt.setString(2, isbn);
        return pstmt.executeQuery();
    }

    public static int insertBookInList(Connection connection, String reader, String listName, String isbn) throws SQLException {
        String query = "INSERT INTO liste_lettura (lettore, nome, ISBN) VALUES (?, ?,?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString( 1, reader);
        pstmt.setString(2, listName);
        pstmt.setString(3, isbn);
        return pstmt.executeUpdate();  //ritorna il numero di righe inserite
    }




}
