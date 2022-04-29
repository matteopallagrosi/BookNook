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


}
