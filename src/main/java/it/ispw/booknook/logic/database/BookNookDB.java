package it.ispw.booknook.logic.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


//singleton
//gestisce la connesione unica al database
public class BookNookDB {

    private static BookNookDB db = null;
    private Connection conn = null;
    private Properties p = null;

    private BookNookDB() {}

    public synchronized static BookNookDB getInstance() {
        if (BookNookDB.db == null)
            BookNookDB.db = new BookNookDB();
        return db;
    }

    public Connection getConn() {
        if (db.conn == null) {
            p = new Properties();
            //FileInputStream implementa interfaccia Autoclosable perciò try-with-resources assicura sempre la chiusura
            try (FileInputStream f = new FileInputStream("C:\\Users\\HP\\IdeaProjects\\BookNook\\src\\main\\resources\\dbconfig.properties")) {
                p.load(f);
                Class.forName(p.getProperty("jdbcDriver"));
                db.conn = DriverManager.getConnection(p.getProperty("jdbcUrl"), p.getProperty("jdbcUser"), p.getProperty("jdbcPass"));
            } catch (ClassNotFoundException | IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        return db.conn;
    }

    public void closeConn() {
        try {
            db.conn.close();
            db.conn = null;
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


}
