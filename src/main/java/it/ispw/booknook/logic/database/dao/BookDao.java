package it.ispw.booknook.logic.database.dao;

import it.ispw.booknook.logic.database.BookNookDB;
import it.ispw.booknook.logic.database.queries.BookQueries;
import it.ispw.booknook.logic.database.queries.LogQueries;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDao {

    private BookDao() {}

    public static List<String>  getRequestedBooks(String title) {
        List<String> list = new ArrayList<String>();
        Connection conn = null;

        BookNookDB db = BookNookDB.getInstance();
        conn = db.getConn();

        try {
            ResultSet rs = BookQueries.getBooks(conn, title);

            if (!rs.first()){ // rs empty
                throw new Exception("No Books found matching with title or author");
            }

            list.add(rs.getString("titolo") + " " + rs.getString("autore"));

            //altrimenti libri presenti
            while (rs.next()) {
                list.add(rs.getString("titolo") + " " + rs.getString("autore"));
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



}
