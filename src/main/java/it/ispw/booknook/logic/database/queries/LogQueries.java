package it.ispw.booknook.logic.database.queries;

import it.ispw.booknook.logic.entity.ReaderUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogQueries {

    public static int saveReaderUser(Statement stat, ReaderUser user) throws SQLException {
        String query = String.format("INSERT INTO readeruser (email, password) VALUES ('%s','%s')", user.getEmail(), user.getPassword());
        return stat.executeUpdate(query);
    }

    public static ResultSet selectReaderUser(Statement stat, String email, String password) throws SQLException {
        String query = "SELECT email,password FROM readeruser where email = '" + email + "' and password = '" + password + "';";
        return stat.executeQuery(query);
    }



}
