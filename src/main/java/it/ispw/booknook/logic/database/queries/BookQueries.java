package it.ispw.booknook.logic.database.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookQueries {

    public static ResultSet getBooks(Connection connection, String title) throws SQLException {
        String query = "SELECT titolo,autore FROM libri where titolo LIKE ? or autore LIKE ?";
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, "%" + title + "%");
        pstmt.setString( 2, "%" + title + "%");
        return pstmt.executeQuery();
    }


}
