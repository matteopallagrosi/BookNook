package it.ispw.booknook.logic.database.queries;

import it.ispw.booknook.logic.entity.ReaderUser;

import java.sql.*;

public class LogQueries {

    public static int saveReaderUser(Connection connection, ReaderUser user) throws SQLException {
        String query = String.format("INSERT INTO utenti (username, email, password) VALUES (?, ?,?)");
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, user.getUsername());
        pstmt.setString( 2, user.getEmail());
        pstmt.setString( 3, user.getPassword());

        return pstmt.executeUpdate( );  // ritorna il numero di righe inserite/aggiornate
    }

    public static ResultSet selectReaderUser(Connection connection, String email, String password) throws SQLException {
        String query = "SELECT email,password FROM utenti where email = ? and password = ? ";
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, email);
        pstmt.setString( 2, password);
        return pstmt.executeQuery();
    }

    public static ResultSet getpass(Connection connection, String email) throws SQLException {
        String query = "SELECT password FROM utenti where email = ?";
        PreparedStatement pstmt = connection.prepareStatement( query );
        pstmt.setString( 1, email);
        return pstmt.executeQuery();
    }





}
