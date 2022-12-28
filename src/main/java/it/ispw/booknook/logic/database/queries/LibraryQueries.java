package it.ispw.booknook.logic.database.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryQueries {

    public static ResultSet getLibraries(Connection connection, String ISBN) throws SQLException {
        String query = "SELECT * FROM biblioteche JOIN copie ON biblioteca = username where ISBN LIKE ?";
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, ISBN);
        return pstmt.executeQuery();
    }
}
